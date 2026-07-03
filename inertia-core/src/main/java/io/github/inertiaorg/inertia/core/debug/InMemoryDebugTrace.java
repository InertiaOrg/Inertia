package io.github.inertiaorg.inertia.core.debug;

import io.github.inertiaorg.inertia.api.debug.DebugTrace;
import io.github.inertiaorg.inertia.api.debug.DebugTraceEntry;

import java.util.ArrayList;
import java.util.List;

public final class InMemoryDebugTrace implements DebugTrace {

    private final List<DebugTraceEntry> entries = new ArrayList<>();

    public void append(DebugTraceEntry entry) {
        entries.add(entry);
    }

    @Override
    public List<DebugTraceEntry> entries() {
        return List.copyOf(entries);
    }

    public void appendAll(DebugTrace trace) {
        entries.addAll(trace.entries());
    }
}
