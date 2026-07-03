package io.github.inertiaorg.inertia.api.evidence;

public record EvidenceDecisionResult(
        EvidenceDecision decision,
        double appliedWeight,
        String reason,
        ConfidenceSnapshot snapshot
) {
}

