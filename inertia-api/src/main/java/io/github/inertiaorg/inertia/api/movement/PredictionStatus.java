package io.github.inertiaorg.inertia.api.movement;

public enum PredictionStatus {
    WITHIN_LIMITS,
    IMPOSSIBLE_HORIZONTAL,
    IMPOSSIBLE_VERTICAL,
    TELEPORT_GRACE,
    VELOCITY_GRACE
}

