/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.quest;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.weapon.melee.FieryAvenger;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * The Fiery Avenger Heritage Quest
 * Classic EQ2 quest chain to obtain the legendary paladin sword
 */
public class FieryAvengerQuest extends HeritageQuest {

	{
		name = "The Fiery Avenger";
		image = ItemSpriteSheet.SCROLL_HOLDER;
	}

	public FieryAvengerQuest() {
		super();
		questName = "The Fiery Avenger";
		questDescription = "Seek the legendary sword of Soulfire, the paladin hero who fell defending Qeynos. " +
		                   "His blade, the Fiery Avenger, was lost in the depths of Lavastorm. Only a true paladin " +
		                   "of pure heart can reclaim this holy weapon and reignite its eternal flames.";
		totalSteps = 5;
	}

	@Override
	public String getCurrentObjective() {
		switch (currentStep) {
			case 0: return "Find Priest Tomlin in Qeynos and learn of Soulfire's sacrifice";
			case 1: return "Collect 10 Red Dragon Scales from Lavastorm drakes";
			case 2: return "Defeat Lord Nagafen in his lair (Depth 20)";
			case 3: return "Retrieve the Broken Sword Hilt from Nagafen's hoard";
			case 4: return "Return to the Temple of Life for the sword's reforging";
			default: return "Quest Complete";
		}
	}

	@Override
	public boolean canAdvanceStep(Hero hero) {
		// Simplified - in a real implementation, would check inventory for quest items
		return true;
	}

	@Override
	protected void onQuestComplete(Hero hero) {
		// Award the Fiery Avenger
		FieryAvenger sword = new FieryAvenger();
		if (sword.doPickUp(hero)) {
			GLog.p("The flames of righteousness ignite! You have obtained the Fiery Avenger!");
			Badges.validateItemLevelAquired(sword);
		} else {
			hero.belongings.drop(sword, hero.pos);
		}
	}

	@Override
	public String desc() {
		return info();
	}
}
