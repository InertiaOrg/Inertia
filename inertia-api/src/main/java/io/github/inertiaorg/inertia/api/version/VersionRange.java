package io.github.inertiaorg.inertia.api.version;

import org.jspecify.annotations.NonNull;

public record VersionRange(@NonNull GameVersion minVersion, @NonNull GameVersion maxVersion) {

    public VersionRange {
        if (minVersion.compareTo(maxVersion) > 0) {
            throw new IllegalArgumentException("minVersion must not be greater than maxVersion");
        }
    }

    public boolean contains(@NonNull GameVersion version) {
        return version.compareTo(minVersion) >= 0 && version.compareTo(maxVersion) <= 0;
    }
}