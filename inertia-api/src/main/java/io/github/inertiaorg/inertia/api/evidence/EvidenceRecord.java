package io.github.inertiaorg.inertia.api.evidence;

public record EvidenceRecord(
        EvidenceType type,
        EvidenceDomain domain,
        String source,
        double baseWeight,
        long tick,
        EvidenceSensitivity sensitivity,
        String detail
) {
}

