/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public class PlateOfEternalVigilance extends Armor {

	{
		name = "Plate of Eternal Vigilance";
		image = ItemSpriteSheet.ARMOR_PLATE;
	}

	public PlateOfEternalVigilance() {
		super( 7 );  // High armor value
	}

	@Override
	public String desc() {
		return
			"The Plate of Eternal Vigilance was forged by Mithaniel Marr himself and gifted to his most devoted " +
			"paladins. The armor shines with an inner light that never dims, and evil creatures find themselves " +
			"uncomfortable in its presence. Each plate is inscribed with prayers to the Lightbringer, and the armor " +
			"seems to grow stronger when defending the innocent. Guardians and Paladins who wear this armor report " +
			"feeling Marr's presence guiding their actions, warning them of danger before it strikes. The metal is " +
			"silvered mithril from the Plane of Valor, lightweight yet incredibly durable. Holy symbols are worked " +
			"into every piece, from the pauldrons to the greaves. When struck by dark magic, the armor flares with " +
			"brilliant light, dispelling curses and hexes. Those who wear it are marked as champions of good, earning " +
			"respect from allies and fear from enemies. The armor adjusts to fit its wearer perfectly, as if alive.";
	}
}
