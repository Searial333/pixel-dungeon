/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.potions;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.utils.GLog;

public class PotionOfStamina extends Potion {

	{
		name = "Potion of Enduring Stamina";
	}

	@Override
	protected void apply( Hero hero ) {
		setKnown();
		hero.staminaPool = hero.maxStaminaPool;
		GLog.p( "Vigor courses through your muscles! Stamina fully restored!" );
	}

	@Override
	public String desc() {
		return
			"This red-orange potion bubbles with physical energy, created by the battle priests of Rallos Zek's temple. " +
			"The mixture contains ground roots from the Plane of War, honey from giant warrior bees, and the blood of " +
			"strong beasts. When drunk, it tastes like iron and cinnamon, burning slightly as it goes down. The potion " +
			"instantly refreshes tired muscles and restores physical energy, allowing warriors and scouts to continue " +
			"fighting long after they should have collapsed from exhaustion. Fighters prize this potion for its ability " +
			"to turn the tide of battle when they're running low on stamina for their combat abilities. The recipe is " +
			"closely guarded by barbarian alchemists in Halas, passed down through generations of brewmasters. Side effects " +
			"include increased heart rate and a temporary boost to physical reflexes.";
	}

	@Override
	public int price() {
		return isKnown() ? 150 * quantity : super.price();
	}
}
