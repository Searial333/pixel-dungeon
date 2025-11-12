/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.potions;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.utils.GLog;

public class PotionOfManaRestoration extends Potion {

	{
		name = "Potion of Mana Restoration";
	}

	@Override
	protected void apply( Hero hero ) {
		setKnown();
		hero.manaPool = hero.maxManaPool;
		GLog.p( "Arcane energy floods your body! Mana fully restored!" );
	}

	@Override
	public String desc() {
		return
			"This shimmering blue potion is infused with pure arcane energy, distilled by the mages of the Concordium. " +
			"The liquid glows faintly and seems to move on its own within the bottle, swirling in patterns that resemble " +
			"spell formulas. When consumed, it instantly restores all expended mana, making it invaluable to wizards and " +
			"priests in extended battles. The potion tastes like lightning and starlight, leaving a tingling sensation " +
			"on the tongue. Alchemists create this by condensing leyline energy during lunar eclipses, making it rare and " +
			"expensive. The bottle itself is enchanted to prevent the volatile magical contents from evaporating. Many " +
			"adventuring mages keep several of these potions on hand for emergencies, knowing that running out of mana " +
			"in a dungeon often means death.";
	}

	@Override
	public int price() {
		return isKnown() ? 150 * quantity : super.price();
	}
}
