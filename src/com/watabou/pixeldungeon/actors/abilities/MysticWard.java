/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Blessed;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Mystic Ward - Mystic ability from EverQuest II
 * Create a protective barrier that absorbs damage
 */
public class MysticWard extends Ability {

	public MysticWard() {
		name = "Mystic Ward";
		description = "Weave ancient protective magic around yourself, creating a barrier that absorbs damage. " +
		              "Mystics use wards and foresight to prevent harm before it occurs.";
		manaCost = 45;
		staminaCost = 0;
		cooldown = 20f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Mystic Ward!");
			return false;
		}

		use(hero);

		// Apply protective buff
		float duration = 25f + hero.mysticism;
		Buff.affect(hero, Blessed.class, duration);

		// TODO: Create actual Ward buff that absorbs damage

		GLog.p("Mystical energy forms a protective barrier around you!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
