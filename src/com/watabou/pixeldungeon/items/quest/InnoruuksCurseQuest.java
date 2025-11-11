/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.quest;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.weapon.melee.InnoruuksCurse;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Innoruuk's Curse Heritage Quest
 * Dark quest chain to obtain the shadowknight's cursed blade
 */
public class InnoruuksCurseQuest extends HeritageQuest {

	{
		name = "Innoruuk's Curse";
		image = ItemSpriteSheet.SCROLL_HOLDER;
	}

	public InnoruuksCurseQuest() {
		super();
		questName = "Innoruuk's Curse";
		questDescription = "Seek the favor of Innoruuk, the Prince of Hate, by performing dark deeds in his name. " +
		                   "His cursed blade waits for a shadowknight worthy of wielding its terrible power. " +
		                   "Only through blood and betrayal can you prove yourself to the dark god.";
		totalSteps = 6;
	}

	@Override
	public String getCurrentObjective() {
		switch (currentStep) {
			case 0: return "Speak with Lucan D'Lere in the Freeport Militia House";
			case 1: return "Slay 20 innocent creatures to prove your darkness";
			case 2: return "Collect the Unholy Symbol from the depths of Crushbone";
			case 3: return "Defeat Lord Darish the Fallen and claim his corrupted soul";
			case 4: return "Perform the Ritual of Hate at the Altar of Innoruuk";
			case 5: return "Receive Innoruuk's blessing and claim the cursed blade";
			default: return "Quest Complete";
		}
	}

	@Override
	public boolean canAdvanceStep(Hero hero) {
		// Simplified - in a real implementation, would check inventory and kill counts
		return true;
	}

	@Override
	protected void onQuestComplete(Hero hero) {
		// Award Innoruuk's Curse
		InnoruuksCurse sword = new InnoruuksCurse();
		if (sword.doPickUp(hero)) {
			GLog.n("Dark power flows through you! You have obtained Innoruuk's Curse!");
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
