<div align="center">

# AllayClaims

**A lightweight land-claim plugin for Spigot/Paper 1.20+ — supporting both golden-shovel and chunk-based claiming.**

Protect what's yours — simply, intuitively, and without the bloat.

[Features](#features) · [Installation](#installation) · [Claim Modes](#claim-modes) · [How to Claim](#how-to-claim) · [Commands](#commands) · [Configuration](#configuration) · [Placeholders](#placeholders)

</div>

---

## Overview

AllayClaims is a minimalist land-claim plugin offering two claim modes:

- **Shovel mode** (default) — Inspired by GriefPrevention. Players select two corners with a golden shovel to create a rectangular claim of any size.
- **Chunk mode** — Inspired by plugins like NClaims and Towny. Players claim entire 16x16 chunks with a single command. The golden shovel is disabled.

Both modes share the same trust system, economy integration, explosion toggle, and protection logic. Switch between modes with a single config option.

Designed for servers that want core grief protection without the overhead of a full-featured land-management suite.

## Features

- **Two Claim Modes** — Choose between golden-shovel (freeform rectangle) or chunk-based claiming. Disabled by default; enable with one config line.
- **Golden Shovel Claiming** (shovel mode) — Select two corners with a golden shovel to create a rectangular claim. A visual frame appears so you can see exactly what's protected.
- **Chunk Claiming** (chunk mode) — Claim the entire chunk you're standing in with `/chunkclaim`. Each chunk costs a configurable number of claim blocks. Unclaim with `/chunkunclaim`.
- **Flat Frame Visualization** — Claims are shown as a single-layer rectangular outline on the ground (not a 3D box), keeping the view clean and unobtrusive.
- **Trust System** — Grant specific players access to your claims with `/trust`. Revoke anytime with `/untrust`.
- **Claim Block Economy** — Each player starts with a pool of claim blocks. Larger claims (or more chunks) cost more blocks. Buy and sell blocks with `/buyclaimblocks` and `/sellclaimblocks` (requires Vault).
- **Explosion Toggle** — Claim owners can enable or disable explosions within their claim using `/claimexplosion`.
- **Piston & Redstone Freedom** — Pistons and redstone work freely in unclaimed areas by default. Inside claims, pistons cannot push blocks across claim boundaries.
- **Admin Tools** — Server operators can manage claims with `/bbclaim`: delete a player's claims, give or take claim blocks, and toggle admin bypass.
- **Entry Notifications** — A title appears when you enter a claim, showing whose claim it is.
- **100% Translatable Messages** — Every message lives in `messages.yml` and supports color codes and placeholders.
- **PlaceholderAPI Support** — Exposes the `%allayclaims_claim_block%` placeholder for remaining claim blocks.
- **Per-World Control** — Configure which worlds allow claiming and which don't.
- **Data Persistence** — Claims and player data are saved to a simple YAML file. No external database required.
- **Lightweight** — Small JAR with minimal dependencies. Only Vault and PlaceholderAPI are optional soft-dependencies.

## Installation

1. Download the `AllayClaims.jar` from the [releases page](https://github.com/AllayMC/AllayClaims/releases).
2. Place the jar in your server's `plugins/` folder.
3. (Optional) Install [Vault](https://www.spigotmc.org/resources/vault.34315/) and an economy plugin to enable buying and selling claim blocks.
4. (Optional) Install [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) to use the `%allayclaims_claim_block%` placeholder.
5. Restart your server.
6. Edit `plugins/AllayClaims/config.yml` and `messages.yml` to your liking.
7. Reload or restart.

**Requirements:**
- Java 21 or higher
- Spigot/Paper 1.20.6+ (api-version 1.20)

## Claim Modes

AllayClaims supports two mutually exclusive claim modes. Only one is active at a time, controlled by the `claim-mode` setting in `config.yml`.

### Shovel Mode (default)

```yaml
claim-mode: shovel
```

Players use a golden shovel to select two corners of a rectangle. The claim covers the full vertical column (bedrock to sky) within that rectangle. Claim cost is based on area (1 block = 1 claim block).

### Chunk Mode

```yaml
claim-mode: chunk
```

Players use `/chunkclaim` to claim the entire 16x16 chunk they're standing in. The golden shovel is disabled — if a player tries to use it, they'll see a message telling them chunk mode is active. Each chunk costs a configurable number of claim blocks (default: 256, the area of one chunk). Use `/chunkunclaim` to release a chunk.

Chunk mode is ideal for servers that want grid-aligned, predictable claims similar to NClaims or Towny, without the complexity of towns, plots, or nations.

### Switching Modes

Change `claim-mode` in `config.yml` and restart. Existing claims created in the previous mode remain valid and protected — only the method for creating *new* claims changes. You can mix shovel and chunk claims in the same world if you switch modes after some claims already exist.

## How to Claim

### Shovel Mode

1. **Equip a golden shovel.** This is your claim tool.
2. **Right-click the ground** at the first corner. You'll see a confirmation message with coordinates and a single-block visual marker.
3. **Right-click the ground** at the opposite corner. The claim is created instantly. A glowing rectangular frame appears showing the boundary, and you'll see remaining claim blocks.

### Chunk Mode

1. **Stand in the chunk** you want to claim.
2. **Run `/chunkclaim`.** The entire chunk is claimed instantly. A visual frame appears showing the chunk boundary, and you'll see the cost and remaining blocks.
3. **To release a chunk**, stand in it and run `/chunkunclaim`.

### Tips (both modes)

- You need enough claim blocks to cover the cost. Check your balance with `%allayclaims_claim_block%` (PlaceholderAPI).
- Use `/unclaim` (shovel mode) or `/chunkunclaim` (chunk mode) while standing in a claim to delete it and recover blocks.
- Right-click inside an existing claim with the shovel (shovel mode) to see its boundary again.
- Claims must be at least 5x5 blocks (shovel mode only; chunks are always 16x16).

## Commands

### Player Commands

| Command | Description | Mode |
|---|---|---|
| `/trust <player>` | Grant a player access to the claim you're standing in. | Both |
| `/untrust <player>` | Revoke a player's access to the claim you're standing in. | Both |
| `/unclaim` | Delete the claim you're standing in. Blocks are returned to you. | Both |
| `/unallclaim` | Delete all of your claims at once. | Both |
| `/claimexplosion` | Toggle explosions on or off in the claim you're standing in. | Both |
| `/buyclaimblocks <amount>` | Purchase claim blocks with server currency. Requires Vault. | Both |
| `/sellclaimblocks <amount>` | Sell claim blocks for server currency. Requires Vault. | Both |
| `/chunkclaim` | Claim the chunk you're standing in. | Chunk |
| `/chunkunclaim` | Unclaim the chunk you're standing in. | Chunk |
| `/allayclaims` | Show plugin info, version, and credits. | Both |

### Admin Commands

| Command | Description |
|---|---|
| `/bbclaim delete <player>` | Delete all of a player's claims. |
| `/bbclaim give <player> <blocks>` | Give a player claim blocks. |
| `/bbclaim take <player> <blocks>` | Take claim blocks from a player. |
| `/bbclaim ignore` | Info on admin bypass (operators bypass all claim protection). |

Admin commands require **operator status** (`/op`). No separate permission node is needed — if you're an op, you can use them. Operators also bypass all claim protection automatically, meaning they can build and break anywhere regardless of claims.

## Configuration

All settings live in `plugins/AllayClaims/config.yml`:

```yaml
# Claim mode: "shovel" (default) or "chunk".
# When chunk mode is enabled, the golden shovel claim method is disabled
# and players claim whole chunks instead.
claim-mode: shovel

max-claim-blocks: 10000          # Maximum claim blocks a player can hold
starting-claim-blocks: 1000      # Blocks given to new players
blocks-per-hour: 100             # Reserved for future auto-accrual

# Chunk mode: cost (in claim blocks) per chunk claimed.
chunk-cost: 256

economy:
  enabled: true
  buy-price: 1.0                  # Cost per claim block when buying
  sell-price: 0.5                 # Money per claim block when selling

min-claim-width: 5               # Minimum claim width (shovel mode)
min-claim-height: 5              # Minimum claim depth (shovel mode)

claim-tool: GOLDEN_SHOVEL        # Tool used to create claims (shovel mode)
visualization-block: GLOWSTONE   # Block shown for the claim frame
visualization-duration: 30       # Seconds the frame stays visible

claimable-worlds:                 # Worlds where claiming is allowed
  - world
  - world_nether
  - world_the_end
unclaimable-worlds: []            # Worlds where claiming is blocked

explosions-enabled-by-default: false
block-explosions-in-unclaimed: true

allow-pistons-unclaimed: true     # Pistons work freely outside claims
allow-redstone-unclaimed: true   # Redstone works freely outside claims
```

### Messages

Every player-facing string is in `plugins/AllayClaims/messages.yml`. Use `&` color codes and `{placeholder}` variables. Edit and reload — no restart needed for message changes.

## Placeholders

Requires [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/).

| Placeholder | Description |
|---|---|
| `%allayclaims_claim_block%` | The player's remaining claim blocks (total minus blocks used by claims). |

## Strengths

- **Two claim modes.** Switch between freeform golden-shovel claiming and grid-aligned chunk claiming with one config line.
- **Simple to use.** Two clicks with a shovel, or one command for a chunk. No learning curve.
- **Lightweight.** No database, no web interface, no heavy dependencies. Just YAML storage and a small jar.
- **Clean visualization.** The flat frame shows boundaries without cluttering the screen.
- **Fully translatable.** Every message is editable in one file. Full color code support.
- **Economy integration.** Optional Vault support lets players trade money for claim space.
- **Redstone and piston friendly.** Unclaimed areas keep full redstone and piston functionality.
- **Per-world granularity.** Claiming can be enabled or disabled per world.
- **Simple admin model.** Operator-only admin commands mean no permission node setup.

## Weaknesses

- **No subclaims.** All claims are top-level. There's no hierarchy for subdividing into zones with different trust levels.
- **No claim resizing.** To change a shovel claim's size, delete and recreate it. Chunks are always 16x16.
- **2D claims only.** Claims cover a full-height column from bedrock to sky. No vertical limits.
- **YAML storage.** Data is stored in a flat YAML file. Very large servers with thousands of claims may see slower I/O compared to a database-backed plugin.
- **No automatic block accrual.** The `blocks-per-hour` setting is reserved for future use.
- **Single trust tier.** `/trust` grants full build access. No permission granularity (e.g., container-only).
- **Op-only admin.** Admin access is tied to operator status. Servers using fine-grained permission systems cannot grant admin tools to non-op staff.
- **Mutually exclusive modes.** Only one claim mode is active at a time. You cannot create shovel claims and chunk claims simultaneously in the same session (though existing claims from the other mode persist).

## License

MIT License. Copyright (c) 2026 AllayMC.

See [LICENSE](LICENSE) for full text.

## Credits

**Author:** AllayMC  
**Project:** [Modrinth](https://modrinth.com/user/AllayMC)  
**Inspired by:** GriefPrevention (golden-shovel), NClaims and Towny (chunk-based claiming).

---

<div align="center">

*AllayClaims — protect your builds, keep it simple.*

</div>
