package io.github.inertiaorg.inertia.testkit.movement;

import io.github.inertiaorg.inertia.api.evidence.FalsePositiveContext;
import io.github.inertiaorg.inertia.api.math.Vec3;
import io.github.inertiaorg.inertia.api.movement.MovementFrame;

import java.util.ArrayList;
import java.util.List;

public final class MovementScenarioBuilder {

    private final List<MovementScenarioStep> steps = new ArrayList<>();
    private Vec3 currentPosition = Vec3.ZERO;
    private boolean currentOnGround = true;

    public MovementScenarioBuilder flatGround(long tick, double deltaX, double deltaZ) {
        return step(tick, deltaX, 0.0D, deltaZ, true, false, FalsePositiveContext.none());
    }

    public MovementScenarioBuilder impossibleMovement(long tick, double deltaX, double deltaZ) {
        return step(tick, deltaX, 0.0D, deltaZ, true, false, FalsePositiveContext.none());
    }

    public MovementScenarioBuilder teleportCorrection(long tick, double deltaX, double deltaY, double deltaZ) {
        return step(
                tick,
                deltaX,
                deltaY,
                deltaZ,
                true,
                false,
                new FalsePositiveContext(true, false, 1.0D, io.github.inertiaorg.inertia.api.evidence.LatencyContext.normal(), io.github.inertiaorg.inertia.api.evidence.ServerHealthContext.healthy())
        );
    }

    public MovementScenarioBuilder velocityGrace(long tick, double deltaX, double deltaY, double deltaZ, double reductionMultiplier) {
        return step(
                tick,
                deltaX,
                deltaY,
                deltaZ,
                true,
                false,
                new FalsePositiveContext(false, true, reductionMultiplier, io.github.inertiaorg.inertia.api.evidence.LatencyContext.normal(), io.github.inertiaorg.inertia.api.evidence.ServerHealthContext.healthy())
        );
    }

    public MovementScenarioBuilder step(long tick, double deltaX, double deltaY, double deltaZ, boolean onGround, boolean sprinting, FalsePositiveContext context) {
        Vec3 from = currentPosition;
        Vec3 to = new Vec3(from.x() + deltaX, from.y() + deltaY, from.z() + deltaZ);
        steps.add(new MovementScenarioStep(new MovementFrame(tick, from, to, onGround, sprinting, false), context));
        currentPosition = to;
        currentOnGround = onGround;
        return this;
    }

    public boolean currentOnGround() {
        return currentOnGround;
    }

    public List<MovementScenarioStep> build() {
        return List.copyOf(steps);
    }
}

