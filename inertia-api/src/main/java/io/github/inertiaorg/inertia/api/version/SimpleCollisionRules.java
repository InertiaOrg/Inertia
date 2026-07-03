package io.github.inertiaorg.inertia.api.version;

import io.github.inertiaorg.inertia.api.world.BlockStateView;
import io.github.inertiaorg.inertia.api.world.BoxCollisionShape;
import io.github.inertiaorg.inertia.api.world.CollisionShape;

public record SimpleCollisionRules(boolean usesLegacyEdgeClipping) implements CollisionRules {

    @Override
    public CollisionShape shapeFor(BlockStateView blockState) {
        return BoxCollisionShape.FULL_BLOCK;
    }
}

