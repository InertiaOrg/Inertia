package io.github.inertiaorg.inertia.testkit.movement;

import io.github.inertiaorg.inertia.api.session.EngineSession;
import io.github.inertiaorg.inertia.api.version.VersionProfile;
import io.github.inertiaorg.inertia.api.world.WorldSnapshot;

public record TestEngineSession(VersionProfile versionProfile, WorldSnapshot worldSnapshot) implements EngineSession {
}

