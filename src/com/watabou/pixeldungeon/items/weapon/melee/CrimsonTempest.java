/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Bleeding;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class CrimsonTempest extends MeleeWeapon {

	{
		name = "Crimson Tempest";
		image = ItemSpriteSheet.DAGGER;
	}

	public CrimsonTempest() {
		super( 4, 1.0f, 1.3f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 40% chance to cause bleeding
		if (Random.Int(10) < 4) {
			Buff.affect(defender, Bleeding.class).set(damage);
		}
	}

	@Override
	public String desc() {
		return
			"Crimson Tempest is the signature blade of the Assassin's Guild of Freeport. Its edge is so impossibly sharp " +
			"that victims don't realize they've been cut until they see their own blood pooling beneath them. The blade " +
			"is stained a permanent crimson from countless kills, and no amount of cleaning can remove the color. " +
			"Assassins who wield this dagger are known for leaving a trail of exsanguinated corpses in their wake. " +
			"The weapon's name comes from the whirlwind of arterial spray it creates when wielded by a master. " +
			"It is said that the blade thirsts for blood and will cut its own wielder if not fed regularly.";
	}
}
