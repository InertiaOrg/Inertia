package io.github.inertiaorg.inertia.api.movement;

import org.jspecify.annotations.Nullable;

public record PredictionResult(
        PredictionStatus status,
        MovementPrediction prediction,
        MovementState state,
        @Nullable MovementEvidenceType evidenceType,
        String reason
) {
}