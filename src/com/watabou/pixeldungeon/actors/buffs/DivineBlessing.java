/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.buffs;

import com.watabou.pixeldungeon.ui.BuffIndicator;

/**
 * Divine Blessing - Priest buff that enhances all stats temporarily
 * Granted by deity worship or priest abilities
 */
public class DivineBlessing extends Buff {

	public static final float DURATION = 50f;

	@Override
	public int icon() {
		return BuffIndicator.MIND_VISION;  // TODO: Create proper icon
	}

	@Override
	public String toString() {
		return "Divine Blessing";
	}

	@Override
	public String desc() {
		return "The gods smile upon you! Your abilities are enhanced by divine power, " +
		       "granting increased damage, defense, and magical potency. This blessing " +
		       "comes from your deity's favor and will fade with time.";
	}
}
