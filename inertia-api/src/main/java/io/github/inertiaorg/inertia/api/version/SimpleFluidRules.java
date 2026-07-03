package io.github.inertiaorg.inertia.api.version;

public record SimpleFluidRules(
        double waterDrag,
        double lavaDrag,
        boolean supportsModernSwimming
) implements FluidRules {
}

