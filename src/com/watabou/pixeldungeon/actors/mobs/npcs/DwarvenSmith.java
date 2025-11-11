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
package com.watabou.pixeldungeon.actors.mobs.npcs;

import com.watabou.pixeldungeon.sprites.BlacksmithSprite;

public class DwarvenSmith extends Blacksmith {

	{
		name = "Dwarven Blacksmith";
		spriteClass = BlacksmithSprite.class;
	}

	@Override
	public String description() {
		return
			"This stout dwarf hails from the forges of Kaladim, the ancient dwarven city carved deep into " +
			"the Butcherblock Mountains. Dwarven smiths are renowned throughout Norrath as the finest craftsmen " +
			"of weapons and armor. Their work is rivaled only by the gnomish tinkerers of Ak'Anon. This particular " +
			"smith has ventured from his mountain halls, seeking rare ores and the thrill of adventure. His hands " +
			"are calloused from years at the forge, and his beard is singed from working with molten metal. " +
			"For the right price - and the right materials - he can reforge your equipment to even greater strength. " +
			"By Brell's beard, there's no finer smith in all the lands!";
	}
}
