# Teddy Bear Guy: Bottlecap Hollow

A playable single-file HTML5 Canvas prototype for an **RPOG platforming Teddy Bear Guy game**.

This is the first real vertical slice: movement, jumping, dash, guard, yo-yo combat, enemies, XP, leveling, pickups, NPC rescue, gate win condition, and browser save/load.

## Play

Open `index.html` in a modern browser.

## Controls

| Input | Action |
|---|---|
| `A / D` or arrow keys | Move |
| `W`, `Space`, or up arrow | Jump |
| `J` | Yo-yo attack |
| `K` | Guard |
| `Shift` | Dash |
| `E` | Talk / open gate |
| `R` | Restart |

## Current loop

1. Explore Bottlecap Hollow.
2. Collect at least 3 bottlecaps.
3. Defeat enemies using stomp attacks or the yo-yo.
4. Rescue Moxie.
5. Reach the nursery gate and press `E` to clear the prototype.

## Systems implemented

- Side-scrolling platformer physics
- Scrolling level wider than the viewport
- Procedural pixel-art player, enemies, NPC, platforms, pickups, and gate
- Teddy Bear Guy purple-mask sprite read
- Yo-yo combat hitbox
- Enemy collision and stomp defeats
- XP and level progression
- Stamina dash and guard
- Bottlecap quest counter
- Moxie NPC rescue requirement
- LocalStorage save/load/wipe

## Next build targets

- Replace procedural art with the real sprite sheets and animation frames
- Add controller/gamepad support
- Split the single file into `src/` modules once the loop stabilizes
- Add tilemap JSON loading and a level editor
- Build boss fights, equipment, charms, plush companions, and zone transitions

Tone target: cute but dangerous. A tiny masked bear in a cursed Saturday-morning platformer RPG, carrying a yo-yo and somehow more product direction than most corporate roadmaps. Humanity remains under review.
