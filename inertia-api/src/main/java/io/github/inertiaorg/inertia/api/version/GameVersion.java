package io.github.inertiaorg.inertia.api.version;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record GameVersion(
        int major,
        int minor,
        int patch,
        @Nullable Integer protocolNumber,
        @NonNull String displayName
) implements Comparable<GameVersion> {

    public static GameVersion of(int major, int minor, int patch) {
        return new GameVersion(major, minor, patch, null, String.format("%d.%d.%d", major, minor, patch));
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