/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.buffs;

import com.watabou.pixeldungeon.ui.BuffIndicator;

/**
 * Stone Skin - Guardian/tank buff that massively increases defense
 * EverQuest II Guardian ability
 */
public class StoneSkin extends Buff {

	public static final float DURATION = 20f;

	@Override
	public int icon() {
		return BuffIndicator.BARKSKIN;
	}

	@Override
	public String toString() {
		return "Stone Skin";
	}

	@Override
	public String desc() {
		return "Your skin has hardened like stone, providing exceptional protection against physical attacks. " +
		       "This powerful Guardian ability makes you nearly invulnerable for a short time, allowing you to " +
		       "tank devastating blows that would kill lesser warriors. The stone skin will gradually wear off.";
	}
}
