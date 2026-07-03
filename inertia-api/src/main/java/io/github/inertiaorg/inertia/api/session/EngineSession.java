package io.github.inertiaorg.inertia.api.session;

import io.github.inertiaorg.inertia.api.version.VersionProfile;
import io.github.inertiaorg.inertia.api.world.WorldSnapshot;

public interface EngineSession {

    VersionProfile versionProfile();

    WorldSnapshot worldSnapshot();
}

