/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Vertigo;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Haymaker - Bruiser ability from EverQuest II
 * Devastating punch that dazes the enemy
 */
public class Haymaker extends Ability {

	public Haymaker() {
		name = "Haymaker";
		description = "Wind up and deliver a crushing punch to your enemy's head, dealing massive damage and leaving them dazed. " +
		              "Bruisers use this to disable dangerous enemies.";
		manaCost = 0;
		staminaCost = 45;
		cooldown = 12f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for Haymaker!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Haymaker!");
			return false;
		}

		if (!Level.adjacent(hero.pos, enemy.pos)) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Massive damage based on durability
		int damage = Random.NormalIntRange(hero.durability * 2, hero.durability * 4);
		enemy.damage(damage, this);

		// Daze the enemy
		Buff.prolong(enemy, Vertigo.class, 4f);

		GLog.p("You deliver a crushing haymaker to %s for %d damage!", enemy.name, damage);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
