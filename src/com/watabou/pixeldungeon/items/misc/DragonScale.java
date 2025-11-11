/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.misc;

import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Dragon Scale - Rare crafting material
 * Harvested from dragons in Lavastorm
 */
public class DragonScale extends Item {

	{
		name = "red dragon scale";
		image = ItemSpriteSheet.SOMETHING;
		stackable = true;
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public String info() {
		return
			"This magnificent scale came from a red dragon's hide. Dragon scales are among the most valuable crafting " +
			"materials in all of Norrath, prized for their incredible durability and magical properties. A single scale " +
			"is harder than steel yet weighs almost nothing, and it retains a measure of the dragon's natural resistance " +
			"to fire and magic. Master armorers can forge dragon scales into nearly impenetrable armor that kings would " +
			"envy. The scale shimmers with inner light and feels warm to the touch, as if the dragon's fire still burns " +
			"within. These scales are so rare that most adventurers never see one in their entire career. Only those brave " +
			"or foolish enough to face dragons have any chance of obtaining them.";
	}

	@Override
	public int price() {
		return 500 * quantity;
	}
}
