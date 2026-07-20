<div align="center">

# AllayClaims

**A lightweight, gold-shovel-based land-claim plugin for Spigot/Paper 1.20+.**

Protect what's yours — simply, intuitively, and without the bloat.

[Features](#features) · [Installation](#installation) · [How to Claim](#how-to-claim) · [Commands](#commands) · [Configuration](#configuration) · [Placeholders](#placeholders)

</div>

---

## Overview

AllayClaims is a minimalist land-claim plugin inspired by GriefPrevention's gold-shovel mechanic. Players protect their builds by selecting two corners with a golden shovel — no database, no web UI, no complex subclaim hierarchy. Just claim, build, and trust.

Designed for servers that want core grief protection without the overhead of a full-featured land-management suite.

## Features

- **Golden Shovel Claiming** — Select two corners with a golden shovel to create a rectangular claim. A visual frame appears so you can see exactly what's protected.
- **Flat Frame Visualization** — Claims are shown as a single-layer rectangular outline on the ground (not a 3D box), keeping the view clean and unobtrusive.
- **Trust System** — Grant specific players access to your claims with `/trust`. Revoke anytime with `/untrust`.
- **Claim Block Economy** — Each player starts with a pool of claim blocks. Larger claims cost more blocks. Buy and sell blocks with `/buyclaimblocks` and `/sellclaimblocks` (requires Vault).
- **Explosion Toggle** — Claim owners can enable or disable explosions within their claim using `/claimexplosion`.
- **Piston & Redstone Freedom** — Pistons and redstone work freely in unclaimed areas by default. Inside claims, pistons cannot push blocks across claim boundaries.
- **Admin Tools** — Server operators can manage claims with `/bbclaim`: delete a player's claims, give or take claim blocks, and toggle admin bypass.
- **Entry Notifications** — A title appears when you enter a claim, showing whose claim it is.
- **100% Translatable Messages** — Every message lives in `messages.yml` and supports color codes and placeholders.
- **PlaceholderAPI Support** — Exposes the `%allayclaims_claim_block%` placeholder for remaining claim blocks.
- **Per-World Control** — Configure which worlds allow claiming and which don't.
- **Data Persistence** — Claims and player data are saved to a simple YAML file. No external database required.

## Installation

1. Download the `AllayClaims.jar` from this
2. Place the jar in your server's `plugins/` folder.
3. (Optional) Install [Vault](https://www.spigotmc.org/resources/vault.34315/) and an economy plugin to enable buying and selling claim blocks.
4. (Optional) Install [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) to use the `%allayclaims_claim_block%` placeholder.
5. Restart your server.
6. Edit `plugins/AllayClaims/config.yml` and `messages.yml` to your liking.
7. Reload or restart.

**Requirements:**
- Java 21 or higher
- Spigot/Paper 1.20.6+ (api-version 1.20)

## How to Claim

1. **Equip a golden shovel.** This is your claim tool.
2. **Right-click the ground** at the first corner of the area you want to protect. You'll see a confirmation message with the coordinates and a single-block visual marker.
3. **Right-click the ground** at the opposite corner. The claim is created instantly. A glowing rectangular frame appears on the ground showing the claim boundary, and you'll see how many claim blocks you have remaining.

**That's it.** Everything inside the rectangle is now protected — other players cannot break, place, or interact with blocks in your claim.

### Tips

- Claims must be at least 5x5 blocks (configurable).
- You need enough claim blocks to cover the area. Check your balance with `%allayclaims_claim_block%` (PlaceholderAPI) or by attempting to create a claim.
- Right-click inside an existing claim with the shovel to see its boundary again.
- Use `/unclaim` while standing in a claim to delete it and recover the blocks.

## Commands

### Player Commands

| Command | Description |
|---|---|
| `/trust <player>` | Grant a player access to the claim you're standing in. |
| `/untrust <player>` | Revoke a player's access to the claim you're standing in. |
| `/unclaim` | Delete the claim you're standing in. Blocks are returned to you. |
| `/unallclaim` | Delete all of your claims at once. |
| `/claimexplosion` | Toggle explosions on or off in the claim you're standing in. |
| `/buyclaimblocks <amount>` | Purchase claim blocks with server currency. Requires Vault. |
| `/sellclaimblocks <amount>` | Sell claim blocks for server currency. Requires Vault. |
| `/allayclaims` | Show plugin info, version, and credits. |

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
max-claim-blocks: 10000          # Maximum claim blocks a player can hold
starting-claim-blocks: 1000      # Blocks given to new players
blocks-per-hour: 100             # Reserved for future auto-accrual

economy:
  enabled: true
  buy-price: 1.0                  # Cost per claim block when buying
  sell-price: 0.5                 # Money per claim block when selling

min-claim-width: 5               # Minimum claim width
min-claim-height: 5              # Minimum claim depth (Z axis)

claim-tool: GOLDEN_SHOVEL        # Tool used to create claims
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

Every player-facing string is in `plugins/AllayClaims/messages.yml`. Use `&` color codes and `{placeholder}` variables. Edit and reload — no restart needed for message changes (after a `/reload` or plugin reload).

## Placeholders

Requires [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/).

| Placeholder | Description |
|---|---|
| `%allayclaims_claim_block%` | The player's remaining claim blocks (total minus blocks used by claims). |

## Strengths

- **Simple to use.** Two clicks with a shovel and you're protected. No learning curve, no tutorials needed.
- **Lightweight.** No database, no web interface, no heavy dependencies. Just YAML storage and a small jar.
- **Clean visualization.** The flat frame shows boundaries without cluttering the screen with 3D pillars or walls.
- **Fully translatable.** Every message is editable in one file. Full color code support.
- **Economy integration.** Optional Vault support lets players trade money for claim space.
- **Redstone and piston friendly.** Unclaimed areas keep full redstone and piston functionality. Claims only restrict cross-boundary piston movement.
- **Per-world granularity.** Claiming can be enabled or disabled per world.
- **Simple admin model.** Operator-only admin commands mean no permission node setup. Op your trusted staff and they're ready to manage claims.

## Weaknesses

- **No subclaims.** All claims are top-level rectangles. There's no hierarchy for subdividing a claim into zones with different trust levels.
- **No claim resizing.** To change a claim's size, you must delete it (`/unclaim`) and recreate it. There is no corner-drag resize.
- **2D claims only.** Claims cover a full-height column from bedrock to sky. You cannot limit vertical extent.
- **YAML storage.** Data is stored in a flat YAML file. Very large servers with thousands of claims may see slower I/O compared to a database-backed plugin.
- **No automatic block accrual.** The `blocks-per-hour` setting is reserved for future use. Players currently start with a fixed pool and must buy more if needed.
- **Single trust tier.** `/trust` grants full build access. There's no permission granularity (e.g., container-only, door-only).
- **Op-only admin.** Admin access is tied to operator status. Servers that use fine-grained permission systems (LuckPerms, etc.) cannot grant admin claim tools to non-op staff.

## License

MIT License. Copyright (c) 2026 AllayMC.

See [LICENSE](LICENSE) for full text.

## Credits

**Author:** AllayMC  
**Project:** [Modrinth](https://modrinth.com/user/AllayMC)  
**Inspired by:** GriefPrevention's gold-shovel claiming mechanic.

---

<div align="center">

*AllayClaims — protect your builds, keep it simple.*

</div>
