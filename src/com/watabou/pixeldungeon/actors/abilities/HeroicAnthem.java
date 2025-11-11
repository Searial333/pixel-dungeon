/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Bless;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Heroic Anthem - Troubadour ability from EverQuest II
 * Powerful buff song that enhances all combat stats
 */
public class HeroicAnthem extends Ability {

	public HeroicAnthem() {
		name = "Heroic Anthem";
		description = "Sing a rousing anthem that inspires heroic deeds, boosting your combat effectiveness. " +
		              "Troubadours weave magic through music, empowering themselves and their allies to greatness.";
		manaCost = 40;
		staminaCost = 0;
		cooldown = 30f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Heroic Anthem!");
			return false;
		}

		use(hero);

		// Duration based on Presence stat
		float duration = 20f + (hero.presence * 3);
		Buff.prolong(hero, Bless.class, duration);

		GLog.p("You sing a heroic anthem, empowering yourself with courage and strength!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
