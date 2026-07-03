package io.github.inertiaorg.inertia.api.version;

import java.util.Objects;

public record GameVersion(
        int major,
        int minor,
        int patch,
        Integer protocolNumber,
        String displayName
) implements Comparable<GameVersion> {

    public GameVersion {
        Objects.requireNonNull(displayName, "displayName");
    }

    public static GameVersion of(int major, int minor, int patch) {
        return new GameVersion(major, minor, patch, null, major + "." + minor + "." + patch);
    }

    @Override
    public int compareTo(GameVersion other) {
        int majorCompare = Integer.compare(major, other.major);
        if (majorCompare != 0) {
            return majorCompare;
        }
        int minorCompare = Integer.compare(minor, other.minor);
        if (minorCompare != 0) {
            return minorCompare;
        }
        return Integer.compare(patch, other.patch);
    }
}

