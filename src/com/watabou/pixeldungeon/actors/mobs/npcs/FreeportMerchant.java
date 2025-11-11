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

import com.watabou.pixeldungeon.sprites.ShopkeeperSprite;

public class FreeportMerchant extends Shopkeeper {

	{
		name = "Freeport Black Market Dealer";
		spriteClass = ShopkeeperSprite.class;
	}

	@Override
	public String description() {
		return
			"This shifty-eyed merchant operates under the authority of the Freeport Militia, though 'authority' " +
			"is a loose term in the city of the Overlord. Freeport dealers are known for selling items of... " +
			"questionable origin. No questions asked, no answers given. The Overlord's agents turn a blind eye " +
			"to the black market trade as long as they get their cut. This merchant deals in weapons, armor, " +
			"and supplies that may have 'fallen off a caravan' in the Commonlands. His prices are steep, but " +
			"in these dangerous times, beggars can't be choosers. Just don't ask where the blood stains came from.";
	}
}
