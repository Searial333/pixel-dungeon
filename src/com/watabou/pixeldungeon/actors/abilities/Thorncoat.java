/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Barkskin;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Thorncoat - Druid ability from EverQuest II
 * Cover yourself in thorns that damage attackers
 */
public class Thorncoat extends Ability {

	public Thorncoat() {
		name = "Thorncoat";
		description = "Cover your body in magical thorns that damage any enemy who strikes you. " +
		              "Druids use this defensive buff to punish melee attackers.";
		manaCost = 35;
		staminaCost = 0;
		cooldown = 20f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Thorncoat!");
			return false;
		}

		use(hero);

		// Apply barkskin buff (provides armor)
		float duration = 30f + hero.mysticism;
		Buff.affect(hero, Barkskin.class).level(hero.mysticism / 2);

		// TODO: Add Thorns buff that reflects damage

		GLog.p("Thorns sprout from your skin, ready to punish attackers!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
