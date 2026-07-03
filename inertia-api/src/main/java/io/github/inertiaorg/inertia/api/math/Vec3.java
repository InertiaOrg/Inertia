package io.github.inertiaorg.inertia.api.math;

public record Vec3(double x, double y, double z) {

    public static final Vec3 ZERO = new Vec3(0.0D, 0.0D, 0.0D);
}

