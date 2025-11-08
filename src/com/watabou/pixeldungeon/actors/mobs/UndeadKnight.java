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
import com.watabou.pixeldungeon.actors.blobs.ToxicGas;
import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.actors.buffs.Paralysis;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.items.weapon.enchantments.Death;
import com.watabou.pixeldungeon.sprites.SkeletonSprite;
import com.watabou.utils.Random;

public class UndeadKnight extends Mob {

	{
		name = "undead knight";
		spriteClass = SkeletonSprite.class;

		HP = HT = 28;
		defenseSkill = 10;

		EXP = 6;
		maxLvl = 14;

		loot = new Death();
		lootChance = 0.1f;

		immunities.add( Death.class );
		immunities.add( Poison.class );
		immunities.add( ToxicGas.class );
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 6, 12 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 18;
	}

	@Override
	public int dr() {
		return 8;
	}

	@Override
	public String description() {
		return
			"These cursed warriors were once noble knights who fell in battle defending Norrath. " +
			"Raised by dark necromancy, they now serve the forces of undeath with the same martial " +
			"prowess they possessed in life. Clad in rusted armor and wielding spectral weapons, " +
			"they patrol the graveyards and crypts of Norrath, slaying any who dare disturb their eternal vigil.";
	}
}
