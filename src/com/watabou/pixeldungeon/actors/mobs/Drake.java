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
import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfRecharging;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.mechanics.Ballistica;
import com.watabou.pixeldungeon.sprites.BatSprite; // Temporary sprite
import com.watabou.utils.Random;

public class Drake extends Mob {

	{
		name = "lavastorm drake";
		spriteClass = BatSprite.class; // TODO: Create DrakeSprite

		HP = HT = 35;
		defenseSkill = 12;

		EXP = 8;
		maxLvl = 16;

		flying = true;

		loot = new ScrollOfRecharging();
		lootChance = 0.15f;

		immunities.add( Burning.class );
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 8, 14 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 20;
	}

	@Override
	public int dr() {
		return 6;
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		if (Random.Int( 3 ) == 0) {
			Buff.affect( enemy, Burning.class ).reignite( enemy );
		}

		return damage;
	}

	@Override
	public String description() {
		return
			"Drakes are lesser dragons that inhabit the volcanic regions of Norrath, particularly around Lavastorm. " +
			"Though smaller than their true dragon cousins, these winged reptiles are still formidable predators. " +
			"They breathe fire and have thick, scaly hides resistant to heat. Drakes are territorial and aggressive, " +
			"guarding their nests and hoards with fierce determination. Many adventurers have fallen to their fiery breath.";
	}
}
