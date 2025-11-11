/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Paralysis;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.items.keys.SkeletonKey;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.watabou.pixeldungeon.items.weapon.melee.BattleAxe;
import com.watabou.pixeldungeon.sprites.BruteSprite;
import com.watabou.utils.Random;

/**
 * Emperor Crush - Legendary orc warlord and boss
 * Rules Crushbone Keep with an iron fist
 */
public class EmperorCrush extends Mob {

	{
		name = "Emperor Crush";
		spriteClass = BruteSprite.class;  // TODO: Create EmperorSprite

		HP = HT = 120;
		defenseSkill = 20;

		EXP = 30;
		maxLvl = 15;

		loot = new SkeletonKey();
		lootChance = 1f;  // Always drops key
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 15, 30 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 28;
	}

	@Override
	public int dr() {
		return 15;
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		// Emperor's devastating strikes can stun
		if (Random.Int( 4 ) == 0) {
			Buff.prolong( enemy, Paralysis.class, Paralysis.DURATION / 2 );
		}
		return damage;
	}

	@Override
	public void die( Object cause ) {
		super.die( cause );

		// Drop additional rewards
		Dungeon.level.drop( new Gold( Random.IntRange( 100, 200 ) ), pos ).sprite.drop();
		if (Random.Int(2) == 0) {
			Dungeon.level.drop( new BattleAxe(), pos ).sprite.drop();
		}
		if (Random.Int(3) == 0) {
			Dungeon.level.drop( new ScrollOfUpgrade(), pos ).sprite.drop();
		}

		Badges.validateBossSlain();
	}

	@Override
	public String description() {
		return
			"Emperor Crush is the self-proclaimed ruler of all orcs in the Greater Faydark region. From his throne " +
			"in the depths of Crushbone Keep, he commands thousands of orcish warriors in an ongoing campaign to " +
			"conquer the elven lands. Crush is an enormous orc, standing over seven feet tall and built like a siege " +
			"weapon. His armor is forged from the finest stolen elven steel, inscribed with crude orcish runes of power. " +
			"He wields a massive battle axe that has cleaved through countless foes. Crush is not just a brute - he's " +
			"a cunning military strategist who has united the fractious orc clans through a combination of strength and " +
			"tactical brilliance. His ultimate goal is to overthrow the High Elves of Felwithe and claim their city as " +
			"his new capital. Defeating Emperor Crush would deal a devastating blow to orcish ambitions in Faydark.";
	}
}
