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
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class WaterSprinkler extends MeleeWeapon {

	{
		name = "Water Sprinkler of Nem Ankh";
		image = ItemSpriteSheet.MACE;
	}

	public WaterSprinkler() {
		super( 4, 0.9f, 1.3f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// Heals attacker for a small amount when striking undead
		if (attacker instanceof Hero && defender.name != null &&
		    (defender.name.toLowerCase().contains("skeleton") ||
		     defender.name.toLowerCase().contains("undead") ||
		     defender.name.toLowerCase().contains("zombie"))) {
			Hero hero = (Hero) attacker;
			int heal = damage / 4;
			if (heal > 0) {
				hero.HP = Math.min(hero.HT, hero.HP + heal);
			}
		}
	}

	@Override
	public String desc() {
		return
			"The Water Sprinkler of Nem Ankh is a legendary mace wielded by the most devoted clerics of Norrath. " +
			"Blessed by the waters of the sacred Nem Ankh temple, this weapon shimmers with divine radiance. " +
			"It is particularly effective against the undead, the holy water infused within its metal purifying " +
			"corrupted flesh and restoring vitality to its wielder. Clerics who have completed their epic quest " +
			"are granted this weapon as a symbol of their faith and dedication to the gods of light. " +
			"The mace hums with divine power, eager to smite the forces of darkness and heal the faithful.";
	}
}
