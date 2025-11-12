/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Cripple;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Tempest - Fury ability from EverQuest II
 * Unleash a storm that damages all nearby enemies
 */
public class Tempest extends Ability {

	public Tempest() {
		name = "Tempest";
		description = "Summon a raging tempest that strikes all nearby enemies with lightning and wind. " +
		              "Furies channel nature's destructive fury to devastate their foes.";
		manaCost = 70;
		staminaCost = 0;
		cooldown = 20f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Tempest!");
			return false;
		}

		use(hero);

		int enemiesHit = 0;
		int totalDamage = 0;

		// Hit all enemies in range
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (Level.distance(hero.pos, mob.pos) <= 4) {
				int damage = Random.NormalIntRange(hero.mysticism, hero.mysticism * 2);
				mob.damage(damage, this);
				Buff.prolong(mob, Cripple.class, 3f);
				enemiesHit++;
				totalDamage += damage;
			}
		}

		if (enemiesHit > 0) {
			GLog.p("Your tempest strikes %d %s for %d total damage!",
			       enemiesHit, enemiesHit == 1 ? "enemy" : "enemies", totalDamage);
		} else {
			GLog.i("Your tempest finds no targets.");
		}

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
