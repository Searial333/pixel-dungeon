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
package com.watabou.pixeldungeon.items.potions;

import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.utils.GLog;

public class PotionOfDragonfire extends PotionOfLiquidFlame {

	{
		name = "Potion of Dragonfire";
	}

	@Override
	public String desc() {
		return
			"This volatile potion contains concentrated dragonfire, harvested from the breath of Lavastorm drakes. " +
			"Alchemists risk their lives venturing into dragon lairs to collect the rare components needed for this brew. " +
			"When the flask shatters, it releases a burst of flame hot enough to melt steel, incinerating everything " +
			"in the vicinity. The fire burns with the same intensity as a dragon's breath weapon, and even the bravest " +
			"warriors fear its power. Some say a drop of Lord Nagafen's own flame is used in the most potent batches. " +
			"This potion is equally useful for destroying enemy hordes or creating an impassable wall of fire.";
	}

	@Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
