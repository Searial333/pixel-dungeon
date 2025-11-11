/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.quest;

import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

/**
 * Orcish Belt - Quest item from Crushbone orcs
 * Proof of slaying orcs for various factions
 */
public class OrcishBelt extends Item {

	{
		name = "Crushbone orc belt";
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
			"This crude leather belt was worn by an orc of the Crushbone clan. Orcish belts are constructed from thick, " +
			"poorly-tanned leather and feature an iron buckle stamped with clan markings. The High Elves of Felwithe pay " +
			"handsomely for these belts as proof of orc kills, using them to track the effectiveness of anti-orc campaigns. " +
			"The Soldiers of Tunare, an elite elven military order, have standing bounties for Crushbone belts. Each belt " +
			"represents one less orc threatening the elven lands of Faydark. Adventurers who collect these can trade them " +
			"to faction representatives for gold, equipment, or increased standing with the elven kingdoms.";
	}
}
