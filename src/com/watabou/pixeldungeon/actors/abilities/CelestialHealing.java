/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Celestial Healing - Cleric/Priest ability from EverQuest II
 * Powerful healing that restores a large amount of health
 */
public class CelestialHealing extends Ability {

	public CelestialHealing() {
		name = "Celestial Healing";
		description = "Channel divine energy to heal yourself or an ally for a substantial amount. " +
		              "This powerful healing spell is the cornerstone of Clerics and Templars, " +
		              "allowing them to keep their party alive in the darkest moments.";
		manaCost = 50;
		staminaCost = 0;
		cooldown = 8f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for celestial healing!");
			return false;
		}

		use(hero);

		// Heal based on Mysticism and Presence
		int healAmount = 20 + (hero.mysticism * 2) + hero.presence;
		hero.HP = Math.min(hero.HT, hero.HP + healAmount);

		hero.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.4f, 4);

		GLog.p("Divine light washes over you, healing %d HP!", healAmount);
		hero.trackMetric("heal", healAmount);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
