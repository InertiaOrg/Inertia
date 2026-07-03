package io.github.inertiaorg.inertia.api.version;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VersionRangeTest {

    @Test
    void versionRangeContainsExpectedVersions() {
        VersionRange range = new VersionRange(GameVersion.of(1, 8, 0), GameVersion.of(1, 12, 99));

        assertTrue(range.contains(GameVersion.of(1, 8, 0)));
        assertTrue(range.contains(GameVersion.of(1, 12, 2)));
        assertFalse(range.contains(GameVersion.of(1, 7, 10)));
        assertFalse(range.contains(GameVersion.of(1, 13, 0)));
    }
}

