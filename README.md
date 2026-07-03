# Inertia
[![Build](https://img.shields.io/github/actions/workflow/status/InertiaOrg/Inertia/build.yml?style=flat-square)](https://github.com/SQD-Studios/ChamoItemSkins/actions)
[![Static Badge](https://img.shields.io/badge/Documentation-8A2BE2?style=flat-square)](https://inertiaorg.github.io/starlight-docs/)
[![Javadocs](https://img.shields.io/badge/Javadocs-ED8B00?style=flat-square)](https://inertiaorg.github.io/Inertia/)
[![Static Badge](https://img.shields.io/badge/Jira-2684FF?style=flat-square)](https://inertiaorg.atlassian.net/)

Inertia is an open-source Minecraft anti-cheat foundation focused on version-neutral engine design.

The project is being built around behavior profiles, normalized movement and packet frames, world snapshots, collision models, and testable evidence handling.

## Current scope

This repository does not claim full Minecraft `1.8 -> latest` support yet.

The current implementation builds the foundation for that goal:

* lean module layout
* neutral version and profile API
* evidence and confidence accumulation
* false-positive context handling
* tests that run without a live server

## Module layout

The current repo intentionally stays small:

* `inertia-api` - neutral public contracts
* `inertia-core` - core engine and evidence behavior
* `inertia-testkit` - scenario-oriented tests
* `docs/` - project rules and research notes

Platform adapters, packet libraries, and plugin bootstrap code are intentionally deferred.

## Design rules

Inertia models behavior, not server package names.

That means:

* no Bukkit or NMS in the core
* no `v1_8_R3` style engine branching
* version differences belong in profiles
* the engine should be testable without booting a Minecraft server

## Early focus

The first implementation pass covers:

* `GameVersion`, `VersionRange`, and grouped `VersionProfile` rules
* movement, world, collision, packet, and debug contracts
* evidence records and a default evidence accumulator
* false-positive context for teleports, velocity grace, latency spikes, and server-health drops
* required tests for evidence accumulation, decay, reductions, and trace output

The repository now also includes the first movement prediction skeleton:

* movement state tracking across frames
* basic horizontal and vertical sanity limits from version profiles
* teleport grace and velocity grace handling
* movement debug traces
* scenario-driven movement tests

This is still a foundation pass, not full vanilla movement parity.

## Reference policy

Local reference projects may be inspected for category-level research only.

They are not part of Inertia and must not be copied into this repository.

See [docs/reference-policy.md](docs/reference-policy.md).

For the current engine boundaries and movement limits, see [docs/architecture.md](docs/architecture.md).