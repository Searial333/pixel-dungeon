/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class StaffOfEternalFlame extends MeleeWeapon {

	{
		name = "Staff of Eternal Flame";
		image = ItemSpriteSheet.QUARTERSTAFF;
	}

	public StaffOfEternalFlame() {
		super( 5, 1.0f, 1.4f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// High chance to burn enemies
		if (Random.Int(2) == 0) {
			Buff.affect(defender, Burning.class).reignite(defender);
		}
	}

	@Override
	public String desc() {
		return
			"This legendary staff was forged by the efreeti in the Plane of Fire and gifted to the wizards of Freeport. " +
			"The crystal orb at its head contains actual flames from Solusek Ro's personal forge, burning eternally without " +
			"consuming fuel. Mages who wield this staff can channel the raw power of elemental fire, setting their enemies " +
			"ablaze with even the lightest touch. The staff radiates heat constantly, and its wielder never feels cold. " +
			"Master wizards who complete their epic quest are sometimes rewarded with this powerful artifact.";
	}
}
