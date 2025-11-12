/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class BowOfTheWoodElf extends MeleeWeapon {

	{
		name = "Bow of the Wood Elf";
		image = ItemSpriteSheet.CROSSBOW;
	}

	public BowOfTheWoodElf() {
		super( 4, 1.0f, 1.4f );  // Fast attack speed
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 20% chance for double shot
		if (Random.Int(5) == 0) {
			int secondShot = Random.Int(min(), max());
			defender.damage(secondShot, this);
		}
	}

	@Override
	public String desc() {
		return
			"This exquisite bow is crafted from the wood of an ancient Elddar tree, blessed by Tunare herself. " +
			"The bow is a masterpiece of elven craftsmanship, featuring intricate carvings of leaves and vines that " +
			"seem to shift and grow when not observed directly. Rangers of the Greater Faydark have used bows like " +
			"this for millennia to defend their forests from invaders. The string is made from enchanted spider silk, " +
			"providing perfect tension without ever wearing out. When drawn, the bow hums with natural energy, and " +
			"arrows fired from it fly true and swift. Occasionally, the bow's magic allows a skilled archer to loose " +
			"two arrows in the time it takes to fire one. The weapon is light as a feather yet strong as steel, and " +
			"seems to guide the archer's aim toward weak points in armor. Many wood elves consider receiving such a " +
			"bow to be a sacred honor.";
	}
}
