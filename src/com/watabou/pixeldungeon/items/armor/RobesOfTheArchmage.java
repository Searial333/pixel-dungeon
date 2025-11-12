/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public class RobesOfTheArchmage extends Armor {

	{
		name = "Robes of the Archmage";
		image = ItemSpriteSheet.ARMOR_MAGE;
	}

	public RobesOfTheArchmage() {
		super( 2 );  // Light armor
	}

	@Override
	public String desc() {
		return
			"These flowing robes are woven from threads of pure mana, visible only to those with magical sight. " +
			"The fabric shimmers with all colors of the spectrum, shifting through hues of blue, purple, and gold. " +
			"Created by the Arcane Academy of Freeport, these robes are awarded only to mages who have proven their " +
			"mastery of the arcane arts. The robes provide minimal physical protection but are heavily enchanted against " +
			"magical attacks. Runes of power are embroidered throughout, each one a spell of warding or amplification. " +
			"Wizards who wear these robes find their spells easier to cast and more potent in effect. The hem never " +
			"gets dirty, and the fabric repairs itself when torn. Pockets sewn into the interior are dimensionally " +
			"expanded, holding far more than should be possible. The hood can be pulled up to obscure the wearer's " +
			"face in shadow, useful for maintaining anonymity. When casting powerful spells, the robes glow with eldritch " +
			"energy, crackling with arcane power that intimidates lesser magic users.";
	}
}
