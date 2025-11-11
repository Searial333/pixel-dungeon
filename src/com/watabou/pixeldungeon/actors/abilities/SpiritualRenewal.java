/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Regeneration;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Spiritual Renewal - Shaman/Druid ability from EverQuest II
 * Channels spiritual energy to provide healing over time
 */
public class SpiritualRenewal extends Ability {

	public SpiritualRenewal() {
		name = "Spiritual Renewal";
		description = "Call upon ancestral spirits to renew your life force over time. This shamanic blessing " +
		              "provides sustained healing, allowing Shamans, Druids, and Wardens to endure prolonged battles. " +
		              "The spirits of nature flow through you, mending wounds with each passing moment.";
		manaCost = 40;
		staminaCost = 0;
		cooldown = 15f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for spiritual renewal!");
			return false;
		}

		use(hero);

		// Apply regeneration buff - enhanced version
		Buff.affect(hero, Regeneration.class);

		// Immediate heal based on Presence
		int immediateHeal = 5 + hero.presence;
		hero.HP = Math.min(hero.HT, hero.HP + immediateHeal);

		hero.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.4f, 6);

		GLog.p("Spiritual energy flows through you, healing %d HP immediately and continuously!", immediateHeal);
		GLog.i("Regeneration effect applied!");
		hero.trackMetric("heal", immediateHeal);
		hero.trackMetric("support", 3);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
