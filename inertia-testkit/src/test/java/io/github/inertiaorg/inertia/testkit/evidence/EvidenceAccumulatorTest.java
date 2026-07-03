package io.github.inertiaorg.inertia.testkit.evidence;

import io.github.inertiaorg.inertia.api.evidence.ConfidenceSnapshot;
import io.github.inertiaorg.inertia.api.evidence.EvidenceDecision;
import io.github.inertiaorg.inertia.api.evidence.EvidenceDecisionResult;
import io.github.inertiaorg.inertia.core.evidence.DefaultEvidenceAccumulator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EvidenceAccumulatorTest {

    @Test
    void repeatedEvidenceIncreasesConfidence() {
        DefaultEvidenceAccumulator accumulator = new DefaultEvidenceAccumulator();

        double first = accumulator.record(TestEvidenceRecords.movement(10L, 1.0D), TestContexts.clean()).snapshot().confidence();
        double second = accumulator.record(TestEvidenceRecords.movement(11L, 1.0D), TestContexts.clean()).snapshot().confidence();

        assertTrue(second > first);
    }

    @Test
    void staleEvidenceDecays() {
        DefaultEvidenceAccumulator accumulator = new DefaultEvidenceAccumulator();

        accumulator.record(TestEvidenceRecords.movement(10L, 1.5D), TestContexts.clean());
        double recentConfidence = accumulator.snapshot(10L).confidence();
        double decayedConfidence = accumulator.snapshot(200L).confidence();

        assertTrue(decayedConfidence < recentConfidence);
    }

    @Test
    void exemptedEvidenceDoesNotIncreaseConfidence() {
        DefaultEvidenceAccumulator accumulator = new DefaultEvidenceAccumulator();

        EvidenceDecisionResult result = accumulator.record(TestEvidenceRecords.movement(10L, 1.2D), TestContexts.teleportCorrection());
        ConfidenceSnapshot snapshot = accumulator.snapshot(10L);

        assertEquals(EvidenceDecision.EXEMPTED, result.decision());
        assertEquals(0.0D, snapshot.confidence());
    }

    @Test
    void latencySensitiveEvidenceIsReducedDuringLatencySpike() {
        DefaultEvidenceAccumulator accumulator = new DefaultEvidenceAccumulator();

        EvidenceDecisionResult result = accumulator.record(TestEvidenceRecords.latencySensitive(10L, 1.0D), TestContexts.latencySpike());

        assertEquals(EvidenceDecision.REDUCED, result.decision());
        assertTrue(result.appliedWeight() < 1.0D);
    }

    @Test
    void serverSensitiveEvidenceIsReducedDuringTpsDrop() {
        DefaultEvidenceAccumulator accumulator = new DefaultEvidenceAccumulator();

        EvidenceDecisionResult result = accumulator.record(TestEvidenceRecords.serverSensitive(10L, 1.0D), TestContexts.tpsDrop());

        assertEquals(EvidenceDecision.REDUCED, result.decision());
        assertTrue(result.appliedWeight() < 1.0D);
    }

    @Test
    void debugTraceRecordsEvidenceDecision() {
        DefaultEvidenceAccumulator accumulator = new DefaultEvidenceAccumulator();

        EvidenceDecisionResult result = accumulator.record(TestEvidenceRecords.latencySensitive(10L, 1.0D), TestContexts.latencySpike());

        assertEquals(1, result.snapshot().trace().entries().size());
        assertEquals(EvidenceDecision.REDUCED, result.snapshot().trace().entries().get(0).decision());
        assertTrue(result.snapshot().trace().entries().get(0).message().contains("latency"));
    }
}
