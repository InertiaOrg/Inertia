package io.github.inertiaorg.inertia.core.evidence;

import io.github.inertiaorg.inertia.api.debug.DebugTraceEntry;
import io.github.inertiaorg.inertia.api.evidence.ConfidenceSnapshot;
import io.github.inertiaorg.inertia.api.evidence.EvidenceAccumulator;
import io.github.inertiaorg.inertia.api.evidence.EvidenceDecision;
import io.github.inertiaorg.inertia.api.evidence.EvidenceDecisionResult;
import io.github.inertiaorg.inertia.api.evidence.EvidenceRecord;
import io.github.inertiaorg.inertia.api.evidence.FalsePositiveContext;
import io.github.inertiaorg.inertia.core.debug.InMemoryDebugTrace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class DefaultEvidenceAccumulator implements EvidenceAccumulator {

    private final double decayPerTick;
    private final double forgetThreshold;
    private final List<StoredEvidence> retainedEvidence = new ArrayList<>();
    private final InMemoryDebugTrace trace = new InMemoryDebugTrace();

    public DefaultEvidenceAccumulator() {
        this(0.02D, 0.05D);
    }

    public DefaultEvidenceAccumulator(double decayPerTick, double forgetThreshold) {
        this.decayPerTick = decayPerTick;
        this.forgetThreshold = forgetThreshold;
    }

    @Override
    public EvidenceDecisionResult record(EvidenceRecord evidence, FalsePositiveContext context) {
        compact(evidence.tick());

        if (context.teleportCorrectionActive() && evidence.sensitivity().exemptDuringTeleportCorrection()) {
            return result(evidence, EvidenceDecision.EXEMPTED, 0.0D, "teleport correction active");
        }

        if (context.velocityGraceActive() && evidence.sensitivity().exemptDuringVelocityGrace()) {
            return result(evidence, EvidenceDecision.EXEMPTED, 0.0D, "velocity grace active");
        }

        double appliedWeight = evidence.baseWeight();
        EvidenceDecision decision = EvidenceDecision.ACCEPTED;
        String reason = "accepted";

        if (evidence.sensitivity().latencySensitive() && context.latency().spikeActive()) {
            appliedWeight *= context.latency().reductionMultiplier();
            decision = EvidenceDecision.REDUCED;
            reason = "latency spike reduction";
        }

        if (context.velocityGraceActive() && evidence.sensitivity().reduceDuringVelocityGrace()) {
            appliedWeight *= context.velocityGraceReductionMultiplier();
            decision = EvidenceDecision.REDUCED;
            reason = decision == EvidenceDecision.REDUCED && !"accepted".equals(reason)
                    ? reason + ", velocity grace reduction"
                    : "velocity grace reduction";
        }

        if (evidence.sensitivity().serverSensitive() && context.serverHealth().degraded()) {
            appliedWeight *= context.serverHealth().reductionMultiplier();
            decision = EvidenceDecision.REDUCED;
            reason = decision == EvidenceDecision.REDUCED && !"accepted".equals(reason)
                    ? reason + ", server health reduction"
                    : "server health reduction";
        }

        if (appliedWeight > 0.0D) {
            retainedEvidence.add(new StoredEvidence(evidence.tick(), appliedWeight));
        }

        return result(evidence, decision, appliedWeight, reason);
    }

    @Override
    public ConfidenceSnapshot snapshot(long tick) {
        compact(tick);
        return new ConfidenceSnapshot(tick, confidenceAt(tick), retainedEvidence.size(), trace);
    }

    private EvidenceDecisionResult result(EvidenceRecord evidence, EvidenceDecision decision, double appliedWeight, String reason) {
        trace.append(new DebugTraceEntry(evidence.tick(), evidence.source(), decision, appliedWeight, reason));
        return new EvidenceDecisionResult(decision, appliedWeight, reason, snapshot(evidence.tick()));
    }

    private double confidenceAt(long tick) {
        double total = 0.0D;
        for (StoredEvidence retained : retainedEvidence) {
            total += retained.weightAt(tick, decayPerTick);
        }
        return total;
    }

    private void compact(long tick) {
        Iterator<StoredEvidence> iterator = retainedEvidence.iterator();
        while (iterator.hasNext()) {
            StoredEvidence retained = iterator.next();
            if (retained.weightAt(tick, decayPerTick) < forgetThreshold) {
                iterator.remove();
            }
        }
    }

    private record StoredEvidence(long tick, double appliedWeight) {

        private double weightAt(long currentTick, double decayPerTick) {
            long age = Math.max(0L, currentTick - tick);
            return appliedWeight * Math.exp(-decayPerTick * age);
        }
    }
}
