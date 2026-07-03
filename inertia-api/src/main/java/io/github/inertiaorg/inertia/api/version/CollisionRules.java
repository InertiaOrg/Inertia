package io.github.inertiaorg.inertia.api.version;

import io.github.inertiaorg.inertia.api.world.BlockStateView;
import io.github.inertiaorg.inertia.api.world.CollisionShape;

public interface CollisionRules {

    CollisionShape shapeFor(BlockStateView blockState);

    boolean usesLegacyEdgeClipping();
}

