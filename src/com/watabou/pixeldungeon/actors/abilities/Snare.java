/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Roots;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Snare - Ranger ability from EverQuest II
 * Root an enemy in place with nature magic or traps
 */
public class Snare extends Ability {

	public Snare() {
		name = "Snare";
		description = "Trap your enemy in place with roots or tripwires, preventing them from moving. " +
		              "Rangers use this ability to control the battlefield and pick off enemies from range.";
		manaCost = 20;
		staminaCost = 15;
		cooldown = 10f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough resources for Snare!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Snare!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 8) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Duration based on Skill stat
		float duration = 5f + hero.skill;
		Buff.prolong(enemy, Roots.class, duration);

		GLog.p("You snare %s, rooting them in place!", enemy.name);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
