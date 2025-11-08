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
import com.watabou.pixeldungeon.sprites.RatSprite; // Temporary sprite
import com.watabou.utils.Random;

public class Kobold extends Mob {

	{
		name = "Stormhold kobold";
		spriteClass = RatSprite.class; // TODO: Create KoboldSprite

		HP = HT = 10;
		defenseSkill = 4;

		EXP = 1;
		maxLvl = 6;

		loot = Gold.class;
		lootChance = 0.4f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 4 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 10;
	}

	@Override
	public int dr() {
		return 1;
	}

	@Override
	public String description() {
		return
			"Kobolds are small, reptilian humanoids that infest the dungeons of Stormhold and the Commonlands. " +
			"Cowardly when alone, they become dangerous in packs, overwhelming foes with sheer numbers. " +
			"They hoard treasure in their warrens and are known for their primitive traps and ambushes.";
	}
}
