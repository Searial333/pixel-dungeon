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
 * Rampage - Berserker ability from EverQuest II
 * Devastating whirlwind attack that hits all adjacent enemies
 */
public class Rampage extends Ability {

	public Rampage() {
		name = "Rampage";
		description = "Unleash a furious whirlwind of attacks, striking all adjacent enemies with brutal force. " +
		              "A Berserker's rage made manifest in devastating cleaving attacks.";
		manaCost = 0;
		staminaCost = 45;
		cooldown = 15f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for Rampage!");
			return false;
		}

		use(hero);

		int enemiesHit = 0;
		int totalDamage = 0;

		// Hit all adjacent enemies
		for (int i : Level.NEIGHBOURS8) {
			int cell = hero.pos + i;
			Char enemy = Char.findChar(cell);
			if (enemy != null && enemy != hero && enemy instanceof Mob) {
				int damage = Random.NormalIntRange(hero.durability, hero.durability * 2);
				enemy.damage(damage, this);
				enemiesHit++;
				totalDamage += damage;
			}
		}

		if (enemiesHit > 0) {
			GLog.p("You rampage wildly, hitting %d %s for %d total damage!",
			       enemiesHit, enemiesHit == 1 ? "enemy" : "enemies", totalDamage);
		} else {
			GLog.i("No enemies in range to rampage!");
		}

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
