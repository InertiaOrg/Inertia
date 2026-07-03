package io.github.inertiaorg.inertia.api.evidence;

import io.github.inertiaorg.inertia.api.debug.DebugTrace;

public record ConfidenceSnapshot(
        long tick,
        double confidence,
        int retainedEvidenceCount,
        DebugTrace trace
) {
}

