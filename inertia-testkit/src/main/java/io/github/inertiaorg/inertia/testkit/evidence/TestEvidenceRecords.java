package io.github.inertiaorg.inertia.testkit.evidence;

import io.github.inertiaorg.inertia.api.evidence.EvidenceDomain;
import io.github.inertiaorg.inertia.api.evidence.EvidenceRecord;
import io.github.inertiaorg.inertia.api.evidence.EvidenceSensitivity;
import io.github.inertiaorg.inertia.api.evidence.EvidenceType;

public final class TestEvidenceRecords {

    private TestEvidenceRecords() {
    }

    public static EvidenceRecord movement(long tick, double weight) {
        return new EvidenceRecord(EvidenceType.MOVEMENT_MISMATCH, EvidenceDomain.MOVEMENT, "movement-check", weight, tick, EvidenceSensitivity.MOVEMENT, "movement mismatch");
    }

    public static EvidenceRecord latencySensitive(long tick, double weight) {
        return new EvidenceRecord(EvidenceType.BAD_PACKET, EvidenceDomain.PACKET, "packet-check", weight, tick, EvidenceSensitivity.LATENCY, "packet burst");
    }

    public static EvidenceRecord serverSensitive(long tick, double weight) {
        return new EvidenceRecord(EvidenceType.GENERIC, EvidenceDomain.GENERAL, "server-check", weight, tick, EvidenceSensitivity.SERVER, "server-sensitive sample");
    }
}

