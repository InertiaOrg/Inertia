package io.github.inertiaorg.inertia.api.evidence;

public record LatencyContext(int pingMillis, boolean spikeActive, double reductionMultiplier) {

    public static LatencyContext normal() {
        return new LatencyContext(50, false, 1.0D);
    }

    public static LatencyContext spike(int pingMillis, double reductionMultiplier) {
        return new LatencyContext(pingMillis, true, reductionMultiplier);
    }
}

