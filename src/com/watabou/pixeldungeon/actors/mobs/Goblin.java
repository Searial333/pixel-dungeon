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
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.sprites.GnollSprite; // Temporary sprite
import com.watabou.utils.Random;

public class Goblin extends Mob {

	{
		name = "Runnyeye goblin";
		spriteClass = GnollSprite.class; // TODO: Create GoblinSprite

		HP = HT = 16;
		defenseSkill = 6;

		EXP = 3;
		maxLvl = 9;

		loot = Gold.class;
		lootChance = 0.55f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 2, 6 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 12;
	}

	@Override
	public int dr() {
		return 2;
	}

	@Override
	public String description() {
		return
			"The goblins of Runnyeye Citadel are cunning and vicious creatures who have made the old dwarven " +
			"fortress their home. Led by their king, these green-skinned humanoids are master trappers and " +
			"ambushers. They hoard treasure stolen from merchants and adventurers, filling their warrens with " +
			"ill-gotten gold. Goblins fight dirty, using poison, traps, and superior numbers to overcome stronger foes. " +
			"They are slightly more intelligent than their kobold cousins but just as greedy and cowardly.";
	}
}
