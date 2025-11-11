/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Vertigo;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class MindBlade extends MeleeWeapon {

	{
		name = "Mind Blade";
		image = ItemSpriteSheet.DAGGER;
	}

	public MindBlade() {
		super( 3, 1.0f, 1.2f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 25% chance to confuse/vertigo the enemy (mental attack)
		if (Random.Int(4) == 0) {
			Buff.prolong(defender, Vertigo.class, 5f);
		}
	}

	@Override
	public String desc() {
		return
			"The Mind Blade is not truly a physical weapon, but rather a crystallized thought given form. " +
			"Created by the Illusionist Academy in Freeport, this dagger appears to shimmer and shift, never quite looking " +
			"the same twice. It cuts through mental defenses as easily as physical ones, leaving victims confused and " +
			"disoriented. Illusionists and Coercers use this weapon to devastating effect, as each strike assaults both body " +
			"and mind. Those wounded by the Mind Blade report vivid hallucinations and memory loss. The weapon is said to " +
			"grow sharper the more intelligent its wielder, feeding on psychic energy.";
	}
}
