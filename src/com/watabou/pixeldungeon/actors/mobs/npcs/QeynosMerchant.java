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

public class QeynosMerchant extends Shopkeeper {

	{
		name = "Bayle's Merchant";
		spriteClass = ShopkeeperSprite.class;
	}

	@Override
	public String description() {
		return
			"This well-dressed merchant represents the trading guilds of Qeynos, the shining city of light. " +
			"Licensed by Qeynos Harbor and bearing the seal of Antonia Bayle herself, this merchant travels " +
			"the dangerous wilderness to bring goods to brave adventurers. Despite the perils, he maintains " +
			"a cheerful demeanor and fair prices. The merchants of Qeynos are known throughout Norrath for " +
			"their honesty and quality wares - a stark contrast to the cutthroat dealers of Freeport. " +
			"He's always eager to trade with heroes fighting against the darkness.";
	}
}
