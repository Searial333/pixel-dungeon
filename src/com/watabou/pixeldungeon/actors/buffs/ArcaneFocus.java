/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.buffs;

import com.watabou.pixeldungeon.ui.BuffIndicator;

/**
 * Arcane Focus - Wizard buff that increases spell damage and reduces mana costs
 * Channel pure magical energy
 */
public class ArcaneFocus extends Buff {

	public static final float DURATION = 40f;

	@Override
	public int icon() {
		return BuffIndicator.MIND_VISION;  // TODO: Create proper icon
	}

	@Override
	public String toString() {
		return "Arcane Focus";
	}

	@Override
	public String desc() {
		return "Your mind is perfectly attuned to the arcane energies flowing through Norrath. Spells cast while " +
		       "under this focus deal significantly more damage and cost less mana to cast. Wizards and Sorcerers " +
		       "enter this state of heightened magical awareness to unleash devastating spell combinations. The focus " +
		       "requires intense concentration and will eventually break as mental fatigue sets in.";
	}
}
