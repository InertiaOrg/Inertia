package io.github.inertiaorg.inertia.api.packet;

import io.github.inertiaorg.inertia.api.math.Vec3;

public record PacketFrame(
        long tick,
        PacketFrameType type,
        Vec3 position,
        boolean onGround
) {
}

