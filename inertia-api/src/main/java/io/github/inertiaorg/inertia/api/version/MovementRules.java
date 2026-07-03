package io.github.inertiaorg.inertia.api.version;

public interface MovementRules {

    double stepHeight();

    double groundFriction();

    double sprintAcceleration();

    double maxHorizontalDistance();

    double maxVerticalRise();

    double maxVerticalDrop();
}
