/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.items.weapon.melee.Mace;
import com.watabou.pixeldungeon.sprites.GnollSprite;
import com.watabou.utils.Random;

/**
 * Gnasher the Fierce - Named gnoll boss from Blackburrow
 * This legendary gnoll champion guards the deepest chambers
 */
public class GnasherTheFierce extends Gnoll {

	{
		name = "Gnasher the Fierce";
		spriteClass = GnollSprite.class;

		HP = HT = 35;  // Much tougher than regular gnoll
		defenseSkill = 10;

		EXP = 8;
		maxLvl = 12;

		// Better loot
		loot = new Mace();
		lootChance = 0.3f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 5, 10 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 16;
	}

	@Override
	public int dr() {
		return 5;
	}

	@Override
	public String description() {
		return
			"Gnasher the Fierce is a legendary champion among the Sabertooth gnolls, a massive brute who has " +
			"claimed countless adventurer lives in the tunnels of Blackburrow. His fur is matted with blood and " +
			"decorated with trophies from his victims - fingers, teeth, and bits of armor. Gnasher wields a crude " +
			"but effective mace made from a human femur wrapped in iron bands. He's twice the size of a normal gnoll " +
			"and possesses cunning intelligence that makes him far more dangerous. The Sabertooth clan looks to " +
			"Gnasher as their war leader, and he has never been defeated in single combat. His battle howl can " +
			"freeze the blood of even experienced warriors.";
	}
}
