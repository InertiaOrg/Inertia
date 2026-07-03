package io.github.inertiaorg.inertia.testkit.version;

import io.github.inertiaorg.inertia.api.version.VersionProfile;
import io.github.inertiaorg.inertia.api.version.VersionProfiles;

public final class TestVersionProfiles {

    private TestVersionProfiles() {
    }

    public static VersionProfile legacy() {
        return VersionProfiles.LEGACY_1_8;
    }

    public static VersionProfile modern() {
        return VersionProfiles.MODERN;
    }
}

