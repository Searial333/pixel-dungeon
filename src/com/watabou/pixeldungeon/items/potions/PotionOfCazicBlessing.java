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

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Invisibility;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.utils.GLog;

public class PotionOfCazicBlessing extends Potion {

	{
		name = "Potion of Cazic's Blessing";
	}

	@Override
	protected void apply( Hero hero ) {
		setKnown();
		// Temporarily increase all stats
		hero.durability += 2;
		hero.mysticism += 2;
		hero.skill += 2;
		hero.presence += 2;
		GLog.p( "You feel the fearsome power of Cazic-Thule flow through you!" );
		GLog.i( "All stats temporarily increased!" );
	}

	@Override
	public String desc() {
		return
			"This dark, swirling potion contains the essence of fear itself, blessed by Cazic-Thule, the God of Fear. " +
			"Despite its ominous origins, the potion grants temporary power to those brave enough to consume it. " +
			"Alchemists of the dark arts brew this concoction using ingredients harvested from the Plane of Fear. " +
			"When consumed, the drinker feels a surge of primal power as terror becomes strength. The effect is " +
			"temporary but potent, enhancing all aspects of the drinker's capabilities. Warriors report feeling " +
			"invincible, mages find their spells amplified, and scouts move with supernatural agility.";
	}

	@Override
	public int price() {
		return isKnown() ? 100 * quantity : super.price();
	}
}
