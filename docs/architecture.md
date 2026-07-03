# Architecture

## Current shape

Inertia currently has:

* neutral API contracts in `inertia-api`
* core engine behavior in `inertia-core`
* fake test support in `inertia-testkit`

Server adapters are still future work.

## Movement skeleton

Inertia now has the first movement prediction skeleton.

Current scope:

* movement state tracking from sequential frames
* horizontal and vertical sanity limits from `VersionProfile`
* teleport correction grace
* velocity grace reduction skeleton
* movement evidence emission through the existing evidence accumulator
* movement debug traces

Current limits:

* this is not full vanilla physics
* collision and world data are passed through but only lightly used so far
* version profiles currently provide basic movement limits, not full per-version parity

Full version parity and deeper movement simulation are future work.

