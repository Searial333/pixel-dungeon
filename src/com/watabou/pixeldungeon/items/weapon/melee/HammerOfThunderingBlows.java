/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class HammerOfThunderingBlows extends MeleeWeapon {

	{
		name = "Hammer of Thundering Blows";
		image = ItemSpriteSheet.WAR_HAMMER;
	}

	public HammerOfThunderingBlows() {
		super( 5, 1.0f, 1.3f );  // Slow but powerful
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 25% chance for bonus thunder damage
		if (Random.Int(4) == 0) {
			int bonusDamage = Random.Int(10, 20);
			defender.damage(bonusDamage, this);
		}
	}

	@Override
	public String desc() {
		return
			"This massive warhammer was gifted by Karana, the Rainkeeper, to his most devoted followers. The hammer's " +
			"head is carved from solid stormstone, a rare mineral found only in the highest peaks during the fiercest " +
			"lightning storms. Each strike produces a thunderous boom that echoes across the battlefield. Bruisers " +
			"and warriors favor this weapon for its ability to stun and disorient enemies with sheer concussive force. " +
			"Runes of power are etched along the haft, glowing faintly with electrical energy. When swung with full " +
			"force, the hammer can produce actual lightning arcs that leap to nearby foes. The weapon is surprisingly " +
			"balanced despite its size, a testament to dwarven craftsmanship enhanced by divine blessing. Those struck " +
			"by this hammer report feeling as though they've been hit by a thunderbolt itself.";
	}
}
