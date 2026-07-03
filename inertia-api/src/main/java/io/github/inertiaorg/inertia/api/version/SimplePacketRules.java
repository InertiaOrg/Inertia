package io.github.inertiaorg.inertia.api.version;

public record SimplePacketRules(
        boolean combinesPositionAndLook,
        boolean allowsGroundOnlyFrames
) implements PacketRules {
}

