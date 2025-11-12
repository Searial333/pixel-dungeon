/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public class VestmentsOfTheHighPriest extends Armor {

	{
		name = "Vestments of the High Priest";
		image = ItemSpriteSheet.ARMOR_MAGE;
	}

	public VestmentsOfTheHighPriest() {
		super( 2 );  // Light cloth armor
	}

	@Override
	public String desc() {
		return
			"These sacred vestments are worn by high priests of Rodcet Nife, the Prime Healer. The white cloth is " +
			"woven from blessed fibers grown in the Garden of Marr, never staining no matter how much blood is spilled " +
			"during healing work. Gold thread forms intricate patterns depicting the cycle of life and rebirth, and each " +
			"stitch was sewn with prayers for those who would wear the garments. The vestments radiate gentle warmth " +
			"and those who stand near the wearer feel their pains ease slightly. Clerics, Druids, and Shamans who don " +
			"these robes find their healing magic amplified significantly. The fabric seems alive, glowing faintly when " +
			"healing spells are cast. Holy symbols of various good deities are embroidered throughout, showing unity " +
			"among the forces of light. The sleeves are wide to allow for dramatic gesturing during rituals, and hidden " +
			"pockets hold components for emergency healing. When the wearer prays, a soft golden aura surrounds them, " +
			"visible even to the non-religious as proof of divine favor.";
	}
}
