/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Regeneration;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Regrowth - Warden ability from EverQuest II
 * Powerful healing over time effect
 */
public class Regrowth extends Ability {

	public Regrowth() {
		name = "Regrowth";
		description = "Channel nature's regenerative power to heal yourself over time. " +
		              "Wardens specialize in healing-over-time effects that are mana efficient.";
		manaCost = 25;
		staminaCost = 0;
		cooldown = 10f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Regrowth!");
			return false;
		}

		use(hero);

		// Strong regeneration based on mysticism
		int regenPower = hero.mysticism;
		Buff.affect(hero, Regeneration.class).boost(regenPower);

		GLog.p("Nature's power flows through you, regenerating your wounds!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
