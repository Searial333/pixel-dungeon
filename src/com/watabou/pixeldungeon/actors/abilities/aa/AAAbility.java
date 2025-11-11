/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities.aa;

import com.watabou.pixeldungeon.actors.abilities.Ability;
import com.watabou.pixeldungeon.actors.hero.Hero;

/**
 * AAAbility - Alternate Advancement abilities from EverQuest II
 * These are powerful endgame abilities that players unlock through
 * an alternate progression system separate from normal leveling
 */
public abstract class AAAbility extends Ability {

	protected int aaPointCost;  // How many AA points needed to unlock
	protected int requiredLevel;  // Minimum level to learn

	public int getAAPointCost() {
		return aaPointCost;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public boolean canLearn(Hero hero) {
		return hero.lvl >= requiredLevel;
		// TODO: Check hero.aaPoints >= aaPointCost when AA system is implemented
	}
}
