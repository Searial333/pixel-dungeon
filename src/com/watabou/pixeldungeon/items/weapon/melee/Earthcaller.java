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
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Roots;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class Earthcaller extends MeleeWeapon {

	{
		name = "Earthcaller";
		image = ItemSpriteSheet.QUARTERSTAFF;
	}

	public Earthcaller() {
		super( 4, 1.1f, 1.1f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// Chance to root enemies in place with nature's power
		if (Random.Int(3) == 0) {
			Buff.prolong(defender, Roots.class, Roots.DURATION);
		}
	}

	@Override
	public String desc() {
		return
			"Earthcaller is the sacred weapon of shamans who have proven themselves worthy through trials of " +
			"spirit and nature. This staff is carved from an ancient oak that grew in the Enchanted Lands, " +
			"and it pulses with the raw power of the earth itself. Shamans who wield Earthcaller can call upon " +
			"the spirits of stone and soil to bind their enemies in place, entangling them with roots and " +
			"earthen power. The staff is adorned with totems and fetishes, each representing a spirit the shaman " +
			"has communed with. It is said that Earthcaller chooses its wielder as much as the wielder chooses it, " +
			"and those deemed unworthy will find the staff heavy and unresponsive.";
	}
}
