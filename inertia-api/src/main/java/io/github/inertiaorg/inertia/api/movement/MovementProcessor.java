package io.github.inertiaorg.inertia.api.movement;

import io.github.inertiaorg.inertia.api.evidence.EvidenceAccumulator;

public interface MovementProcessor {

    MovementDecision process(MovementContext context, EvidenceAccumulator accumulator);
}
