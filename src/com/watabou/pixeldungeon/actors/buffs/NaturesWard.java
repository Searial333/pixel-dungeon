/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.buffs;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.ui.BuffIndicator;

/**
 * Nature's Ward - Druid/Warden buff providing regeneration and poison immunity
 * Protected by the forces of nature
 */
public class NaturesWard extends Buff {

	public static final float DURATION = 60f;
	private int level;

	@Override
	public boolean act() {
		if (target.isAlive()) {
			// Slow regeneration
			if (target.HP < target.HT) {
				target.HP = Math.min(target.HT, target.HP + 1);
			}
		}

		spend( TICK );
		return true;
	}

	@Override
	public int icon() {
		return BuffIndicator.BARKSKIN;
	}

	@Override
	public String toString() {
		return "Nature's Ward";
	}

	@Override
	public String desc() {
		return "You are protected by the primal forces of nature itself. A ward of living energy surrounds you, " +
		       "slowly healing your wounds and protecting you from poisons and toxins. Druids and Wardens call upon " +
		       "Tunare's blessing to grant this powerful ward, which makes them nearly unkillable in prolonged battles. " +
		       "The natural energy will sustain you until it is depleted.";
	}
}
