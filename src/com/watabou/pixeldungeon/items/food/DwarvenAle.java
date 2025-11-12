/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.food;

import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

public class DwarvenAle extends Food {

	{
		name = "Dwarven Ale";
		image = ItemSpriteSheet.POTION_AZURE;
		energy = Hunger.STARVING * 0.5f;  // Not very filling, it's a drink
	}

	@Override
	protected void satisfy( Hero hero ) {
		super.satisfy( hero );
		// Temporary durability boost
		hero.durability += 1;
		// Small stamina restore
		hero.staminaPool = Math.min(hero.maxStaminaPool, hero.staminaPool + 20);
		GLog.p( "The strong ale warms your belly, increasing durability temporarily and restoring stamina!" );
	}

	@Override
	public String info() {
		return
			"This is genuine dwarven ale from the breweries of Kaladim, renowned throughout Norrath for its quality and " +
			"potency. Brewed in ancient copper vats using secret recipes passed down for generations, this ale is dark, " +
			"rich, and strong enough to make even a barbarian stumble. The dwarves use underground spring water, rare " +
			"hops from volcanic soil, and aged barley to create this legendary beverage. When drunk, the ale provides " +
			"not just liquid courage but actual physical resilience—dwarven warriors swear that a good ale makes their " +
			"skin tougher and their stamina greater. The brew has a complex flavor profile with notes of caramel, smoke, " +
			"and minerals from the deep earth. Dwarves drink this daily with their meals, but outsiders often find it " +
			"overwhelming on first taste. A barrel of Kaladim's finest is worth its weight in gold to connoisseurs.";
	}

	@Override
	public int price() {
		return 75 * quantity;
	}
}
