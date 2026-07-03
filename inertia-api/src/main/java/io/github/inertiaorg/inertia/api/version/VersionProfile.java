package io.github.inertiaorg.inertia.api.version;

public interface VersionProfile {

    String name();

    ProfileFamily family();

    VersionRange supportedRange();

    MovementRules movementRules();

    CollisionRules collisionRules();

    FluidRules fluidRules();

    PacketRules packetRules();

    FeatureSet features();
}

