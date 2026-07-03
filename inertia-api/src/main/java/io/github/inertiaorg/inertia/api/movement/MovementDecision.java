package io.github.inertiaorg.inertia.api.movement;

import io.github.inertiaorg.inertia.api.debug.DebugTrace;
import io.github.inertiaorg.inertia.api.evidence.EvidenceDecisionResult;
import io.github.inertiaorg.inertia.api.evidence.EvidenceRecord;

public record MovementDecision(
        PredictionResult predictionResult,
        EvidenceRecord evidence,
        EvidenceDecisionResult evidenceDecision,
        DebugTrace trace
) {
}

