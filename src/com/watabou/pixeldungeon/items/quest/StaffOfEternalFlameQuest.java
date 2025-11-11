/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.quest;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.weapon.melee.StaffOfEternalFlame;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Staff of Eternal Flame Heritage Quest
 * Wizard quest chain to obtain the ultimate fire staff
 */
public class StaffOfEternalFlameQuest extends HeritageQuest {

	{
		name = "The Staff of Eternal Flame";
		image = ItemSpriteSheet.SCROLL_HOLDER;
	}

	public StaffOfEternalFlameQuest() {
		super();
		questName = "The Staff of Eternal Flame";
		questDescription = "Master the element of fire by seeking out the legendary staff of Solusek Ro, the Burning Prince. " +
		                   "Hidden in the heart of Lavastorm, this staff contains the essence of elemental fire itself. " +
		                   "Only a wizard who has proven their mastery over flame magic can wield this devastating weapon.";
		totalSteps = 5;
	}

	@Override
	public String getCurrentObjective() {
		switch (currentStep) {
			case 0: return "Study ancient fire magic with Archmage Theldyn in the Mage Tower";
			case 1: return "Collect 5 Essence of Fire from Lavastorm elementals";
			case 2: return "Defeat the Fire Drake Matriarch on depth 18";
			case 3: return "Retrieve the Heartstone of Solusek from her hoard";
			case 4: return "Complete the Ritual of Flames at the Altar of Solusek Ro";
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
		// Award the Staff of Eternal Flame
		StaffOfEternalFlame staff = new StaffOfEternalFlame();
		if (staff.doPickUp(hero)) {
			GLog.p("Eternal flames dance at your command! You have obtained the Staff of Eternal Flame!");
			Badges.validateItemLevelAquired(staff);
		} else {
			hero.belongings.drop(staff, hero.pos);
		}
	}

	@Override
	public String desc() {
		return info();
	}
}
