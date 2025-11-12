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
 * Smite Evil - Inquisitor ability from EverQuest II
 * Deal holy damage to enemies, especially effective vs undead
 */
public class SmiteEvil extends Ability {

	public SmiteEvil() {
		name = "Smite Evil";
		description = "Strike your enemy with holy wrath, dealing divine damage. Extra effective against undead and demons. " +
		              "Inquisitors balance healing with aggressive holy magic.";
		manaCost = 30;
		staminaCost = 0;
		cooldown = 8f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Smite Evil!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Smite Evil!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 6) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Holy damage based on mysticism
		int damage = Random.NormalIntRange(hero.mysticism, hero.mysticism * 2);

		// TODO: Check if enemy is undead/demon for bonus damage
		// if (enemy.isUndead()) damage *= 2;

		enemy.damage(damage, this);

		GLog.p("Holy light burns %s for %d damage!", enemy.name, damage);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
