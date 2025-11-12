/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class SpearOfTheSeaKings extends MeleeWeapon {

	{
		name = "Spear of the Sea Kings";
		image = ItemSpriteSheet.SPEAR;
	}

	public SpearOfTheSeaKings() {
		super( 4, 1.1f, 1.1f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 30% chance for bonus water damage
		if (Random.Int(10) < 3) {
			int waterDamage = Random.Int(5, 15);
			defender.damage(waterDamage, this);
		}
	}

	@Override
	public String desc() {
		return
			"Forged in the underwater city of Prexus, this spear was wielded by the ancient sea kings who ruled the " +
			"oceans before the Age of Scale. The spearhead is crafted from pure coral-steel, a material that can only " +
			"be shaped by those blessed by Prexus, god of the oceans. The shaft appears to be made of polished " +
			"driftwood, but it's as hard as iron and never rots. Intricate wave patterns are carved into the metal, " +
			"and the spear seems to shimmer as if underwater even in dry air. When thrust, the spear moves with the " +
			"force of a tidal wave, and enemies report feeling as though they're drowning when struck. Fishermen and " +
			"sailors tell tales of sea kings rising from the depths wielding these spears to defend their territory. " +
			"The weapon is equally effective on land or in water, making it prized by adventurers who explore both " +
			"realms. Small barnacles grow along the shaft but never impede its use.";
	}
}
