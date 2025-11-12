/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Poisoned Blades - Brigand ability from EverQuest II
 * Coat your weapons in deadly poison
 */
public class PoisonedBlades extends Ability {

	public PoisonedBlades() {
		name = "Poisoned Blades";
		description = "Coat your weapons in a potent toxin. Your next several attacks will poison enemies, dealing " +
		              "damage over time. Brigands are masters of dirty fighting and deadly poisons.";
		manaCost = 0;
		staminaCost = 30;
		cooldown = 20f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for Poisoned Blades!");
			return false;
		}

		use(hero);

		// TODO: Create PoisonedBladesBuff that adds poison to attacks
		GLog.p("You coat your blades in deadly poison!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
