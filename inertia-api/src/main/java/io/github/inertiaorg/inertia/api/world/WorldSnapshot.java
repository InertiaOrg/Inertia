package io.github.inertiaorg.inertia.api.world;

public interface WorldSnapshot {

    BlockStateView blockAt(int x, int y, int z);

    CollisionShape collisionShapeAt(int x, int y, int z);
}

