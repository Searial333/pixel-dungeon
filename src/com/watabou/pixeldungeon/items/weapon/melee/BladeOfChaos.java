/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class BladeOfChaos extends MeleeWeapon {

	{
		name = "Blade of Chaos";
		image = ItemSpriteSheet.SWORD;
	}

	public BladeOfChaos() {
		super( 5, 0.8f, 1.4f );  // High variance damage (chaotic)
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 30% chance for bonus chaos damage (random element)
		if (Random.Int(10) < 3) {
			int bonusDamage = Random.Int(5, 20);
			defender.damage(bonusDamage, this);
		}
	}

	@Override
	public String desc() {
		return
			"The Blade of Chaos crackles with unpredictable arcane energy. Forged by the Warlock Council of Freeport, " +
			"this weapon channels raw chaos magic through its dark steel. Each strike unleashes wildly varying power, " +
			"sometimes merely scratching, other times devastating. Warlocks embrace this unpredictability, knowing that " +
			"chaos is the ultimate force in Norrath. The blade whispers dark secrets to those who wield it, tempting them " +
			"with promises of greater power through darker pacts.";
	}
}
