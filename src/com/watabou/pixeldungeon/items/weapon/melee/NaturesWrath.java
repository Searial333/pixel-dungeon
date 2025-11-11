/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Roots;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class NaturesWrath extends MeleeWeapon {

	{
		name = "Nature's Wrath";
		image = ItemSpriteSheet.STAFF;
	}

	public NaturesWrath() {
		super( 4, 1.0f, 1.0f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 25% chance to root the enemy
		if (Random.Int(4) == 0) {
			Buff.prolong(defender, Roots.class, 5f);
		}
	}

	@Override
	public String desc() {
		return
			"Nature's Wrath is a living staff blessed by Tunare, the Mother of All. Carved from an ancient Elddar tree " +
			"in the Enchanted Lands, this staff pulses with primal energy. Vines and leaves grow along its length, and it " +
			"smells of fresh earth and rain. Druids and Wardens channel Tunare's fury through this weapon, and enemies " +
			"struck by it often find themselves entangled by sudden growths of roots and thorns. The staff is said to weep " +
			"sap when nature itself is threatened.";
	}
}
