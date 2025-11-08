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
import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class FieryAvenger extends MeleeWeapon {

	{
		name = "Fiery Avenger";
		image = ItemSpriteSheet.SWORD;
	}

	public FieryAvenger() {
		super( 5, 1.0f, 1.2f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 25% chance to set enemy on fire
		if (Random.Int(4) == 0) {
			Buff.affect(defender, Burning.class).reignite(defender);
		}
	}

	@Override
	public String desc() {
		return
			"The legendary Fiery Avenger, a sword wreathed in eternal flames. Forged in the heart of Lavastorm, " +
			"this blade was wielded by the Paladin hero Soulfire in his battle against the forces of Innoruuk. " +
			"Its flames burn evil with holy fire, and it is said that only those pure of heart can wield its power. " +
			"The sword occasionally bursts into flame when striking foes, burning them with righteous fury.";
	}
}
