/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public class LeathersOfTheSilentHunter extends Armor {

	{
		name = "Leathers of the Silent Hunter";
		image = ItemSpriteSheet.ARMOR_ROGUE;
	}

	public LeathersOfTheSilentHunter() {
		super( 3 );  // Medium-light armor
	}

	@Override
	public String desc() {
		return
			"Crafted by the legendary ranger Firiona Vie, these leathers are made from the hide of a shadow panther " +
			"hunted in the darkest depths of Nektulos Forest. The armor seems to drink in light, making the wearer " +
			"harder to see even in broad daylight. Rangers and rogues prize this armor above all others for its " +
			"combination of protection and stealth. The leather is treated with rare herbs and tree sap from the " +
			"Greater Faydark, making it supple and silent. No matter how fast the wearer moves, the armor makes no " +
			"sound—no creaking, no rustling, nothing. The suit is reinforced at vital areas with thin plates of " +
			"darkened steel that somehow don't impede movement. Camouflage patterns woven into the leather shift to " +
			"match the surroundings, an enchantment placed by druidic magic. The boots are lined with spidersilk, " +
			"allowing the wearer to move across any surface without leaving tracks. Many master assassins wear replicas " +
			"of this armor, but the original remains unmatched in quality and enchantment.";
	}
}
