/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.quest;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Item;

/**
 * HeritageQuest - EverQuest II's signature multi-step quest system
 * These legendary quests require collecting specific items and defeating bosses
 * to earn powerful rewards
 */
public abstract class HeritageQuest extends Item {

	protected String questName;
	protected String questDescription;
	protected int currentStep;
	protected int totalSteps;
	protected boolean completed;

	public HeritageQuest() {
		stackable = false;
		unique = true;
		currentStep = 0;
		completed = false;
	}

	/**
	 * Check if the player can advance to the next step
	 */
	public abstract boolean canAdvanceStep(Hero hero);

	/**
	 * Advance to the next quest step
	 */
	public boolean advanceStep(Hero hero) {
		if (canAdvanceStep(hero)) {
			currentStep++;
			if (currentStep >= totalSteps) {
				completed = true;
				onQuestComplete(hero);
				return true;
			}
			return true;
		}
		return false;
	}

	/**
	 * Called when the quest is completed
	 */
	protected abstract void onQuestComplete(Hero hero);

	/**
	 * Get the current objective description
	 */
	public abstract String getCurrentObjective();

	public boolean isCompleted() {
		return completed;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public int getTotalSteps() {
		return totalSteps;
	}

	@Override
	public String info() {
		StringBuilder info = new StringBuilder();
		info.append(questName).append("\n\n");
		info.append(questDescription).append("\n\n");

		if (completed) {
			info.append("Quest Completed!");
		} else {
			info.append("Progress: Step ").append(currentStep + 1).append(" of ").append(totalSteps).append("\n");
			info.append("Current Objective: ").append(getCurrentObjective());
		}

		return info.toString();
	}

	@Override
	public String toString() {
		return questName + " (" + (currentStep + 1) + "/" + totalSteps + ")";
	}
}
