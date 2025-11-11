/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.quest;

import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Gnoll Tooth - Quest item from EverQuest II
 * Collected from gnolls for various quests
 */
public class GnollTooth extends Item {

	{
		name = "Sabertooth gnoll fang";
		image = ItemSpriteSheet.SOMETHING;
		stackable = true;
		unique = false;
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
			"This is a large, yellowed fang from a Sabertooth gnoll. The fang is nearly six inches long and still sharp, " +
			"showing signs of the gnoll's carnivorous diet. Merchants in Qeynos and various quest-givers collect these " +
			"fangs as proof of gnoll kills, offering rewards to adventurers who help thin the gnoll population around " +
			"Blackburrow. The fangs can also be used by skilled craftsmen to create primitive but effective weapons and " +
			"tools. Some alchemists grind the fangs into powder for use in strange concoctions. These trophies are common " +
			"loot from gnoll encounters and stack easily in your pack.";
	}
}
