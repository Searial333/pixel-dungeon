/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Reactive Heal - Templar ability from EverQuest II
 * Places a ward that automatically heals you when damaged
 */
public class ReactiveHeal extends Ability {

	public ReactiveHeal() {
		name = "Reactive Heal";
		description = "Place a divine ward upon yourself that automatically triggers healing when you take damage. " +
		              "Templars use this to survive on the front lines while wearing heavy armor.";
		manaCost = 40;
		staminaCost = 0;
		cooldown = 25f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Reactive Heal!");
			return false;
		}

		use(hero);

		// TODO: Create ReactiveHealBuff that triggers on damage taken
		// For now, immediate heal
		int healAmount = (hero.mysticism + hero.durability);
		hero.HP = Math.min(hero.HT, hero.HP + healAmount);

		GLog.p("A divine ward forms around you, ready to heal your wounds!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
