package io.github.inertiaorg.inertia.api.debug;

import java.util.List;

public interface DebugTrace {

    List<DebugTraceEntry> entries();

    default boolean isEmpty() {
        return entries().isEmpty();
    }
}

