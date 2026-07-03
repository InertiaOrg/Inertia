package io.github.inertiaorg.inertia.api.evidence;

public record ServerHealthContext(double tps, boolean degraded, double reductionMultiplier) {

    public static ServerHealthContext healthy() {
        return new ServerHealthContext(20.0D, false, 1.0D);
    }

    public static ServerHealthContext degraded(double tps, double reductionMultiplier) {
        return new ServerHealthContext(tps, true, reductionMultiplier);
    }
}

