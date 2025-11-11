/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities.aa;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Time Warp - AA ability that grants extra actions
 * Manipulate time itself to act twice in succession
 */
public class TimeWarp extends AAAbility {

	public TimeWarp() {
		name = "Time Warp";
		description = "Bend the fabric of time itself, allowing you to act twice in rapid succession. For a brief moment, " +
		              "time slows for everyone but you, granting you a decisive advantage in combat.";
		manaCost = 80;
		staminaCost = 0;
		cooldown = 120f;  // 2 minute cooldown
		image = ItemSpriteSheet.SOMETHING;

		aaPointCost = 25;
		requiredLevel = 15;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Time Warp!");
			return false;
		}

		use(hero);

		// Grant the hero an immediate extra turn
		hero.spend(-Actor.TICK);  // Refund the action cost

		GLog.p("Time warps around you! You move with incredible speed!");

		return true;
	}
}
