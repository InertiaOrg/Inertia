package io.github.inertiaorg.inertia.api.movement;

import io.github.inertiaorg.inertia.api.evidence.FalsePositiveContext;
import io.github.inertiaorg.inertia.api.session.EngineSession;

public record MovementContext(
        MovementFrame frame,
        MovementState previousState,
        EngineSession session,
        FalsePositiveContext falsePositiveContext
) {

}

