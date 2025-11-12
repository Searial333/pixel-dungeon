/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Chill;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class FrostReaver extends MeleeWeapon {

	{
		name = "Frostreaver";
		image = ItemSpriteSheet.SWORD;
	}

	public FrostReaver() {
		super( 5, 1.0f, 1.1f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 35% chance to chill
		if (Random.Int(100) < 35) {
			Buff.prolong(defender, Chill.class, 3f);
		}
	}

	@Override
	public String desc() {
		return
			"Frostreaver is a blade forged in the frozen peaks of Everfrost. The sword's metal is permanently " +
			"cold to the touch, covered in a thin layer of frost that never melts. Legend says it was crafted by " +
			"the frost giants of Permafrost in an age before the Rending, using ice from the heart of Lady Vox's lair. " +
			"Warriors who wield this blade find their enemies slowed and chilled by each strike, making it a favorite " +
			"weapon for those who face fast-moving foes. The pommel is carved from a single piece of blue ice that " +
			"never thaws, and the crossguard resembles icicles. Monks of the Silent Fist monastery prize this weapon " +
			"for its balance and elemental properties.";
	}
}
