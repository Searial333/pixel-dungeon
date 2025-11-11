/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.blobs.ToxicGas;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.items.potions.PotionOfHealing;
import com.watabou.pixeldungeon.sprites.ShamanSprite; // Temporary sprite
import com.watabou.utils.Random;

public class Treant extends Mob {

	{
		name = "ancient treant";
		spriteClass = ShamanSprite.class; // TODO: Create TreantSprite

		HP = HT = 45;
		defenseSkill = 12;

		EXP = 9;
		maxLvl = 16;

		loot = new PotionOfHealing();
		lootChance = 0.25f;

		immunities.add( Poison.class );
		immunities.add( ToxicGas.class );
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 8, 15 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 20;
	}

	@Override
	public int dr() {
		return 12;
	}

	@Override
	public String description() {
		return
			"Treants are sentient tree-folk, ancient guardians of the forests of Norrath. These massive beings " +
			"appear as gnarled, walking trees with faces carved into their bark and limbs that can crush stone. " +
			"Found primarily in the Enchanted Lands and Nektulos Forest, treants are normally peaceful, tending " +
			"to the natural order. However, they become fiercely aggressive when their forests are threatened. " +
			"Druids and Rangers revere treants as symbols of nature's power. Their wooden bodies are resistant " +
			"to poison and toxins, and they possess incredible strength. When slain, treants sometimes leave " +
			"behind potent healing saps that can restore vitality to those who consume them.";
	}
}
