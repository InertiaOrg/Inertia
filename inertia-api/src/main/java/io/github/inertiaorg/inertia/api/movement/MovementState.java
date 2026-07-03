package io.github.inertiaorg.inertia.api.movement;

import io.github.inertiaorg.inertia.api.math.Vec3;

public record MovementState(
        long tick,
        Vec3 previousPosition,
        Vec3 currentPosition,
        MovementDelta delta,
        boolean previousOnGround,
        boolean currentOnGround
) {
}

