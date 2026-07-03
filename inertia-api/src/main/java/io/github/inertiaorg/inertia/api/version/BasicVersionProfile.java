package io.github.inertiaorg.inertia.api.version;

public record BasicVersionProfile(
        String name,
        ProfileFamily family,
        VersionRange supportedRange,
        MovementRules movementRules,
        CollisionRules collisionRules,
        FluidRules fluidRules,
        PacketRules packetRules,
        FeatureSet features
) implements VersionProfile {
}

