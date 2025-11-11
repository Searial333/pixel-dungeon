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

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Cripple;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.sprites.BruteSprite; // Temporary sprite
import com.watabou.utils.Random;

public class StormGiant extends Mob {

	{
		name = "storm giant";
		spriteClass = BruteSprite.class; // TODO: Create GiantSprite

		HP = HT = 50;
		defenseSkill = 14;

		EXP = 10;
		maxLvl = 18;

		loot = Gold.class;
		lootChance = 0.7f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 10, 18 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 22;
	}

	@Override
	public int dr() {
		return 10;
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		// Giants can stun/cripple with their massive blows
		if (Random.Int( 4 ) == 0) {
			Buff.prolong( enemy, Cripple.class, Cripple.DURATION );
		}

		return damage;
	}

	@Override
	public String description() {
		return
			"Storm giants are massive humanoids that tower over normal mortals, standing 15-20 feet tall. " +
			"These powerful beings dwell in the mountainous regions of Norrath, particularly in the Thundering Steppes " +
			"where they commune with the storms. Giants are incredibly strong and resilient, capable of crushing " +
			"lesser creatures with a single blow. They wield enormous weapons and wear armor forged from " +
			"materials that would be impossibly heavy for normal races. Storm giants are intelligent but " +
			"territorial, attacking any who trespass in their domains. Their thunderous footsteps can be " +
			"heard from great distances, giving wise adventurers time to flee.";
	}
}
