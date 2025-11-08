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
import com.watabou.pixeldungeon.items.food.MysteryMeat;
import com.watabou.pixeldungeon.sprites.GnollSprite; // Temporary sprite, would need OrcSprite
import com.watabou.utils.Random;

public class Orc extends Mob {

	{
		name = "Crushbone orc";
		spriteClass = GnollSprite.class; // TODO: Create OrcSprite

		HP = HT = 18;
		defenseSkill = 6;

		EXP = 3;
		maxLvl = 10;

		loot = Gold.class;
		lootChance = 0.6f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 3, 7 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 13;
	}

	@Override
	public int dr() {
		return 3;
	}

	@Override
	public String description() {
		return
			"The orcs of Crushbone are a militant and organized force led by the infamous Emperor Crush. " +
			"These green-skinned warriors occupy the ruins of Castle Crushbone in the Greater Faydark, " +
			"where they train for war and plot their conquests. They are formidable foes, skilled in both " +
			"melee combat and military tactics.";
	}
}
