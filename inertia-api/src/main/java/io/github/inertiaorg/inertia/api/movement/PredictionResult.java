package io.github.inertiaorg.inertia.api.movement;

public record PredictionResult(
        PredictionStatus status,
        MovementPrediction prediction,
        MovementState state,
        MovementEvidenceType evidenceType,
        String reason
) {
}

