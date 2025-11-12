/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.food;

import com.watabou.pixeldungeon.actors.buffs.Hunger;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

public class HalflingPipeweed extends Food {

	{
		name = "Halfling Pipeweed";
		image = ItemSpriteSheet.SEED_ICECAP;
		energy = Hunger.STARVING * 0.3f;  // Not filling at all
	}

	@Override
	protected void satisfy( Hero hero ) {
		super.satisfy( hero );
		// Increase luck/skill temporarily
		hero.skill += 2;
		GLog.p( "You enjoy a relaxing smoke. Your mind clears and your reflexes sharpen! (+2 Skill)" );
	}

	@Override
	public String info() {
		return
			"This is premium halfling pipeweed from the Shire, cultivated by the finest halfling gardeners in Rivervale. " +
			"The tobacco plant grows in rich soil fertilized by the River of Gold's silt, giving it a unique sweet and " +
			"earthy flavor. Halflings smoke pipeweed throughout the day, claiming it helps them think clearly and move " +
			"more nimbly. Indeed, those who partake report sharper reflexes and better hand-eye coordination, though " +
			"scholars debate whether this is pharmacological or simply relaxation. The weed is dried and cured for exactly " +
			"three months, then stored in leather pouches to preserve freshness. Halflings consider pipeweed an essential " +
			"part of their culture, with different blends for different occasions—this appears to be 'Old Toby,' known " +
			"for its calming effects and boost to dexterity. Non-halflings often underestimate pipeweed until they try it, " +
			"then become devoted fans of the leaf.";
	}

	@Override
	public int price() {
		return 40 * quantity;
	}
}
