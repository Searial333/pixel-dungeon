/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.items;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.items.armor.ProceduralArmor;
import com.watabou.pixeldungeon.items.weapon.melee.ProceduralWeapon;
import com.watabou.utils.Random;

/**
 * ItemFactory - Procedural Item Generation System
 *
 * This system generates items dynamically by combining multiple components:
 * - Material: The core substance (Bronze, Iron, Steel, etc.)
 * - Quality: Craftsmanship level (Broken, Fine, Masterwork, etc.)
 * - Base Item: The item type (Sword, Helm, etc.)
 * - Prefix: Magical modifier (Vicious, Blazing, etc.)
 * - Suffix: Magical modifier (of the Titan, of Fortitude, etc.)
 *
 * Rarity determines component complexity:
 * - COMMON: Material + Quality + Base
 * - UNCOMMON: + Prefix OR Suffix
 * - RARE: + Both Prefix AND Suffix
 * - EPIC: Enhanced stats + Both modifiers
 * - LEGENDARY: Maximum stats + Both modifiers + special effects
 */
public class ItemFactory {

	// ============================================================================
	// RARITY SYSTEM
	// ============================================================================

	public static enum Rarity {
		COMMON		(70, 0x999999),  // Gray
		UNCOMMON	(20, 0x1EFF00),  // Green
		RARE		(8,  0x0070DD),  // Blue
		EPIC		(1,  0xA335EE),  // Purple
		LEGENDARY	(1,  0xFF8000);  // Orange

		public int chance;
		public int color;

		private Rarity(int chance, int color) {
			this.chance = chance;
			this.color = color;
		}

		public static Rarity random(int depth) {
			// Adjust probabilities based on dungeon depth
			int commonChance = Math.max(10, 70 - depth * 3);
			int uncommonChance = Math.min(40, 20 + depth * 2);
			int rareChance = Math.min(30, 8 + depth * 2);
			int epicChance = Math.min(15, 1 + depth);
			int legendaryChance = Math.min(5, Math.max(0, depth - 15));

			int roll = Random.Int(commonChance + uncommonChance + rareChance + epicChance + legendaryChance);

			if (roll < commonChance) return COMMON;
			roll -= commonChance;
			if (roll < uncommonChance) return UNCOMMON;
			roll -= uncommonChance;
			if (roll < rareChance) return RARE;
			roll -= rareChance;
			if (roll < epicChance) return EPIC;
			return LEGENDARY;
		}
	}

	// ============================================================================
	// MATERIAL SYSTEM
	// ============================================================================

	public static class Material {
		public String name;
		public int minDepth;
		public float statMultiplier;
		public int strModifier;

		public Material(String name, int minDepth, float statMultiplier, int strModifier) {
			this.name = name;
			this.minDepth = minDepth;
			this.statMultiplier = statMultiplier;
			this.strModifier = strModifier;
		}
	}

	public static final Material[] MATERIALS = {
		new Material("Wooden",      0,  0.6f,  -2),
		new Material("Copper",      0,  0.75f, -1),
		new Material("Bronze",      1,  0.9f,   0),
		new Material("Iron",        3,  1.0f,   0),
		new Material("Steel",       5,  1.15f,  1),
		new Material("Mithril",     8,  1.3f,   1),
		new Material("Adamantine", 12,  1.5f,   2),
		new Material("Orichalcum", 16,  1.7f,   3),
		new Material("Obsidian",   18,  1.9f,   3),
		new Material("Dragonbone", 20,  2.2f,   4),
		new Material("Ethereal",   23,  2.5f,   4),
		new Material("Celestial",  25,  3.0f,   5)
	};

	public static Material getMaterialForDepth(int depth) {
		Material selected = MATERIALS[0];
		for (Material mat : MATERIALS) {
			if (mat.minDepth <= depth && Random.Int(3) != 0) {
				selected = mat;
			} else if (mat.minDepth > depth) {
				break;
			}
		}
		return selected;
	}

	// ============================================================================
	// QUALITY SYSTEM
	// ============================================================================

	public static class Quality {
		public String name;
		public float statMultiplier;
		public int levelModifier;
		public int strModifier;
		public boolean cursed;

		public Quality(String name, float statMultiplier, int levelModifier, int strModifier, boolean cursed) {
			this.name = name;
			this.statMultiplier = statMultiplier;
			this.levelModifier = levelModifier;
			this.strModifier = strModifier;
			this.cursed = cursed;
		}
	}

	public static final Quality[] QUALITIES = {
		new Quality("Broken",      0.6f,  -3, 2, true),
		new Quality("Worn",        0.8f,  -1, 1, false),
		new Quality("Standard",    1.0f,   0, 0, false),
		new Quality("Fine",        1.1f,   1, 0, false),
		new Quality("Superior",    1.25f,  2, -1, false),
		new Quality("Masterwork",  1.4f,   3, -1, false),
		new Quality("Legendary",   1.6f,   4, -2, false),
		new Quality("Artifact",    2.0f,   5, -2, false)
	};

	public static Quality getRandomQuality(Rarity rarity) {
		int maxIndex;
		switch (rarity) {
			case COMMON:    maxIndex = 3; break; // Up to Fine
			case UNCOMMON:  maxIndex = 5; break; // Up to Masterwork
			case RARE:      maxIndex = 6; break; // Up to Legendary
			default:        maxIndex = 7; break; // All qualities
		}

		// Weighted towards middle qualities
		int index = Random.Int(maxIndex + 1);
		if (index < 2 && Random.Int(2) == 0) {
			index = 2; // Standard is more common
		}

		return QUALITIES[Math.min(index, maxIndex)];
	}

	// ============================================================================
	// WEAPON BASE TYPES
	// ============================================================================

	public static class WeaponBase {
		public String name;
		public int tier;
		public float accuracy;
		public float speed;
		public int sprite;

		public WeaponBase(String name, int tier, float accuracy, float speed, int sprite) {
			this.name = name;
			this.tier = tier;
			this.accuracy = accuracy;
			this.speed = speed;
			this.sprite = sprite;
		}
	}

	public static final WeaponBase[] WEAPON_BASES = {
		new WeaponBase("Dagger",      1, 1.2f, 0.8f, 12),
		new WeaponBase("Knuckles",    1, 1.0f, 0.5f, 13),
		new WeaponBase("Shortsword",  2, 1.0f, 1.0f, 14),
		new WeaponBase("Spear",       2, 1.0f, 1.0f, 19),
		new WeaponBase("Mace",        3, 1.0f, 1.0f, 16),
		new WeaponBase("Sword",       3, 1.0f, 1.0f, 15),
		new WeaponBase("Longsword",   4, 1.0f, 1.1f, 17),
		new WeaponBase("Battle Axe",  4, 0.8f, 1.4f, 18),
		new WeaponBase("War Hammer",  5, 0.8f, 1.4f, 20),
		new WeaponBase("Glaive",      5, 1.0f, 1.5f, 21),
		new WeaponBase("Greatsword",  5, 0.9f, 1.3f, 17)
	};

	public static WeaponBase getWeaponBaseForDepth(int depth) {
		int maxTier = Math.min(5, 1 + depth / 5);
		WeaponBase selected = WEAPON_BASES[0];

		for (WeaponBase base : WEAPON_BASES) {
			if (base.tier <= maxTier) {
				selected = base;
				if (Random.Int(3) == 0) {
					break; // Sometimes pick earlier tier
				}
			} else {
				break;
			}
		}

		return selected;
	}

	// ============================================================================
	// ARMOR BASE TYPES
	// ============================================================================

	public static class ArmorBase {
		public String name;
		public int tier;
		public int sprite;

		public ArmorBase(String name, int tier, int sprite) {
			this.name = name;
			this.tier = tier;
			this.sprite = sprite;
		}
	}

	public static final ArmorBase[] ARMOR_BASES = {
		new ArmorBase("Cloth",       1, 6),
		new ArmorBase("Leather",     2, 7),
		new ArmorBase("Mail",        3, 8),
		new ArmorBase("Scale",       4, 9),
		new ArmorBase("Plate",       5, 10),
		new ArmorBase("Helm",        2, 7),
		new ArmorBase("Gauntlets",   2, 7),
		new ArmorBase("Boots",       2, 7),
		new ArmorBase("Shield",      3, 8)
	};

	public static ArmorBase getArmorBaseForDepth(int depth) {
		int maxTier = Math.min(5, 1 + depth / 5);
		ArmorBase selected = ARMOR_BASES[0];

		for (ArmorBase base : ARMOR_BASES) {
			if (base.tier <= maxTier) {
				selected = base;
				if (Random.Int(3) == 0) {
					break;
				}
			}
		}

		return selected;
	}

	// ============================================================================
	// PREFIX SYSTEM (Magical Modifiers)
	// ============================================================================

	public static class Prefix {
		public String name;
		public String[] statBonus; // Description of stat bonuses
		public float damageMultiplier;
		public int bonusSTR;
		public int bonusAccuracy;

		public Prefix(String name, String[] statBonus, float damageMultiplier, int bonusSTR, int bonusAccuracy) {
			this.name = name;
			this.statBonus = statBonus;
			this.damageMultiplier = damageMultiplier;
			this.bonusSTR = bonusSTR;
			this.bonusAccuracy = bonusAccuracy;
		}
	}

	public static final Prefix[] PREFIXES = {
		new Prefix("Vicious",    new String[]{"+Damage"}, 1.15f, 0, 0),
		new Prefix("Deadly",     new String[]{"+Damage"}, 1.25f, 1, 0),
		new Prefix("Brutal",     new String[]{"+Damage"}, 1.35f, 1, -1),
		new Prefix("Sharp",      new String[]{"+Accuracy"}, 1.0f, 0, 2),
		new Prefix("Keen",       new String[]{"+Accuracy", "+Damage"}, 1.1f, 0, 1),
		new Prefix("Blazing",    new String[]{"Fire Damage"}, 1.2f, 0, 0),
		new Prefix("Frozen",     new String[]{"Ice Damage"}, 1.2f, 0, 0),
		new Prefix("Shocking",   new String[]{"Lightning Damage"}, 1.2f, 0, 0),
		new Prefix("Vampiric",   new String[]{"Life Steal"}, 1.1f, 0, 0),
		new Prefix("Swift",      new String[]{"+Speed"}, 1.0f, -1, 0),
		new Prefix("Jagged",     new String[]{"Bleed Effect"}, 1.15f, 0, 0),
		new Prefix("Heavy",      new String[]{"+Damage", "-Speed"}, 1.3f, 2, 0),
		new Prefix("Blessed",    new String[]{"Holy Damage"}, 1.2f, 0, 1),
		new Prefix("Cursed",     new String[]{"Dark Damage"}, 1.3f, 0, 0),
		new Prefix("Unstable",   new String[]{"Chaos Damage"}, 1.4f, 1, -1)
	};

	public static Prefix getRandomPrefix() {
		return PREFIXES[Random.Int(PREFIXES.length)];
	}

	// ============================================================================
	// SUFFIX SYSTEM (Magical Modifiers)
	// ============================================================================

	public static class Suffix {
		public String name;
		public String[] statBonus;
		public int bonusHealth;
		public int bonusDefense;
		public int bonusSTR;

		public Suffix(String name, String[] statBonus, int bonusHealth, int bonusDefense, int bonusSTR) {
			this.name = name;
			this.statBonus = statBonus;
			this.bonusHealth = bonusHealth;
			this.bonusDefense = bonusDefense;
			this.bonusSTR = bonusSTR;
		}
	}

	public static final Suffix[] SUFFIXES = {
		new Suffix("of the Titan",     new String[]{"+Strength"}, 0, 0, 2),
		new Suffix("of Fortitude",     new String[]{"+Health"}, 5, 0, 0),
		new Suffix("of Protection",    new String[]{"+Defense"}, 0, 2, 0),
		new Suffix("of Power",         new String[]{"+Damage"}, 0, 0, 1),
		new Suffix("of the Bear",      new String[]{"+Health", "+STR"}, 3, 0, 1),
		new Suffix("of the Eagle",     new String[]{"+Accuracy"}, 0, 0, 0),
		new Suffix("of the Cheetah",   new String[]{"+Speed"}, 0, 0, -1),
		new Suffix("of Carnage",       new String[]{"+Critical"}, 0, 0, 0),
		new Suffix("of Shielding",     new String[]{"+Defense", "+Health"}, 2, 1, 0),
		new Suffix("of the Colossus",  new String[]{"+All Stats"}, 3, 1, 1),
		new Suffix("of the Phoenix",   new String[]{"Resurrection"}, 5, 0, 0),
		new Suffix("of the Void",      new String[]{"Void Damage"}, 0, 0, 1),
		new Suffix("of Dominance",     new String[]{"+All Combat"}, 0, 1, 1),
		new Suffix("of the Dragon",    new String[]{"+Damage", "+Defense"}, 0, 2, 1),
		new Suffix("of Eternity",      new String[]{"Unbreakable"}, 0, 0, 0)
	};

	public static Suffix getRandomSuffix() {
		return SUFFIXES[Random.Int(SUFFIXES.length)];
	}

	// ============================================================================
	// PROCEDURAL GENERATION FUNCTIONS
	// ============================================================================

	/**
	 * Generate a procedural weapon based on dungeon depth
	 */
	public static ProceduralWeapon generateProceduralWeapon(int depth) {
		Rarity rarity = Rarity.random(depth);
		Material material = getMaterialForDepth(depth);
		Quality quality = getRandomQuality(rarity);
		WeaponBase base = getWeaponBaseForDepth(depth);

		Prefix prefix = null;
		Suffix suffix = null;

		// Determine if item has prefix/suffix based on rarity
		switch (rarity) {
			case COMMON:
				// No prefix/suffix
				break;
			case UNCOMMON:
				// Either prefix or suffix
				if (Random.Int(2) == 0) {
					prefix = getRandomPrefix();
				} else {
					suffix = getRandomSuffix();
				}
				break;
			case RARE:
			case EPIC:
			case LEGENDARY:
				// Both prefix and suffix
				prefix = getRandomPrefix();
				suffix = getRandomSuffix();
				break;
		}

		return new ProceduralWeapon(material, quality, base, prefix, suffix, rarity);
	}

	/**
	 * Generate a procedural armor based on dungeon depth
	 */
	public static ProceduralArmor generateProceduralArmor(int depth) {
		Rarity rarity = Rarity.random(depth);
		Material material = getMaterialForDepth(depth);
		Quality quality = getRandomQuality(rarity);
		ArmorBase base = getArmorBaseForDepth(depth);

		Prefix prefix = null;
		Suffix suffix = null;

		// Determine if item has prefix/suffix based on rarity
		switch (rarity) {
			case COMMON:
				// No prefix/suffix
				break;
			case UNCOMMON:
				// Either prefix or suffix
				if (Random.Int(2) == 0) {
					prefix = getRandomPrefix();
				} else {
					suffix = getRandomSuffix();
				}
				break;
			case RARE:
			case EPIC:
			case LEGENDARY:
				// Both prefix and suffix
				prefix = getRandomPrefix();
				suffix = getRandomSuffix();
				break;
		}

		return new ProceduralArmor(material, quality, base, prefix, suffix, rarity);
	}

	/**
	 * Generate a basic starting weapon
	 */
	public static ProceduralWeapon generateStartingWeapon() {
		Material material = MATERIALS[0]; // Wooden
		Quality quality = QUALITIES[2];    // Standard
		WeaponBase base = WEAPON_BASES[0]; // Dagger

		return new ProceduralWeapon(material, quality, base, null, null, Rarity.COMMON);
	}

	/**
	 * Generate a basic starting armor
	 */
	public static ProceduralArmor generateStartingArmor() {
		Material material = MATERIALS[0]; // Wooden
		Quality quality = QUALITIES[2];    // Standard
		ArmorBase base = ARMOR_BASES[0];   // Cloth

		return new ProceduralArmor(material, quality, base, null, null, Rarity.COMMON);
	}
}
