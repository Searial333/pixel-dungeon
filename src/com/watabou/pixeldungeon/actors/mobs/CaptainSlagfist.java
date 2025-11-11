/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.items.armor.PlateArmor;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.sprites.BruteSprite;
import com.watabou.utils.Random;

/**
 * Captain Slagfist - Named orc commander in Crushbone
 * One of Emperor Crush's top lieutenants
 */
public class CaptainSlagfist extends Orc {

	{
		name = "Captain Slagfist";
		spriteClass = BruteSprite.class;

		HP = HT = 45;
		defenseSkill = 12;

		EXP = 10;
		maxLvl = 14;

		loot = new PlateArmor();
		lootChance = 0.25f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 8, 14 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 20;
	}

	@Override
	public int dr() {
		return 8;
	}

	@Override
	public String description() {
		return
			"Captain Slagfist is one of Emperor Crush's most trusted commanders, leading elite orc legionaries " +
			"in the ongoing siege of elven territories. Unlike most orcs who rely solely on brute force, Slagfist " +
			"is a disciplined military officer who drills his troops relentlessly and maintains strict order. His " +
			"fist was crushed in battle and replaced with a spiked iron gauntlet - hence his name. Slagfist wears " +
			"well-maintained plate armor looted from fallen elven knights, and he knows how to use it effectively. " +
			"He's earned his rank through countless victories and shows no mercy to enemies of the orcish empire.";
	}
}
