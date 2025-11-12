/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 *
 * Hero Stat Calculator - Centralizes all stat calculations
 */
package com.watabou.pixeldungeon.actors.hero;

import com.watabou.pixeldungeon.GameBalance;

public class HeroStatCalculator {

	/**
	 * Calculate total durability including all bonuses
	 */
	public static int calculateTotalDurability(Hero hero) {
		int total = hero.baseDurability;

		// Racial bonus
		if (hero.heroRace != null) {
			total += hero.heroRace.getDurabilityBonus();
		}

		// Deity bonus
		if (hero.deity != null) {
			total += hero.deity.getDurabilityBonus();
		}

		// TODO: Add equipment bonuses
		// TODO: Add buff bonuses

		return Math.max(0, total);
	}

	/**
	 * Calculate total mysticism including all bonuses
	 */
	public static int calculateTotalMysticism(Hero hero) {
		int total = hero.baseMysticism;

		if (hero.heroRace != null) {
			total += hero.heroRace.getMysticismBonus();
		}

		if (hero.deity != null) {
			total += hero.deity.getMysticismBonus();
		}

		return Math.max(0, total);
	}

	/**
	 * Calculate total skill including all bonuses
	 */
	public static int calculateTotalSkill(Hero hero) {
		int total = hero.baseSkill;

		if (hero.heroRace != null) {
			total += hero.heroRace.getSkillBonus();
		}

		if (hero.deity != null) {
			total += hero.deity.getSkillBonus();
		}

		return Math.max(0, total);
	}

	/**
	 * Calculate total presence including all bonuses
	 */
	public static int calculateTotalPresence(Hero hero) {
		int total = hero.basePresence;

		if (hero.heroRace != null) {
			total += hero.heroRace.getPresenceBonus();
		}

		if (hero.deity != null) {
			total += hero.deity.getPresenceBonus();
		}

		return Math.max(0, total);
	}

	/**
	 * Recalculate all derived stats
	 */
	public static void recalculateStats(Hero hero) {
		// Update primary stats
		hero.durability = calculateTotalDurability(hero);
		hero.mysticism = calculateTotalMysticism(hero);
		hero.skill = calculateTotalSkill(hero);
		hero.presence = calculateTotalPresence(hero);

		// Update HP
		int baseHP = getBaseHP(hero.heroClass);
		hero.HT = GameBalance.calculateMaxHP(baseHP, hero.durability);
		hero.HP = Math.min(hero.HP, hero.HT);

		// Update resource pools
		int baseMana = getBaseMana(hero.heroClass);
		hero.maxManaPool = GameBalance.calculateMaxMana(baseMana, hero.mysticism);
		hero.manaPool = Math.min(hero.manaPool, hero.maxManaPool);

		int baseStamina = getBaseStamina(hero.heroClass);
		hero.maxStaminaPool = baseStamina + (hero.durability * 5);
		hero.staminaPool = Math.min(hero.staminaPool, hero.maxStaminaPool);
	}

	/**
	 * Get base HP for class
	 */
	private static int getBaseHP(HeroClass heroClass) {
		switch (heroClass) {
			case FIGHTER: return GameBalance.FIGHTER_BASE_HP;
			case PRIEST: return GameBalance.PRIEST_BASE_HP;
			case MAGE: return GameBalance.MAGE_BASE_HP;
			case SCOUT: return GameBalance.SCOUT_BASE_HP;
			default: return 20;
		}
	}

	/**
	 * Get base mana for class
	 */
	private static int getBaseMana(HeroClass heroClass) {
		switch (heroClass) {
			case FIGHTER: return GameBalance.FIGHTER_BASE_MANA;
			case PRIEST: return GameBalance.PRIEST_BASE_MANA;
			case MAGE: return GameBalance.MAGE_BASE_MANA;
			case SCOUT: return GameBalance.SCOUT_BASE_MANA;
			default: return 0;
		}
	}

	/**
	 * Get base stamina for class
	 */
	private static int getBaseStamina(HeroClass heroClass) {
		switch (heroClass) {
			case FIGHTER: return GameBalance.FIGHTER_BASE_STAMINA;
			case PRIEST: return GameBalance.PRIEST_BASE_STAMINA;
			case MAGE: return GameBalance.MAGE_BASE_STAMINA;
			case SCOUT: return GameBalance.SCOUT_BASE_STAMINA;
			default: return 50;
		}
	}

	/**
	 * Apply subclass passive modifiers to damage
	 */
	public static int applySubclassModifiersToDamage(Hero hero, int damage) {
		return SubclassTraits.modifyDamage(hero, damage);
	}

	/**
	 * Apply subclass passive modifiers to defense
	 */
	public static int applySubclassModifiersToDefense(Hero hero, int incomingDamage) {
		return SubclassTraits.modifyDefense(hero, incomingDamage);
	}

	/**
	 * Get mana regeneration per turn
	 */
	public static int getManaRegenPerTurn(Hero hero) {
		int regen = GameBalance.BASE_MANA_REGEN;
		regen += SubclassTraits.getManaRegenBonus(hero);
		return regen;
	}

	/**
	 * Get stamina regeneration per turn
	 */
	public static int getStaminaRegenPerTurn(Hero hero) {
		int regen = GameBalance.BASE_STAMINA_REGEN;
		regen += SubclassTraits.getStaminaRegenBonus(hero);
		return regen;
	}

	/**
	 * Get critical hit chance
	 */
	public static int getCriticalChance(Hero hero) {
		int baseCrit = hero.skill / 2;  // Base crit from skill
		baseCrit += SubclassTraits.getCritChanceBonus(hero);
		return Math.min(baseCrit, 50);  // Cap at 50%
	}
}
