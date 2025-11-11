/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Fireball - Wizard ability from EverQuest II
 * Classic area-of-effect fire spell
 */
public class Fireball extends Ability {

	public Fireball() {
		name = "Fireball";
		description = "Hurl a blazing sphere of fire that explodes on impact, damaging all enemies in the area. " +
		              "The quintessential Wizard spell for dealing with groups of enemies.";
		manaCost = 50;
		staminaCost = 0;
		cooldown = 10f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Fireball!");
			return false;
		}

		if (!Dungeon.level.fieldOfView[target]) {
			GLog.w("Cannot target that location!");
			return false;
		}

		use(hero);

		int enemiesHit = 0;
		int totalDamage = 0;

		// Hit all enemies within 2 tiles of target
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (Level.distance(target, mob.pos) <= 2) {
				int damage = Random.NormalIntRange(hero.mysticism * 2, hero.mysticism * 3);
				mob.damage(damage, this);
				enemiesHit++;
				totalDamage += damage;
			}
		}

		if (enemiesHit > 0) {
			GLog.p("Your fireball explodes, hitting %d %s for %d total damage!",
			       enemiesHit, enemiesHit == 1 ? "enemy" : "enemies", totalDamage);
		} else {
			GLog.i("Your fireball explodes harmlessly.");
		}

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
