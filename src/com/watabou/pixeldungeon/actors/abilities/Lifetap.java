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
 * Lifetap - Shadowknight/Necromancer ability from EverQuest II
 * Drains life from the target to heal yourself
 */
public class Lifetap extends Ability {

	public Lifetap() {
		name = "Lifetap";
		description = "Siphon the life force from your enemy, damaging them and healing yourself. " +
		              "The darker path to survival favored by Shadowknights and Necromancers.";
		manaCost = 25;
		staminaCost = 0;
		cooldown = 8f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Lifetap!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Lifetap!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 8) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Deal damage based on mysticism
		int damage = Random.NormalIntRange(hero.mysticism, hero.mysticism * 2);
		enemy.damage(damage, this);

		// Heal for 75% of damage dealt
		int healAmount = (int)(damage * 0.75f);
		hero.HP = Math.min(hero.HT, hero.HP + healAmount);

		GLog.p("You drain %d HP from %s and heal for %d!", damage, enemy.name, healAmount);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
