/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Sleep;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Mesmerize - Illusionist/Coercer ability from EverQuest II
 * Put an enemy to sleep with mental magic
 */
public class Mesmerize extends Ability {

	public Mesmerize() {
		name = "Mesmerize";
		description = "Weave a spell of enchantment that puts your enemy into a deep sleep. " +
		              "Illusionists use this mind control to neutralize dangerous foes, but damage will wake them.";
		manaCost = 35;
		staminaCost = 0;
		cooldown = 12f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Mesmerize!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Mesmerize!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 8) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Duration based on Presence stat
		float duration = 10f + (hero.presence * 2);
		Buff.affect(enemy, Sleep.class);

		GLog.p("You mesmerize %s, putting them into a deep sleep!", enemy.name);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
