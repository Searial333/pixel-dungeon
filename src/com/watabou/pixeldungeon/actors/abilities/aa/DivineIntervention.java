/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities.aa;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Blessed;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Divine Intervention - AA ability that prevents death
 * Your deity saves you from certain death once
 */
public class DivineIntervention extends AAAbility {

	public DivineIntervention() {
		name = "Divine Intervention";
		description = "Call upon your deity for divine protection. If you would die while this is active, you are instead " +
		              "saved at 1 HP and granted temporary invulnerability. Your deity can only intervene once per day.";
		manaCost = 50;
		staminaCost = 0;
		cooldown = 600f;  // 10 minute cooldown (effectively once per dungeon run)
		image = ItemSpriteSheet.SOMETHING;

		aaPointCost = 30;
		requiredLevel = 15;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Divine Intervention!");
			return false;
		}

		use(hero);

		// Apply divine blessing
		// TODO: Create DivineSafeguardBuff that prevents death once
		Buff.affect(hero, Blessed.class, 100f);

		GLog.p("Your deity watches over you! Divine protection surrounds you!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
