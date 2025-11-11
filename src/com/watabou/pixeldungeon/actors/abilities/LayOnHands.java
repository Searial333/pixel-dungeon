/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Lay on Hands - Paladin ability from EverQuest II
 * Emergency self-heal that restores significant health
 */
public class LayOnHands extends Ability {

	public LayOnHands() {
		name = "Lay on Hands";
		description = "Channel divine power to instantly heal yourself. " +
		              "A Paladin's emergency heal that can save you from certain death. Long cooldown.";
		manaCost = 40;
		staminaCost = 0;
		cooldown = 120f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Lay on Hands!");
			return false;
		}

		use(hero);

		// Heal for 50% of max HP
		int healAmount = hero.HT / 2;
		hero.HP = Math.min(hero.HT, hero.HP + healAmount);

		GLog.p("Divine light washes over you, restoring %d HP!", healAmount);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
