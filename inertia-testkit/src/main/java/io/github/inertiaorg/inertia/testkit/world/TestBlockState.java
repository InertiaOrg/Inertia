package io.github.inertiaorg.inertia.testkit.world;

import io.github.inertiaorg.inertia.api.world.BlockStateView;

public record TestBlockState(String blockKey) implements BlockStateView {
}

