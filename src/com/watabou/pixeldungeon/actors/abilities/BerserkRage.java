/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Fury;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Berserk Rage - Berserker ability from EverQuest II
 * Increases damage output at the cost of defense
 */
public class BerserkRage extends Ability {

	public BerserkRage() {
		name = "Berserk Rage";
		description = "Enter a frenzied state of rage, increasing damage dealt but making you more vulnerable. " +
		              "The hallmark ability of Berserkers who trade safety for devastating power.";
		manaCost = 0;
		staminaCost = 40;
		cooldown = 15f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for berserk rage!");
			return false;
		}

		use(hero);

		// Apply Fury buff (increases damage)
		Buff.affect(hero, Fury.class);

		GLog.p("You enter a berserk rage! Your attacks become devastating!");
		hero.trackMetric("melee", 5);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
