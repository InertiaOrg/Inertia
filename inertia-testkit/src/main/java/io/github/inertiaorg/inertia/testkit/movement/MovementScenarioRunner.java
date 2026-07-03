package io.github.inertiaorg.inertia.testkit.movement;

import io.github.inertiaorg.inertia.api.evidence.EvidenceAccumulator;
import io.github.inertiaorg.inertia.api.movement.MovementContext;
import io.github.inertiaorg.inertia.api.movement.MovementDecision;
import io.github.inertiaorg.inertia.api.movement.MovementProcessor;
import io.github.inertiaorg.inertia.api.movement.MovementState;
import io.github.inertiaorg.inertia.api.session.EngineSession;

import java.util.ArrayList;
import java.util.List;

public final class MovementScenarioRunner {

    private final MovementProcessor processor;
    private final EvidenceAccumulator accumulator;
    private final EngineSession session;

    public MovementScenarioRunner(MovementProcessor processor, EvidenceAccumulator accumulator, EngineSession session) {
        this.processor = processor;
        this.accumulator = accumulator;
        this.session = session;
    }

    public List<MovementDecision> run(List<MovementScenarioStep> steps) {
        List<MovementDecision> decisions = new ArrayList<>();
        MovementState previousState = null;
        for (MovementScenarioStep step : steps) {
            MovementDecision decision = processor.process(new MovementContext(step.frame(), previousState, session, step.context()), accumulator);
            decisions.add(decision);
            previousState = decision.predictionResult().state();
        }
        return List.copyOf(decisions);
    }
}
