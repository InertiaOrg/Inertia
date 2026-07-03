package io.github.inertiaorg.inertia.api.evidence;

public record EvidenceSensitivity(
        boolean latencySensitive,
        boolean serverSensitive,
        boolean exemptDuringTeleportCorrection,
        boolean exemptDuringVelocityGrace,
        boolean reduceDuringVelocityGrace
) {

    public static final EvidenceSensitivity NONE = new EvidenceSensitivity(false, false, false, false, false);
    public static final EvidenceSensitivity MOVEMENT = new EvidenceSensitivity(false, false, true, false, false);
    public static final EvidenceSensitivity MOVEMENT_WITH_VELOCITY_GRACE = new EvidenceSensitivity(false, false, true, false, true);
    public static final EvidenceSensitivity VELOCITY = new EvidenceSensitivity(false, false, false, true, false);
    public static final EvidenceSensitivity LATENCY = new EvidenceSensitivity(true, false, false, false, false);
    public static final EvidenceSensitivity SERVER = new EvidenceSensitivity(false, true, false, false, false);
}
