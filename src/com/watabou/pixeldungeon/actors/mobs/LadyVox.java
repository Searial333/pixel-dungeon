/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Chill;
import com.watabou.pixeldungeon.actors.buffs.Frost;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.items.potions.PotionOfExperience;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.watabou.pixeldungeon.items.wands.WandOfAvalanche;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ElementalSprite;
import com.watabou.utils.Random;

/**
 * Lady Vox - Legendary ice dragon raid boss
 * Ancient white dragon who rules Permafrost
 */
public class LadyVox extends Mob {

	{
		name = "Lady Vox";
		spriteClass = ElementalSprite.class;  // TODO: Create VoxSprite

		HP = HT = 500;  // Raid boss HP
		defenseSkill = 35;

		EXP = 100;
		maxLvl = 25;

		flying = true;
		loot = new ScrollOfUpgrade();
		lootChance = 0.8f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 30, 60 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 40;
	}

	@Override
	public int dr() {
		return 25;
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		// Lady Vox's breath weapon - freeze enemies
		if (Random.Int( 3 ) == 0) {
			Buff.affect( enemy, Frost.class, Frost.duration( enemy ) * Random.Float( 1f, 2f ) );
		} else if (Random.Int( 2 ) == 0) {
			Buff.prolong( enemy, Chill.class, 3f );
		}

		// Area of effect cold aura
		if (Random.Int( 5 ) == 0) {
			for (int i : Level.NEIGHBOURS8) {
				int cell = pos + i;
				Char ch = Char.findChar(cell);
				if (ch != null && ch != this) {
					Buff.prolong( ch, Chill.class, 2f );
				}
			}
		}

		return damage;
	}

	@Override
	public void die( Object cause ) {
		super.die( cause );

		// Epic loot table
		Dungeon.level.drop( new Gold( Random.IntRange( 500, 1000 ) ), pos ).sprite.drop();

		// Guaranteed high-tier loot
		if (Random.Int(2) == 0) {
			Dungeon.level.drop( new WandOfAvalanche(), pos ).sprite.drop();
		}

		// Multiple scrolls of upgrade
		for (int i = 0; i < Random.IntRange(2, 4); i++) {
			Dungeon.level.drop( new ScrollOfUpgrade(), pos ).sprite.drop();
		}

		// Rare potion of experience
		if (Random.Int(3) == 0) {
			Dungeon.level.drop( new PotionOfExperience(), pos ).sprite.drop();
		}

		Badges.validateBossSlain();
		Badges.validateRare(new LadyVox());
	}

	@Override
	public String description() {
		return
			"Lady Vox is one of the most feared dragons in all of Norrath. This ancient white dragon has ruled the " +
			"frozen wastes of Permafrost for millennia, her lair filled with the frozen corpses of countless adventurers " +
			"who dared challenge her. Her scales shimmer like diamonds in the ice, and her breath can freeze a warrior " +
			"solid in seconds. Vox is not merely a beast - she is ancient, intelligent, and utterly ruthless. She speaks " +
			"in riddles and cruel jests before incinerating her victims with blasts of absolute zero. Her hoard is legendary, " +
			"filled with treasures from fallen kingdoms and heroes. Vox is sister to Lord Nagafen, and their rivalry is " +
			"as old as the mountains themselves. She represents the ultimate challenge for any adventurer, a test of skill, " +
			"preparation, and courage. Only the mightiest heroes have ever survived an encounter with Lady Vox, and fewer " +
			"still have claimed victory.";
	}
}
