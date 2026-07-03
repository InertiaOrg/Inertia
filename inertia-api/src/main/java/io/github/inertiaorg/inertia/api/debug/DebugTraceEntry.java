package io.github.inertiaorg.inertia.api.debug;

import io.github.inertiaorg.inertia.api.evidence.EvidenceDecision;

public record DebugTraceEntry(
        long tick,
        String source,
        EvidenceDecision decision,
        double appliedWeight,
        String message
) {
}

