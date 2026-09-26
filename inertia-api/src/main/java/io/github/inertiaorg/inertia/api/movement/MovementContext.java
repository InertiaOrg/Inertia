package io.github.inertiaorg.inertia.api.movement;

import io.github.inertiaorg.inertia.api.evidence.FalsePositiveContext;
import io.github.inertiaorg.inertia.api.session.EngineSession;
import org.jspecify.annotations.Nullable;

public record MovementContext(
        MovementFrame frame,
        @Nullable MovementState previousState,
        EngineSession session,
        FalsePositiveContext falsePositiveContext
) {
}