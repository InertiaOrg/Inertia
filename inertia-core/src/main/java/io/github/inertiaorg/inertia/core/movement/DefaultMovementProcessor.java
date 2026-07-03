package io.github.inertiaorg.inertia.core.movement;

import io.github.inertiaorg.inertia.api.debug.DebugTraceEntry;
import io.github.inertiaorg.inertia.api.evidence.EvidenceAccumulator;
import io.github.inertiaorg.inertia.api.evidence.EvidenceDecision;
import io.github.inertiaorg.inertia.api.evidence.EvidenceDecisionResult;
import io.github.inertiaorg.inertia.api.evidence.EvidenceDomain;
import io.github.inertiaorg.inertia.api.evidence.EvidenceRecord;
import io.github.inertiaorg.inertia.api.evidence.EvidenceSensitivity;
import io.github.inertiaorg.inertia.api.evidence.EvidenceType;
import io.github.inertiaorg.inertia.api.math.Vec3;
import io.github.inertiaorg.inertia.api.movement.MovementContext;
import io.github.inertiaorg.inertia.api.movement.MovementDecision;
import io.github.inertiaorg.inertia.api.movement.MovementDelta;
import io.github.inertiaorg.inertia.api.movement.MovementEvidenceType;
import io.github.inertiaorg.inertia.api.movement.MovementPrediction;
import io.github.inertiaorg.inertia.api.movement.MovementProcessor;
import io.github.inertiaorg.inertia.api.movement.MovementState;
import io.github.inertiaorg.inertia.api.movement.PredictionResult;
import io.github.inertiaorg.inertia.api.movement.PredictionStatus;
import io.github.inertiaorg.inertia.api.version.MovementRules;
import io.github.inertiaorg.inertia.core.debug.InMemoryDebugTrace;

public final class DefaultMovementProcessor implements MovementProcessor {

    @Override
    public MovementDecision process(MovementContext context, EvidenceAccumulator accumulator) {
        MovementState state = buildState(context);
        MovementPrediction prediction = buildPrediction(context);
        InMemoryDebugTrace trace = new InMemoryDebugTrace();

        PredictionResult predictionResult = classify(context, state, prediction);
        trace.append(new DebugTraceEntry(
                state.tick(),
                "movement-processor",
                localDecision(predictionResult.status()),
                0.0D,
                predictionResult.reason()
        ));

        EvidenceRecord evidence = buildEvidence(predictionResult, state);
        EvidenceDecisionResult evidenceDecision = null;
        if (evidence != null) {
            evidenceDecision = accumulator.record(evidence, context.falsePositiveContext());
            trace.appendAll(evidenceDecision.snapshot().trace());
        }

        return new MovementDecision(predictionResult, evidence, evidenceDecision, trace);
    }

    private MovementState buildState(MovementContext context) {
        Vec3 previousPosition = context.previousState() != null
                ? context.previousState().currentPosition()
                : context.frame().from();
        boolean previousOnGround = context.previousState() != null && context.previousState().currentOnGround();
        Vec3 currentPosition = context.frame().to();
        double deltaX = currentPosition.x() - previousPosition.x();
        double deltaY = currentPosition.y() - previousPosition.y();
        double deltaZ = currentPosition.z() - previousPosition.z();
        double horizontalDistance = Math.sqrt((deltaX * deltaX) + (deltaZ * deltaZ));
        return new MovementState(
                context.frame().tick(),
                previousPosition,
                currentPosition,
                new MovementDelta(deltaX, deltaY, deltaZ, horizontalDistance),
                previousOnGround,
                context.frame().onGround()
        );
    }

    private MovementPrediction buildPrediction(MovementContext context) {
        MovementRules rules = context.session().versionProfile().movementRules();
        double horizontalLimit = rules.maxHorizontalDistance();
        if (context.frame().sprinting()) {
            horizontalLimit += rules.sprintAcceleration();
        }
        return new MovementPrediction(
                horizontalLimit,
                rules.maxVerticalRise(),
                rules.maxVerticalDrop(),
                context.falsePositiveContext().teleportCorrectionActive(),
                context.falsePositiveContext().velocityGraceActive()
        );
    }

    private PredictionResult classify(MovementContext context, MovementState state, MovementPrediction prediction) {
        boolean impossibleHorizontal = state.delta().horizontalDistance() > prediction.allowedHorizontalDistance();
        boolean impossibleVertical = state.delta().y() > prediction.allowedVerticalRise()
                || state.delta().y() < -prediction.allowedVerticalDrop();

        if (!impossibleHorizontal && !impossibleVertical) {
            return new PredictionResult(
                    PredictionStatus.WITHIN_LIMITS,
                    prediction,
                    state,
                    null,
                    "movement stayed within the current skeleton limits"
            );
        }

        if (context.falsePositiveContext().teleportCorrectionActive()) {
            return new PredictionResult(
                    PredictionStatus.TELEPORT_GRACE,
                    prediction,
                    state,
                    impossibleHorizontal ? MovementEvidenceType.IMPOSSIBLE_HORIZONTAL_MOVEMENT : MovementEvidenceType.IMPOSSIBLE_VERTICAL_MOVEMENT,
                    "movement exceeded limits during teleport correction grace"
            );
        }

        if (context.falsePositiveContext().velocityGraceActive()) {
            return new PredictionResult(
                    PredictionStatus.VELOCITY_GRACE,
                    prediction,
                    state,
                    impossibleHorizontal ? MovementEvidenceType.IMPOSSIBLE_HORIZONTAL_MOVEMENT : MovementEvidenceType.IMPOSSIBLE_VERTICAL_MOVEMENT,
                    "movement exceeded limits during velocity grace"
            );
        }

        if (impossibleHorizontal) {
            return new PredictionResult(
                    PredictionStatus.IMPOSSIBLE_HORIZONTAL,
                    prediction,
                    state,
                    MovementEvidenceType.IMPOSSIBLE_HORIZONTAL_MOVEMENT,
                    "horizontal distance exceeded the current skeleton limit"
            );
        }

        return new PredictionResult(
                PredictionStatus.IMPOSSIBLE_VERTICAL,
                prediction,
                state,
                MovementEvidenceType.IMPOSSIBLE_VERTICAL_MOVEMENT,
                "vertical change exceeded the current skeleton limit"
        );
    }

    private EvidenceRecord buildEvidence(PredictionResult predictionResult, MovementState state) {
        if (predictionResult.status() == PredictionStatus.WITHIN_LIMITS) {
            return null;
        }

        double baseWeight = predictionResult.evidenceType() == MovementEvidenceType.IMPOSSIBLE_HORIZONTAL_MOVEMENT ? 1.25D : 1.0D;
        return new EvidenceRecord(
                EvidenceType.MOVEMENT_MISMATCH,
                EvidenceDomain.MOVEMENT,
                "movement-processor",
                baseWeight,
                state.tick(),
                EvidenceSensitivity.MOVEMENT_WITH_VELOCITY_GRACE,
                predictionResult.reason()
        );
    }

    private EvidenceDecision localDecision(PredictionStatus status) {
        return switch (status) {
            case WITHIN_LIMITS -> EvidenceDecision.IGNORED;
            case TELEPORT_GRACE -> EvidenceDecision.EXEMPTED;
            case VELOCITY_GRACE -> EvidenceDecision.REDUCED;
            case IMPOSSIBLE_HORIZONTAL, IMPOSSIBLE_VERTICAL -> EvidenceDecision.ACCEPTED;
        };
    }
}
