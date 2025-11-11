/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.buffs;

import com.watabou.pixeldungeon.ui.BuffIndicator;

/**
 * Shadow Form - Assassin/Rogue buff that increases evasion and critical strikes
 * Blend with shadows for deadly strikes
 */
public class ShadowForm extends Buff {

	public static final float DURATION = 30f;

	@Override
	public int icon() {
		return BuffIndicator.SHADOWS;
	}

	@Override
	public String toString() {
		return "Shadow Form";
	}

	@Override
	public String desc() {
		return "You have merged with the shadows, becoming nearly impossible to hit. Your strikes from this form " +
		       "are devastating, dealing critical damage to unsuspecting foes. Assassins and Swashbucklers use this " +
		       "ability to turn the tide of battle, dancing between shadows to deliver deadly attacks. The form will " +
		       "dissipate when the shadows release their hold on you.";
	}
}
