/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Spirit Totem - Shaman ability from EverQuest II
 * Place a totem that heals allies in range over time
 */
public class SpiritTotem extends Ability {

	public SpiritTotem() {
		name = "Spirit Totem";
		description = "Plant a spirit totem that heals all nearby allies over time. " +
		              "Shamans use totems to provide sustained area healing without constant attention.";
		manaCost = 50;
		staminaCost = 0;
		cooldown = 30f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Spirit Totem!");
			return false;
		}

		if (!Dungeon.level.passable[target]) {
			GLog.w("Cannot place totem there!");
			return false;
		}

		if (Char.findChar(target) != null) {
			GLog.w("Space is occupied!");
			return false;
		}

		if (Level.distance(hero.pos, target) > 3) {
			GLog.w("Too far away!");
			return false;
		}

		use(hero);

		// TODO: Create actual Totem mob/object that heals
		// For now, just immediate heal
		int healAmount = hero.mysticism * 2;
		hero.HP = Math.min(hero.HT, hero.HP + healAmount);

		GLog.p("You plant a spirit totem that radiates healing energy!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
