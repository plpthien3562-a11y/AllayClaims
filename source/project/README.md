# AllayClaims Plugin

A comprehensive land claiming plugin for Spigot/Bukkit servers.

## Features

- **Land Claims**: Players can claim land to protect it from other players
- **Claim Management**: Easy commands to manage claims
- **Trusted Players**: Trust other players to build in your claim
- **Economy Integration**: Buy and sell claim blocks
- **Explosion Protection**: Prevent explosions in claims
- **Visual Indicators**: See your claim boundaries
- **Player Data**: Track claim blocks and claims per player

## Commands

- `/claim` - Create a claim
- `/unclaim` - Unclaim land
- `/trust <player>` - Trust a player
- `/untrust <player>` - Remove trust from a player
- `/buyclaimblocks <amount>` - Buy claim blocks
- `/sellclaimblocks <amount>` - Sell claim blocks
- `/info` - Get information about your claims
- `/uc` - Unclaim all your claims
- `/unallclaim` - Remove all claims (admin)
- `/admin` - Admin commands

## Installation

1. Download the latest JAR file
2. Place it in your server's `plugins` folder
3. Restart your server
4. Configure the `config.yml` as needed

## Configuration

Edit `plugins/AllayClaims/config.yml` to customize:
- Number of default claim blocks
- Price per claim block
- Maximum number of claims per player
- Protection settings

## License

MIT License