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
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public class EpicArmor extends Armor {

	{
		name = "Crafted Mastwork";
		image = ItemSpriteSheet.ARMOR_PLATE;
	}

	public EpicArmor() {
		super( 6 );
	}

	@Override
	public String desc() {
		return
			"This masterwork armor represents the pinnacle of Norrathian craftsmanship. Forged by legendary " +
			"armorers in the workshops of Qeynos and Freeport, each plate is perfectly fitted and inscribed " +
			"with protective runes. The metal gleams with an otherworldly sheen, and the joints move with " +
			"surprising fluidity despite the armor's impressive protective qualities. Adventurers who wear " +
			"such armor are marked as heroes of renown, having earned their equipment through great deeds " +
			"or considerable wealth.";
	}
}
