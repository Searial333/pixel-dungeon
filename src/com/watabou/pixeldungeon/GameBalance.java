/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 *
 * Game Balance Configuration
 * Central location for all balance values and tuning parameters
 */
package com.watabou.pixeldungeon;

public class GameBalance {

	// ═══════════════════════════════════════════════════════════════
	// STAT SCALING
	// ═══════════════════════════════════════════════════════════════

	// Base stat effectiveness
	public static final float DURABILITY_TO_HP = 5f;           // Each point = 5 HP
	public static final float DURABILITY_TO_DEFENSE = 1f;      // Each point = 1 armor
	public static final float MYSTICISM_TO_MANA = 10f;         // Each point = 10 mana
	public static final float MYSTICISM_TO_SPELL_DAMAGE = 2f;  // Each point = 2 spell damage
	public static final float SKILL_TO_ACCURACY = 1f;          // Each point = 1 accuracy
	public static final float SKILL_TO_EVASION = 1f;           // Each point = 1 evasion
	public static final float PRESENCE_TO_HEALING = 2f;        // Each point = 2 healing power

	// ═══════════════════════════════════════════════════════════════
	// RESOURCE POOLS
	// ═══════════════════════════════════════════════════════════════

	// Base resource pools by archetype
	public static final int FIGHTER_BASE_HP = 22;
	public static final int FIGHTER_BASE_STAMINA = 100;
	public static final int FIGHTER_BASE_MANA = 0;

	public static final int PRIEST_BASE_HP = 18;
	public static final int PRIEST_BASE_STAMINA = 50;
	public static final int PRIEST_BASE_MANA = 80;

	public static final int MAGE_BASE_HP = 15;
	public static final int MAGE_BASE_STAMINA = 30;
	public static final int MAGE_BASE_MANA = 100;

	public static final int SCOUT_BASE_HP = 20;
	public static final int SCOUT_BASE_STAMINA = 150;
	public static final int SCOUT_BASE_MANA = 0;

	// Resource regeneration per turn
	public static final int BASE_MANA_REGEN = 2;
	public static final int BASE_STAMINA_REGEN = 5;

	// ═══════════════════════════════════════════════════════════════
	// ABILITY BALANCE
	// ═══════════════════════════════════════════════════════════════

	// Damage multipliers by ability type
	public static final float LIGHT_ATTACK_MULTIPLIER = 1.0f;
	public static final float MEDIUM_ATTACK_MULTIPLIER = 1.5f;
	public static final float HEAVY_ATTACK_MULTIPLIER = 2.0f;
	public static final float ULTIMATE_ATTACK_MULTIPLIER = 3.0f;

	// Healing multipliers
	public static final float LIGHT_HEAL_MULTIPLIER = 1.0f;
	public static final float MEDIUM_HEAL_MULTIPLIER = 1.5f;
	public static final float HEAVY_HEAL_MULTIPLIER = 2.0f;

	// Cooldown categories (in turns)
	public static final float SHORT_COOLDOWN = 5f;
	public static final float MEDIUM_COOLDOWN = 10f;
	public static final float LONG_COOLDOWN = 20f;
	public static final float ULTIMATE_COOLDOWN = 60f;

	// ═══════════════════════════════════════════════════════════════
	// RACIAL BONUSES
	// ═══════════════════════════════════════════════════════════════

	// Maximum racial stat bonus
	public static final int MAX_RACIAL_BONUS = 4;
	public static final int MAX_RACIAL_PENALTY = -1;

	// ═══════════════════════════════════════════════════════════════
	// SUBCLASS PASSIVE BONUSES
	// ═══════════════════════════════════════════════════════════════

	// Damage modifiers
	public static final float BERSERKER_MIN_DAMAGE_BONUS = 1.25f;
	public static final float BERSERKER_MAX_DAMAGE_BONUS = 1.75f;
	public static final float WIZARD_DAMAGE_BONUS = 1.30f;
	public static final float ASSASSIN_BACKSTAB_BONUS = 1.50f;
	public static final float NECROMANCER_DOT_BONUS = 1.15f;

	// Defense modifiers
	public static final float GUARDIAN_DEFENSE_BONUS = 0.80f;  // Takes 20% less damage
	public static final float BERSERKER_DEFENSE_PENALTY = 1.10f; // Takes 10% more damage
	public static final float MONK_EVASION_BONUS = 0.15f;      // 15% dodge chance

	// Resource regeneration bonuses
	public static final int SORCERER_MANA_REGEN = 2;
	public static final int WIZARD_MANA_REGEN = 1;
	public static final int MONK_STAMINA_REGEN = 2;
	public static final int ASSASSIN_STAMINA_REGEN = 2;

	// Critical hit bonuses
	public static final int ASSASSIN_CRIT_BONUS = 15;
	public static final int SWASHBUCKLER_CRIT_BONUS = 10;
	public static final int RANGER_CRIT_BONUS = 10;

	// ═══════════════════════════════════════════════════════════════
	// ITEM BALANCE
	// ═══════════════════════════════════════════════════════════════

	// Weapon tiers
	public static final int TIER_1_MIN_DAMAGE = 1;
	public static final int TIER_1_MAX_DAMAGE = 5;

	public static final int TIER_5_MIN_DAMAGE = 3;
	public static final int TIER_5_MAX_DAMAGE = 8;

	public static final int TIER_7_MIN_DAMAGE = 5;
	public static final int TIER_7_MAX_DAMAGE = 12;

	// Armor tiers
	public static final int TIER_1_ARMOR = 2;
	public static final int TIER_3_ARMOR = 4;
	public static final int TIER_5_ARMOR = 6;
	public static final int TIER_7_ARMOR = 8;

	// Proc chances
	public static final float COMMON_PROC_CHANCE = 0.25f;    // 25%
	public static final float UNCOMMON_PROC_CHANCE = 0.35f;  // 35%
	public static final float RARE_PROC_CHANCE = 0.40f;      // 40%

	// ═══════════════════════════════════════════════════════════════
	// ENEMY BALANCE
	// ═══════════════════════════════════════════════════════════════

	// HP scaling by depth
	public static final int ENEMY_HP_PER_DEPTH = 3;
	public static final int BOSS_HP_MULTIPLIER = 5;
	public static final int RAID_BOSS_HP_MULTIPLIER = 10;

	// Damage scaling by depth
	public static final int ENEMY_DAMAGE_PER_DEPTH = 1;

	// Named mob bonuses
	public static final float NAMED_MOB_HP_BONUS = 1.5f;
	public static final float NAMED_MOB_DAMAGE_BONUS = 1.25f;
	public static final float NAMED_MOB_LOOT_BONUS = 2.0f;

	// ═══════════════════════════════════════════════════════════════
	// PROGRESSION
	// ═══════════════════════════════════════════════════════════════

	// Experience requirements
	public static final int XP_PER_LEVEL_BASE = 10;
	public static final float XP_SCALING_FACTOR = 1.5f;

	// Subclass unlock level
	public static final int SUBCLASS_UNLOCK_LEVEL = 3;

	// AA points
	public static final int AA_POINTS_PER_RAID_BOSS = 5;
	public static final int AA_POINTS_PER_HERITAGE_QUEST = 10;

	// ═══════════════════════════════════════════════════════════════
	// DEITY BONUSES
	// ═══════════════════════════════════════════════════════════════

	// Maximum deity stat bonus
	public static final int MAX_DEITY_BONUS = 4;

	// ═══════════════════════════════════════════════════════════════
	// QUALITY OF LIFE
	// ═══════════════════════════════════════════════════════════════

	// Hunger depletion rate
	public static final float HUNGER_DEPLETION_RATE = 1.0f;

	// Potion identification on use
	public static final boolean AUTO_IDENTIFY_ON_USE = true;

	// ═══════════════════════════════════════════════════════════════
	// HELPER METHODS
	// ═══════════════════════════════════════════════════════════════

	/**
	 * Calculate HP from durability stat
	 */
	public static int calculateMaxHP(int basHP, int durability) {
		return baseHP + (int)(durability * DURABILITY_TO_HP);
	}

	/**
	 * Calculate mana pool from mysticism stat
	 */
	public static int calculateMaxMana(int baseMana, int mysticism) {
		return baseMana + (int)(mysticism * MYSTICISM_TO_MANA);
	}

	/**
	 * Calculate experience needed for level
	 */
	public static int experienceForLevel(int level) {
		return (int)(XP_PER_LEVEL_BASE * Math.pow(level, XP_SCALING_FACTOR));
	}

	/**
	 * Calculate spell damage from mysticism
	 */
	public static int calculateSpellDamage(int baseDamage, int mysticism) {
		return baseDamage + (int)(mysticism * MYSTICISM_TO_SPELL_DAMAGE);
	}

	/**
	 * Calculate healing from mysticism and presence
	 */
	public static int calculateHealing(int baseHealing, int mysticism, int presence) {
		return baseHealing + (int)(mysticism * 1.5f) + (int)(presence * PRESENCE_TO_HEALING);
	}
}
