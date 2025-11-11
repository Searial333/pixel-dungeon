/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities.aa;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Regeneration;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Second Wind - AA ability that provides emergency healing and stamina
 * When near death, tap into hidden reserves
 */
public class SecondWind extends AAAbility {

	public SecondWind() {
		name = "Second Wind";
		description = "When death seems certain, tap into hidden reserves of strength. Instantly restore health and " +
		              "stamina, and gain regeneration. This ability can only be used when seriously wounded.";
		manaCost = 0;
		staminaCost = 0;  // Costs nothing - it's an emergency ability
		cooldown = 180f;  // 3 minute cooldown
		image = ItemSpriteSheet.SOMETHING;

		aaPointCost = 10;
		requiredLevel = 10;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		// Can only use when below 30% HP
		if (hero.HP > hero.HT * 0.3f) {
			GLog.w("You can only use Second Wind when seriously wounded!");
			return false;
		}

		// Restore 50% HP and stamina
		hero.HP = Math.min(hero.HT, hero.HP + hero.HT / 2);
		hero.staminaPool = Math.min(hero.maxStaminaPool, hero.staminaPool + hero.maxStaminaPool / 2);

		// Grant regeneration
		Buff.affect(hero, Regeneration.class).boost(5);

		GLog.p("You catch your second wind! Strength flows back into your limbs!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
