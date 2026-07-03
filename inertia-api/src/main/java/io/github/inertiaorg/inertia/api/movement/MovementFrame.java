package io.github.inertiaorg.inertia.api.movement;

import io.github.inertiaorg.inertia.api.math.Vec3;

public record MovementFrame(
        long tick,
        Vec3 from,
        Vec3 to,
        boolean onGround,
        boolean sprinting,
        boolean sneaking
) {
}

