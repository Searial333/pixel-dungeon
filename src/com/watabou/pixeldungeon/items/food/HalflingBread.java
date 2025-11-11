/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.food;

import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

public class HalflingBread extends Food {

	{
		name = "Halfling's Bread";
		image = ItemSpriteSheet.PASTY;
		energy = Hunger.STARVING * 0.8f;  // Very filling
	}

	@Override
	protected void satisfy( Hero hero ) {
		super.satisfy( hero );

		// Halfling bread is extra nourishing
		hero.HP = Math.min( hero.HP + 10, hero.HT );
		GLog.p( "The delicious halfling bread fills you with warmth and comfort!" );
	}

	@Override
	public String info() {
		return
			"This delightful bread is baked using traditional halfling recipes passed down through generations " +
			"in Rivervale. Halflings are renowned throughout Norrath for their culinary skills, and their bread " +
			"is particularly famous for being hearty, filling, and delicious. The loaf is still warm and gives off " +
			"an aroma of honey and herbs. Eating halfling bread not only satisfies hunger but also provides a " +
			"measure of healing, as the wholesome ingredients restore vitality. Many adventurers stock up on " +
			"halfling bread before embarking on dangerous quests.";
	}

	@Override
	public int price() {
		return 15 * quantity;
	}
}
