package io.github.inertiaorg.inertia.testkit.movement;

import io.github.inertiaorg.inertia.api.evidence.EvidenceDecision;
import io.github.inertiaorg.inertia.api.movement.MovementDecision;
import io.github.inertiaorg.inertia.api.movement.PredictionStatus;
import io.github.inertiaorg.inertia.core.evidence.DefaultEvidenceAccumulator;
import io.github.inertiaorg.inertia.core.movement.DefaultMovementProcessor;
import io.github.inertiaorg.inertia.testkit.world.TestWorldSnapshot;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovementProcessorTest {

    @Test
    void flatGroundMovementDoesNotEmitEvidence() {
        MovementDecision decision = run(new MovementScenarioBuilder().flatGround(1L, 0.20D, 0.10D).build()).get(0);

        assertEquals(PredictionStatus.WITHIN_LIMITS, decision.predictionResult().status());
        assertNull(decision.evidence());
    }

    @Test
    void impossibleHorizontalMovementEmitsEvidence() {
        MovementDecision decision = run(new MovementScenarioBuilder().impossibleMovement(1L, 1.20D, 0.0D).build()).get(0);

        assertEquals(PredictionStatus.IMPOSSIBLE_HORIZONTAL, decision.predictionResult().status());
        assertNotNull(decision.evidence());
        assertEquals(EvidenceDecision.ACCEPTED, decision.evidenceDecision().decision());
    }

    @Test
    void teleportCorrectionSuppressesMovementEvidence() {
        MovementDecision decision = run(new MovementScenarioBuilder().teleportCorrection(1L, 2.5D, 0.0D, 0.0D).build()).get(0);

        assertEquals(PredictionStatus.TELEPORT_GRACE, decision.predictionResult().status());
        assertEquals(EvidenceDecision.EXEMPTED, decision.evidenceDecision().decision());
        assertEquals(0.0D, decision.evidenceDecision().appliedWeight());
    }

    @Test
    void velocityGraceReducesMovementEvidence() {
        MovementDecision decision = run(new MovementScenarioBuilder().velocityGrace(1L, 1.10D, 0.0D, 0.0D, 0.30D).build()).get(0);

        assertEquals(PredictionStatus.VELOCITY_GRACE, decision.predictionResult().status());
        assertEquals(EvidenceDecision.REDUCED, decision.evidenceDecision().decision());
        assertTrue(decision.evidenceDecision().appliedWeight() < decision.evidence().baseWeight());
    }

    @Test
    void movementDebugTraceExplainsDecision() {
        MovementDecision decision = run(new MovementScenarioBuilder().impossibleMovement(1L, 1.10D, 0.0D).build()).get(0);

        assertTrue(decision.trace().entries().stream().anyMatch(entry -> entry.message().contains("horizontal distance exceeded")));
        assertTrue(decision.trace().entries().stream().anyMatch(entry -> entry.source().equals("movement-processor")));
    }

    @Test
    void movementScenarioRunnerProcessesFramesInOrder() {
        List<MovementDecision> decisions = run(new MovementScenarioBuilder()
                .flatGround(1L, 0.15D, 0.0D)
                .flatGround(2L, 0.15D, 0.05D)
                .flatGround(3L, 0.10D, 0.05D)
                .build());

        assertEquals(3, decisions.size());
        assertEquals(1L, decisions.get(0).predictionResult().state().tick());
        assertEquals(2L, decisions.get(1).predictionResult().state().tick());
        assertEquals(3L, decisions.get(2).predictionResult().state().tick());
        assertEquals(
                decisions.get(0).predictionResult().state().currentPosition(),
                decisions.get(1).predictionResult().state().previousPosition()
        );
    }

    private List<MovementDecision> run(List<MovementScenarioStep> steps) {
        MovementScenarioRunner runner = new MovementScenarioRunner(
                new DefaultMovementProcessor(),
                new DefaultEvidenceAccumulator(),
                new TestEngineSession(io.github.inertiaorg.inertia.testkit.version.TestVersionProfiles.legacy(), new TestWorldSnapshot())
        );
        return runner.run(steps);
    }
}
