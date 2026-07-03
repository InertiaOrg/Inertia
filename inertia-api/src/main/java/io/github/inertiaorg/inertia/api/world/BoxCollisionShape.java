package io.github.inertiaorg.inertia.api.world;

public record BoxCollisionShape(double width, double height, boolean empty) implements CollisionShape {

    public static final BoxCollisionShape FULL_BLOCK = new BoxCollisionShape(1.0D, 1.0D, false);
    public static final BoxCollisionShape EMPTY = new BoxCollisionShape(0.0D, 0.0D, true);
}

