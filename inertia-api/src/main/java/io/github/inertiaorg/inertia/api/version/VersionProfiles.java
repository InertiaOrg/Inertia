package io.github.inertiaorg.inertia.api.version;

import java.util.List;

public final class VersionProfiles {

    public static final VersionProfile LEGACY_1_8 = new BasicVersionProfile(
            "LEGACY_1_8",
            ProfileFamily.LEGACY_1_8,
            new VersionRange(GameVersion.of(1, 8, 0), GameVersion.of(1, 8, 99)),
            new SimpleMovementRules(0.5D, 0.91D, 0.13D, 0.36D, 0.62D, 4.2D),
            new SimpleCollisionRules(true),
            new SimpleFluidRules(0.8D, 0.5D, false),
            new SimplePacketRules(true, true),
            new SimpleFeatureSet(false, false, false)
    );

    public static final VersionProfile LEGACY_1_12 = new BasicVersionProfile(
            "LEGACY_1_12",
            ProfileFamily.LEGACY_1_12,
            new VersionRange(GameVersion.of(1, 9, 0), GameVersion.of(1, 12, 99)),
            new SimpleMovementRules(0.6D, 0.91D, 0.13D, 0.37D, 0.62D, 4.3D),
            new SimpleCollisionRules(true),
            new SimpleFluidRules(0.8D, 0.5D, false),
            new SimplePacketRules(true, true),
            new SimpleFeatureSet(true, false, false)
    );

    public static final VersionProfile FLATTENING_1_13 = new BasicVersionProfile(
            "FLATTENING_1_13",
            ProfileFamily.FLATTENING_1_13,
            new VersionRange(GameVersion.of(1, 13, 0), GameVersion.of(1, 13, 99)),
            new SimpleMovementRules(0.6D, 0.91D, 0.13D, 0.37D, 0.62D, 4.3D),
            new SimpleCollisionRules(false),
            new SimpleFluidRules(0.8D, 0.5D, true),
            new SimplePacketRules(true, true),
            new SimpleFeatureSet(true, true, true)
    );

    public static final VersionProfile MODERN = new BasicVersionProfile(
            "MODERN",
            ProfileFamily.MODERN,
            new VersionRange(GameVersion.of(1, 14, 0), GameVersion.of(1, 20, 99)),
            new SimpleMovementRules(0.6D, 0.91D, 0.13D, 0.37D, 0.62D, 4.3D),
            new SimpleCollisionRules(false),
            new SimpleFluidRules(0.8D, 0.5D, true),
            new SimplePacketRules(true, true),
            new SimpleFeatureSet(true, true, true)
    );

    public static final VersionProfile LATEST_KNOWN = new BasicVersionProfile(
            "LATEST_KNOWN",
            ProfileFamily.LATEST_KNOWN,
            new VersionRange(GameVersion.of(1, 21, 0), GameVersion.of(1, 21, 99)),
            new SimpleMovementRules(0.6D, 0.91D, 0.13D, 0.37D, 0.62D, 4.3D),
            new SimpleCollisionRules(false),
            new SimpleFluidRules(0.8D, 0.5D, true),
            new SimplePacketRules(true, true),
            new SimpleFeatureSet(true, true, true)
    );

    private VersionProfiles() {
    }

    public static List<VersionProfile> defaults() {
        return List.of(LEGACY_1_8, LEGACY_1_12, FLATTENING_1_13, MODERN, LATEST_KNOWN);
    }
}
