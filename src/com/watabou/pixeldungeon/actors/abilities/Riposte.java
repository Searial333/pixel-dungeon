/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Riposte - Swashbuckler ability from EverQuest II
 * Counter-attack stance that parries and strikes back
 */
public class Riposte extends Ability {

	public Riposte() {
		name = "Riposte";
		description = "Enter a defensive stance that parries incoming attacks and counters with devastating ripostes. " +
		              "Swashbucklers use superior technique to turn enemy attacks against them.";
		manaCost = 0;
		staminaCost = 35;
		cooldown = 15f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for Riposte!");
			return false;
		}

		use(hero);

		// TODO: Create RiposteBuff that counters attacks
		// For now, temporary evasion boost
		GLog.p("You assume a defensive stance, ready to counter any attack!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
