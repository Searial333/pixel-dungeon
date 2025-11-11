/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities.aa;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Invisibility;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Perfect Form - AA ability that temporarily maximizes all stats
 * The ultimate self-buff that makes you a god for a short time
 */
public class PerfectForm extends AAAbility {

	public PerfectForm() {
		name = "Perfect Form";
		description = "Transcend your mortal limitations and achieve perfect form. For a brief moment, all your stats " +
		              "are maximized, your health and resources fully restored, and you become unstoppable. " +
		              "This is the pinnacle of Alternate Advancement mastery.";
		manaCost = 100;
		staminaCost = 100;
		cooldown = 300f;  // 5 minute cooldown
		image = ItemSpriteSheet.SOMETHING;

		aaPointCost = 50;
		requiredLevel = 20;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough resources for Perfect Form!");
			return false;
		}

		use(hero);

		// Restore all resources
		hero.HP = hero.HT;
		hero.manaPool = hero.maxManaPool;
		hero.staminaPool = hero.maxStaminaPool;

		// Apply massive buff
		// TODO: Create PerfectFormBuff that temporarily boosts all stats
		Buff.affect(hero, Invisibility.class, 1f);  // Placeholder effect

		GLog.p("You achieve Perfect Form! Your power reaches its absolute peak!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
