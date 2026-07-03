package io.github.inertiaorg.inertia.api.movement;

public record MovementPrediction(
        double allowedHorizontalDistance,
        double allowedVerticalRise,
        double allowedVerticalDrop,
        boolean teleportGraceActive,
        boolean velocityGraceActive
) {
}

