# Procedural Item Generation System

## Overview

This document describes the comprehensive procedural item generation system that has been implemented for Pixel Dungeon. This system transforms the game's loot experience from static, predefined items into a dynamic, virtually infinite variety of unique equipment inspired by games like Diablo and Borderlands.

## System Architecture

### Core Components

The procedural generation system consists of three main classes:

1. **ItemFactory.java** - The central factory that generates procedural items
2. **ProceduralWeapon.java** - Dynamic weapon class
3. **ProceduralArmor.java** - Dynamic armor class

### How It Works

Every procedural item is constructed from up to **five components**:

#### 1. Material
The core substance of the item that determines base stat potential.

Examples:
- Wooden (0.6x stats, -2 STR)
- Iron (1.0x stats, 0 STR)
- Steel (1.15x stats, +1 STR)
- Mithril (1.3x stats, +1 STR)
- Adamantine (1.5x stats, +2 STR)
- Dragonbone (2.2x stats, +4 STR)
- Celestial (3.0x stats, +5 STR)

**Scaling:** Better materials unlock as you descend deeper into the dungeon.

#### 2. Quality
A modifier that affects overall craftsmanship and provides stat tweaks.

Examples:
- Broken (0.6x stats, -3 levels, +2 STR, **Cursed**)
- Worn (0.8x stats, -1 level, +1 STR)
- Standard (1.0x stats, no modifiers)
- Fine (1.1x stats, +1 level)
- Superior (1.25x stats, +2 levels, -1 STR)
- Masterwork (1.4x stats, +3 levels, -1 STR)
- Legendary (1.6x stats, +4 levels, -2 STR)
- Artifact (2.0x stats, +5 levels, -2 STR)

#### 3. Base Item
The core item type.

**Weapon Bases:**
- Dagger (Tier 1)
- Shortsword (Tier 2)
- Sword, Mace (Tier 3)
- Longsword, Battle Axe (Tier 4)
- War Hammer, Glaive, Greatsword (Tier 5)

**Armor Bases:**
- Cloth (Tier 1)
- Leather (Tier 2)
- Mail (Tier 3)
- Scale (Tier 4)
- Plate (Tier 5)
- Helm, Gauntlets, Boots, Shield (Tier 2-3)

#### 4. Prefix (Optional)
A magical enchantment that adds stats and a descriptor to the front of the name.

Examples:
- **Vicious** (+15% damage)
- **Deadly** (+25% damage, +1 STR)
- **Blazing** (+20% damage, Fire damage)
- **Swift** (-1 STR, +Speed)
- **Vampiric** (Life steal effect)
- **Blessed** (+20% damage, +1 accuracy, Holy damage)

#### 5. Suffix (Optional)
Another magical enchantment that adds stats and a descriptor to the end of the name.

Examples:
- **of the Titan** (+2 Strength)
- **of Fortitude** (+5 Health)
- **of Protection** (+2 Defense)
- **of the Bear** (+3 Health, +1 STR)
- **of the Dragon** (+2 Defense, +1 STR)
- **of Eternity** (Unbreakable)

## Rarity System

The rarity of an item determines its component complexity:

### Rarity Tiers

| Rarity | Color | Chance | Components |
|--------|-------|--------|------------|
| **Common** | Gray (0x999999) | 70% | Material + Quality + Base |
| **Uncommon** | Green (0x1EFF00) | 20% | + Either Prefix OR Suffix |
| **Rare** | Blue (0x0070DD) | 8% | + Both Prefix AND Suffix |
| **Epic** | Purple (0xA335EE) | 1% | Enhanced stats + Both modifiers |
| **Legendary** | Orange (0xFF8000) | 1% | Maximum stats + Both modifiers |

**Dynamic Scaling:** Rarity chances improve with dungeon depth. At deeper levels, you're more likely to find rare and legendary items.

## Example Generated Items

Here are examples of items the system can create:

### Common Items
- "wooden standard dagger"
- "bronze fine leather"
- "iron cloth"

### Uncommon Items
- "vicious steel sword"
- "superior mithril mail of protection"
- "keen iron longsword"

### Rare Items
- "deadly adamantine greatsword of the titan"
- "blazing steel battle axe of carnage"
- "superior mithril plate of the dragon"

### Epic/Legendary Items
- "blessed dragonbone glaive of the colossus"
- "deadly celestial greatsword of dominance"
- "legendary ethereal plate of eternity"

## Depth Scaling

The system automatically scales with dungeon depth:

- **Depth 1-5:** Wooden, Copper, Bronze materials; Tier 1-2 items
- **Depth 6-10:** Iron, Steel materials; Tier 2-3 items
- **Depth 11-15:** Mithril, Adamantine; Tier 3-4 items; Rare items more common
- **Depth 16-20:** Orichalcum, Obsidian; Tier 4-5 items
- **Depth 21+:** Dragonbone, Ethereal, Celestial; Maximum tier items; Epic/Legendary possible

## Visual Indicators

Items of Rare quality and above display a glowing effect:

- **Rare:** Blue glow
- **Epic:** Purple glow
- **Legendary:** Orange glow

## Price Calculation

Item prices are calculated based on:
- Base tier price
- Material multiplier
- Rarity multiplier (1.5x Uncommon, 2.5x Rare, 4x Epic, 10x Legendary)
- Quality and upgrade level

## Technical Details

### Data Organization

All component data is organized in "spreadsheet-like" arrays in `ItemFactory.java`:

```java
public static final Material[] MATERIALS = { ... };
public static final Quality[] QUALITIES = { ... };
public static final WeaponBase[] WEAPON_BASES = { ... };
public static final ArmorBase[] ARMOR_BASES = { ... };
public static final Prefix[] PREFIXES = { ... };
public static final Suffix[] SUFFIXES = { ... };
```

This makes it incredibly easy to add new content by simply adding entries to these arrays.

### Adding New Components

To add a new material:

```java
new Material("Titanium", 10, 1.4f, 1)
// name, minDepth, statMultiplier, strModifier
```

To add a new prefix:

```java
new Prefix("Radiant", new String[]{"+Holy Damage", "+Light"}, 1.2f, 0, 1)
// name, statBonus descriptions, damageMultiplier, bonusSTR, bonusAccuracy
```

### Integration Points

The system integrates with existing game systems through:

1. **Generator.java** - `randomWeapon()` and `randomArmor()` now call `ItemFactory.generateProceduralWeapon()` and `ItemFactory.generateProceduralArmor()`

2. **Dungeon Generation** - Procedural items are automatically spawned in dungeons through the existing loot generation system

3. **Serialization** - Full save/load support through Bundle serialization

## Starting Equipment

For starting equipment, use:

```java
// Generate basic starting weapon
ProceduralWeapon startWeapon = ItemFactory.generateStartingWeapon();

// Generate basic starting armor
ProceduralArmor startArmor = ItemFactory.generateStartingArmor();
```

These generate simple Wooden/Standard quality items appropriate for beginning characters.

## Future Expansion Ideas

The system is designed to be easily expandable:

1. **More Materials:** Add exotic materials like Starstone, Voidmetal, etc.
2. **Set Items:** Create matching sets with bonus effects
3. **Legendary Effects:** Add unique proc effects to legendary items
4. **Socketing System:** Allow gems to be inserted into items
5. **Crafting:** Let players combine materials to create custom items
6. **Transmutation:** Convert items while preserving some properties

## Performance Considerations

- Item generation is fast - components are simple data structures
- No runtime reflection - all classes use direct constructors
- Serialization stores indices, not full objects, keeping save files small
- Component arrays are static and initialized once at class load

## Compatibility

The procedural system:
- ✅ Works with existing enchantment system
- ✅ Compatible with upgrade/degrade mechanics
- ✅ Integrates with curse system
- ✅ Supports all existing item operations (drop, throw, equip, etc.)
- ✅ Full save/load compatibility

## Summary

This procedural item generation system transforms loot from a static experience into an exciting, dynamic aspect of gameplay where every item drop has the potential to be something special and unique. The system scales naturally with game progression and provides endless variety while maintaining balance and game feel.

The spreadsheet-like data organization makes future content additions trivial - just add entries to the arrays in `ItemFactory.java`!
