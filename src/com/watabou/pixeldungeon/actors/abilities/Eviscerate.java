/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Eviscerate - Assassin ability from EverQuest II
 * Devastating finishing move that deals massive damage to low HP enemies
 */
public class Eviscerate extends Ability {

	public Eviscerate() {
		name = "Eviscerate";
		description = "A brutal finishing strike that deals massive damage, especially effective against wounded enemies. " +
		              "Assassins use this as their coup de grâce, often killing their target outright.";
		manaCost = 0;
		staminaCost = 50;
		cooldown = 15f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for Eviscerate!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Eviscerate!");
			return false;
		}

		if (!Level.adjacent(hero.pos, enemy.pos)) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Base damage scales with Skill
		int baseDamage = Random.NormalIntRange(hero.skill * 2, hero.skill * 4);

		// Bonus damage if enemy is below 50% HP
		if (enemy.HP < enemy.HT / 2) {
			baseDamage = (int)(baseDamage * 1.5f);
			GLog.p("You eviscerate %s's weak point for %d critical damage!", enemy.name, baseDamage);
		} else {
			GLog.p("You eviscerate %s for %d damage!", enemy.name, baseDamage);
		}

		enemy.damage(baseDamage, this);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
