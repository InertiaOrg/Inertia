package io.github.inertiaorg.inertia.testkit.world;

import io.github.inertiaorg.inertia.api.world.BlockStateView;
import io.github.inertiaorg.inertia.api.world.BoxCollisionShape;
import io.github.inertiaorg.inertia.api.world.CollisionShape;
import io.github.inertiaorg.inertia.api.world.WorldSnapshot;

public final class TestWorldSnapshot implements WorldSnapshot {

    @Override
    public BlockStateView blockAt(int x, int y, int z) {
        return new TestBlockState("minecraft:stone");
    }

    @Override
    public CollisionShape collisionShapeAt(int x, int y, int z) {
        return BoxCollisionShape.FULL_BLOCK;
    }
}

