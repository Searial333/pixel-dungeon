/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities.aa;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Mana Regeneration - AA passive that increases mana regen rate
 * Not an active ability, but a permanent enhancement
 */
public class ManaRegeneration extends AAAbility {

	private int rank;  // Can be upgraded multiple times

	public ManaRegeneration() {
		this(1);
	}

	public ManaRegeneration(int rank) {
		this.rank = Math.min(rank, 5);  // Max rank 5

		name = "Mana Regeneration " + rank;
		description = "Permanently increase your mana regeneration rate. Each rank provides an additional +2 mana per turn. " +
		              "This passive ability can be trained up to rank 5 for a total of +10 mana per turn.";
		manaCost = 0;
		staminaCost = 0;
		cooldown = 0f;  // Passive
		image = ItemSpriteSheet.SOMETHING;

		aaPointCost = 5 * rank;  // Costs more for each rank
		requiredLevel = 5 + (rank * 2);
	}

	@Override
	public boolean execute(Hero hero, int target) {
		// This is a passive ability, not actively used
		GLog.i("Mana Regeneration is a passive ability.");
		return false;
	}

	public int getManaBonus() {
		return rank * 2;
	}
}
