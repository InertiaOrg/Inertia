package io.github.inertiaorg.inertia.api.version;

import java.util.Objects;

public record VersionRange(GameVersion minVersion, GameVersion maxVersion) {

    public VersionRange {
        Objects.requireNonNull(minVersion, "minVersion");
        Objects.requireNonNull(maxVersion, "maxVersion");
        if (minVersion.compareTo(maxVersion) > 0) {
            throw new IllegalArgumentException("minVersion must not be greater than maxVersion");
        }
    }

    public boolean contains(GameVersion version) {
        Objects.requireNonNull(version, "version");
        return version.compareTo(minVersion) >= 0 && version.compareTo(maxVersion) <= 0;
    }
}

