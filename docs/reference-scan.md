# Light Reference Scan

This note records high-level findings from local reference research without copying source.

Areas inspected:

* check base classes
* movement checks
* packet and bad-packet categories
* velocity handling
* false-positive exemptions
* violation and buffer patterns
* version handling
* debug and result models

High-level findings:

* checks usually sit on top of a shared state and category system instead of owning all state themselves
* movement checks rely on normalized position, on-ground state, recent velocity state, and exemption windows
* packet checks tend to care about ordering, frequency, and shape of normalized input rather than only raw packets
* repeated small signals are often buffered or accumulated instead of treated as immediate hard failures
* false-positive handling commonly depends on teleports, velocity grace, latency spikes, and server health
* version handling becomes expensive when engine code depends on server revisions instead of behavior groups
* debug output is most useful when it records why evidence was counted, reduced, or ignored

Inertia should model these needs directly through neutral frames, version profiles, false-positive context, evidence accumulation, and debug traces.

