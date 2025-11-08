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

public class InnoruuksCurse extends MeleeWeapon {

	{
		name = "Innoruuk's Curse";
		image = ItemSpriteSheet.SWORD;
		cursed = true;
	}

	public InnoruuksCurse() {
		super( 5, 1.2f, 1.0f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// Chance to drain health from enemy and give to wielder
		if (Random.Int(3) == 0 && attacker instanceof Hero) {
			int drain = damage / 3;
			if (drain > 0) {
				Hero hero = (Hero) attacker;
				hero.HP = Math.min(hero.HT, hero.HP + drain);
			}
		}
	}

	@Override
	public String desc() {
		return
			"This dark blade radiates malevolence and bears the mark of Innoruuk, the God of Hate. " +
			"Favored weapon of Shadowknights, this cursed sword drains the life force of those it strikes " +
			"and transfers it to its wielder. The blade whispers promises of power, but at what cost? " +
			"Many who have wielded Innoruuk's Curse have fallen to darkness, consumed by the hatred " +
			"that flows through the weapon. The sword cannot be dropped once equipped, as if bound by dark magic.";
	}
}
