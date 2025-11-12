/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.actors.buffs.Weakness;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Plague - Defiler ability from EverQuest II
 * Spread disease to all nearby enemies
 */
public class Plague extends Ability {

	public Plague() {
		name = "Plague";
		description = "Unleash a wave of pestilence that poisons and weakens all nearby enemies. " +
		              "Defilers embrace dark shaman magic, using disease as both weapon and tool.";
		manaCost = 60;
		staminaCost = 0;
		cooldown = 25f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Plague!");
			return false;
		}

		use(hero);

		int enemiesHit = 0;

		// Afflict all nearby enemies
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (Level.distance(hero.pos, mob.pos) <= 5) {
				Buff.affect(mob, Poison.class).set(Random.Int(5, 10));
				Buff.prolong(mob, Weakness.class, Weakness.DURATION);
				enemiesHit++;
			}
		}

		if (enemiesHit > 0) {
			GLog.n("A plague spreads from you, afflicting %d %s!",
			       enemiesHit, enemiesHit == 1 ? "enemy" : "enemies");
		} else {
			GLog.i("Your plague finds no victims.");
		}

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
