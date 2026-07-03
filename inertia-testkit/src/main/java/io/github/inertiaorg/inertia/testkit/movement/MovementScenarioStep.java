package io.github.inertiaorg.inertia.testkit.movement;

import io.github.inertiaorg.inertia.api.evidence.FalsePositiveContext;
import io.github.inertiaorg.inertia.api.movement.MovementFrame;

public record MovementScenarioStep(
        MovementFrame frame,
        FalsePositiveContext context
) {
}

