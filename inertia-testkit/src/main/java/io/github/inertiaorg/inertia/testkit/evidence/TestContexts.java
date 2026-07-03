package io.github.inertiaorg.inertia.testkit.evidence;

import io.github.inertiaorg.inertia.api.evidence.FalsePositiveContext;
import io.github.inertiaorg.inertia.api.evidence.LatencyContext;
import io.github.inertiaorg.inertia.api.evidence.ServerHealthContext;

public final class TestContexts {

    private TestContexts() {
    }

    public static FalsePositiveContext clean() {
        return FalsePositiveContext.none();
    }

    public static FalsePositiveContext teleportCorrection() {
        return new FalsePositiveContext(true, false, 1.0D, LatencyContext.normal(), ServerHealthContext.healthy());
    }

    public static FalsePositiveContext latencySpike() {
        return new FalsePositiveContext(false, false, 1.0D, LatencyContext.spike(350, 0.25D), ServerHealthContext.healthy());
    }

    public static FalsePositiveContext tpsDrop() {
        return new FalsePositiveContext(false, false, 1.0D, LatencyContext.normal(), ServerHealthContext.degraded(14.5D, 0.4D));
    }

    public static FalsePositiveContext velocityGrace(double reductionMultiplier) {
        return new FalsePositiveContext(false, true, reductionMultiplier, LatencyContext.normal(), ServerHealthContext.healthy());
    }
}
