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
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.ItemFactory;
import com.watabou.pixeldungeon.sprites.ItemSprite;
import com.watabou.pixeldungeon.utils.Utils;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

/**
 * ProceduralArmor - Dynamically generated armor
 *
 * This armor is constructed from multiple components defined in ItemFactory:
 * - Material (e.g., Iron, Steel, Mithril)
 * - Quality (e.g., Fine, Superior, Masterwork)
 * - Base armor type (e.g., Leather, Plate)
 * - Optional Prefix (e.g., Reinforced, Blessed)
 * - Optional Suffix (e.g., of Protection, of the Titan)
 *
 * The name is assembled dynamically, like: "Superior Steel Plate of Fortitude"
 */
public class ProceduralArmor extends Armor {

	private ItemFactory.Material material;
	private ItemFactory.Quality quality;
	private ItemFactory.ArmorBase base;
	private ItemFactory.Prefix prefix;
	private ItemFactory.Suffix suffix;
	private ItemFactory.Rarity rarity;

	// For serialization
	private static final String MATERIAL = "material";
	private static final String QUALITY = "quality";
	private static final String BASE = "base";
	private static final String PREFIX = "prefix";
	private static final String SUFFIX = "suffix";
	private static final String RARITY = "rarity";

	// Default constructor for deserialization
	public ProceduralArmor() {
		this(ItemFactory.MATERIALS[2],
		     ItemFactory.QUALITIES[2],
		     ItemFactory.ARMOR_BASES[0],
		     null, null, ItemFactory.Rarity.COMMON);
	}

	public ProceduralArmor(ItemFactory.Material material,
	                       ItemFactory.Quality quality,
	                       ItemFactory.ArmorBase base,
	                       ItemFactory.Prefix prefix,
	                       ItemFactory.Suffix suffix,
	                       ItemFactory.Rarity rarity) {
		super(base.tier);

		this.material = material;
		this.quality = quality;
		this.base = base;
		this.prefix = prefix;
		this.suffix = suffix;
		this.rarity = rarity;

		// Set the image based on base armor
		this.image = base.sprite;

		// Apply stat modifiers
		applyModifiers();

		// Generate the name
		this.name = generateName();
	}

	private void applyModifiers() {
		// Base STR requirement
		int baseSTR = typicalSTR();

		// Apply material modifier
		baseSTR += material.strModifier;

		// Apply quality modifier
		baseSTR += quality.strModifier;
		if (quality.levelModifier > 0) {
			upgrade(quality.levelModifier);
		} else if (quality.levelModifier < 0) {
			degrade(-quality.levelModifier);
		}

		// Apply suffix modifier (armor benefits more from defensive suffixes)
		if (suffix != null) {
			baseSTR += suffix.bonusSTR;
		}

		// Rarity bonus
		if (rarity == ItemFactory.Rarity.EPIC) {
			baseSTR -= 1;
		} else if (rarity == ItemFactory.Rarity.LEGENDARY) {
			baseSTR -= 2;
		}

		// Set the final STR requirement
		this.STR = Math.max(7, baseSTR);

		// Apply cursed status from quality
		if (quality.cursed) {
			this.cursed = true;
			this.cursedKnown = false;
		}
	}

	private String generateName() {
		StringBuilder name = new StringBuilder();

		// Add prefix if present
		if (prefix != null) {
			name.append(prefix.name).append(" ");
		}

		// Add quality if not "Standard"
		if (!quality.name.equals("Standard")) {
			name.append(quality.name).append(" ");
		}

		// Add material
		name.append(material.name).append(" ");

		// Add base armor name
		name.append(base.name);

		// Add suffix if present
		if (suffix != null) {
			name.append(" ").append(suffix.name);
		}

		// Convert to lowercase for consistency with game style
		return name.toString().toLowerCase();
	}

	@Override
	public int DR() {
		int base = super.DR();
		float multiplier = material.statMultiplier * quality.statMultiplier;

		// Suffix bonuses apply to armor defense
		if (suffix != null && suffix.bonusDefense > 0) {
			base += suffix.bonusDefense;
		}

		return (int)(base * multiplier);
	}

	@Override
	public String desc() {
		StringBuilder desc = new StringBuilder();

		// Base description
		desc.append("A ");
		if (prefix != null) {
			desc.append(prefix.name.toLowerCase()).append(" ");
		}
		desc.append(material.name.toLowerCase()).append(" ");
		desc.append(base.name.toLowerCase());
		if (suffix != null) {
			desc.append(" ").append(suffix.name.toLowerCase());
		}
		desc.append(". ");

		// Add quality description
		if (quality.cursed) {
			desc.append("It appears damaged and cursed. ");
		} else if (quality.name.equals("Masterwork")) {
			desc.append("This armor shows exceptional craftsmanship. ");
		} else if (quality.name.equals("Legendary") || quality.name.equals("Artifact")) {
			desc.append("This armor radiates protective power! ");
		}

		// Add material description
		if (material.statMultiplier >= 2.0f) {
			desc.append("The ").append(material.name.toLowerCase()).append(" construction makes it extraordinarily protective. ");
		} else if (material.statMultiplier >= 1.5f) {
			desc.append("The ").append(material.name.toLowerCase()).append(" material enhances its defense. ");
		}

		// Add prefix bonuses
		if (prefix != null && prefix.statBonus != null) {
			desc.append("Enhanced with: ");
			for (int i = 0; i < prefix.statBonus.length; i++) {
				desc.append(prefix.statBonus[i]);
				if (i < prefix.statBonus.length - 1) {
					desc.append(", ");
				}
			}
			desc.append(". ");
		}

		// Add suffix bonuses
		if (suffix != null && suffix.statBonus != null) {
			desc.append("Grants: ");
			for (int i = 0; i < suffix.statBonus.length; i++) {
				desc.append(suffix.statBonus[i]);
				if (i < suffix.statBonus.length - 1) {
					desc.append(", ");
				}
			}
			desc.append(". ");
		}

		// Add health bonus info
		if (suffix != null && suffix.bonusHealth > 0) {
			desc.append("Wearing this grants ").append(suffix.bonusHealth).append(" bonus health. ");
		}

		return desc.toString();
	}

	@Override
	public ItemSprite.Glowing glowing() {
		// Add glowing effect for rare+ items
		if (rarity == ItemFactory.Rarity.RARE) {
			return new ItemSprite.Glowing(0x0070DD); // Blue
		} else if (rarity == ItemFactory.Rarity.EPIC) {
			return new ItemSprite.Glowing(0xA335EE); // Purple
		} else if (rarity == ItemFactory.Rarity.LEGENDARY) {
			return new ItemSprite.Glowing(0xFF8000); // Orange
		}
		return super.glowing();
	}

	@Override
	public int price() {
		int price = super.price();

		// Apply rarity multiplier
		switch (rarity) {
			case UNCOMMON:  price = (int)(price * 1.5f); break;
			case RARE:      price = (int)(price * 2.5f); break;
			case EPIC:      price = (int)(price * 4.0f); break;
			case LEGENDARY: price = (int)(price * 10.0f); break;
		}

		// Apply material multiplier
		price = (int)(price * material.statMultiplier);

		return price;
	}

	@Override
	public Item random() {
		// Procedural items are already randomized, so just return this
		// But we can add a small chance for additional upgrades
		if (Random.Int(20) == 0) {
			upgrade();
		}

		// Small chance to add a glyph
		if (Random.Int(15) == 0) {
			inscribe();
		}

		return this;
	}

	// ============================================================================
	// SERIALIZATION
	// ============================================================================

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);

		// Store component indices instead of objects
		bundle.put(MATERIAL, getMaterialIndex(material));
		bundle.put(QUALITY, getQualityIndex(quality));
		bundle.put(BASE, getArmorBaseIndex(base));
		bundle.put(PREFIX, prefix != null ? getPrefixIndex(prefix) : -1);
		bundle.put(SUFFIX, suffix != null ? getSuffixIndex(suffix) : -1);
		bundle.put(RARITY, rarity.ordinal());
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);

		// Restore components from indices
		material = ItemFactory.MATERIALS[bundle.getInt(MATERIAL)];
		quality = ItemFactory.QUALITIES[bundle.getInt(QUALITY)];
		base = ItemFactory.ARMOR_BASES[bundle.getInt(BASE)];

		int prefixIndex = bundle.getInt(PREFIX);
		prefix = prefixIndex >= 0 ? ItemFactory.PREFIXES[prefixIndex] : null;

		int suffixIndex = bundle.getInt(SUFFIX);
		suffix = suffixIndex >= 0 ? ItemFactory.SUFFIXES[suffixIndex] : null;

		rarity = ItemFactory.Rarity.values()[bundle.getInt(RARITY)];

		// Regenerate name
		this.name = generateName();
		this.image = base.sprite;
	}

	// Helper methods to get array indices
	private int getMaterialIndex(ItemFactory.Material mat) {
		for (int i = 0; i < ItemFactory.MATERIALS.length; i++) {
			if (ItemFactory.MATERIALS[i] == mat) return i;
		}
		return 0;
	}

	private int getQualityIndex(ItemFactory.Quality qual) {
		for (int i = 0; i < ItemFactory.QUALITIES.length; i++) {
			if (ItemFactory.QUALITIES[i] == qual) return i;
		}
		return 0;
	}

	private int getArmorBaseIndex(ItemFactory.ArmorBase bs) {
		for (int i = 0; i < ItemFactory.ARMOR_BASES.length; i++) {
			if (ItemFactory.ARMOR_BASES[i] == bs) return i;
		}
		return 0;
	}

	private int getPrefixIndex(ItemFactory.Prefix pre) {
		for (int i = 0; i < ItemFactory.PREFIXES.length; i++) {
			if (ItemFactory.PREFIXES[i] == pre) return i;
		}
		return 0;
	}

	private int getSuffixIndex(ItemFactory.Suffix suf) {
		for (int i = 0; i < ItemFactory.SUFFIXES.length; i++) {
			if (ItemFactory.SUFFIXES[i] == suf) return i;
		}
		return 0;
	}
}
