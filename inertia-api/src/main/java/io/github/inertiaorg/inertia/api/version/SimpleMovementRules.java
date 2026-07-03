package io.github.inertiaorg.inertia.api.version;

public record SimpleMovementRules(
        double stepHeight,
        double groundFriction,
        double sprintAcceleration,
        double maxHorizontalDistance,
        double maxVerticalRise,
        double maxVerticalDrop
) implements MovementRules {
}
