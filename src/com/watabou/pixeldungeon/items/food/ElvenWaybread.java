/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.food;

import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

public class ElvenWaybread extends Food {

	{
		name = "Elven Waybread";
		image = ItemSpriteSheet.RATION;
		energy = Hunger.STARVING * 1.5f;  // Very filling
	}

	@Override
	protected void satisfy( Hero hero ) {
		super.satisfy( hero );
		// Small HP heal
		int heal = hero.HT / 5;
		hero.HP = Math.min(hero.HT, hero.HP + heal);
		GLog.p( "The magical waybread nourishes both body and spirit, healing %d HP!", heal );
	}

	@Override
	public String info() {
		return
			"This is elven waybread, also called lembas by the wood elves of Faydark. The bread is made from ancient " +
			"grains grown in clearings blessed by Tunare, kneaded with water from holy springs, and baked under moonlight. " +
			"A single piece is enough to sustain an adventurer for an entire day, and it never spoils no matter how long " +
			"it's carried. The bread has a sweet, wholesome taste and leaves a pleasant aftertaste of honey and herbs. " +
			"Beyond mere sustenance, waybread contains restorative magic that heals minor wounds and eases weariness. " +
			"Elves bake this bread for long journeys and gift it to trusted friends, wrapping each piece in silver-green " +
			"leaves. The recipe is a closely guarded secret of the Faydark bakers, though some humans and halflings have " +
			"managed to create inferior imitations.";
	}

	@Override
	public int price() {
		return 50 * quantity;
	}
}
