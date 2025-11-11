/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public class DragonscaleArmor extends Armor {

	{
		name = "Dragonscale Armor";
		image = ItemSpriteSheet.ARMOR_SCALE;
	}

	public DragonscaleArmor() {
		super( 7 );
	}

	@Override
	public String desc() {
		return
			"Crafted from the scales of ancient red dragons slain in Lavastorm, this armor is among the most " +
			"prized possessions any warrior could hope to obtain. Each scale is harder than steel yet lighter " +
			"than leather, providing exceptional protection without hindering movement. The scales shimmer with " +
			"an inner fire, and those who wear this armor gain a measure of the dragon's legendary resistance to " +
			"heat and flame. Smiths who can work with dragonscale are rare, and the materials rarer still. " +
			"Only the greatest heroes who have faced dragons and lived can hope to wear such magnificent armor.";
	}
}
