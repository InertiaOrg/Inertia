package io.github.inertiaorg.inertia.api.evidence;

public record FalsePositiveContext(
        boolean teleportCorrectionActive,
        boolean velocityGraceActive,
        double velocityGraceReductionMultiplier,
        LatencyContext latency,
        ServerHealthContext serverHealth
) {

    public static FalsePositiveContext none() {
        return new FalsePositiveContext(false, false, 1.0D, LatencyContext.normal(), ServerHealthContext.healthy());
    }
}
