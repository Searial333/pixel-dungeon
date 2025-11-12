/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.hero;

/**
 * SubclassTraits - Passive bonuses and mechanics for each subclass
 * These traits are always active and define each subclass's unique playstyle
 */
public class SubclassTraits {

	/**
	 * Get the passive damage bonus for this subclass
	 * @param hero The hero
	 * @param damageDealt The base damage
	 * @return Modified damage
	 */
	public static int modifyDamage(Hero hero, int damageDealt) {
		if (hero.subClass == HeroSubClass.NONE) {
			return damageDealt;
		}

		switch (hero.subClass) {
			// FIGHTERS
			case BERSERKER:
				// +25% damage, scales with missing HP
				float hpPercent = 1f - ((float)hero.HP / hero.HT);
				return (int)(damageDealt * (1.25f + hpPercent * 0.5f));

			case MONK:
				// +15% damage when unarmored or light armor
				// TODO: Check armor weight
				return (int)(damageDealt * 1.15f);

			case BRUISER:
				// +20% damage
				return (int)(damageDealt * 1.20f);

			case PALADIN:
				// +10% damage vs undead/demons
				// TODO: Check enemy type
				return (int)(damageDealt * 1.10f);

			case SHADOWKNIGHT:
				// +15% damage, lifesteal 5%
				return (int)(damageDealt * 1.15f);

			// MAGES
			case WIZARD:
				// +30% elemental spell damage
				return (int)(damageDealt * 1.30f);

			case WARLOCK:
				// Chaotic damage variance +50%
				return damageDealt;  // Handled by Chaos Blast

			case SUMMONER:
				// +10% damage when pet is active
				return (int)(damageDealt * 1.10f);

			case NECROMANCER:
				// +15% damage over time effects
				return (int)(damageDealt * 1.15f);

			case ILLUSIONIST:
			case COERCER:
				// +10% damage
				return (int)(damageDealt * 1.10f);

			// SCOUTS
			case ASSASSIN:
				// +50% backstab damage
				// TODO: Check if attacking from behind
				return (int)(damageDealt * 1.50f);

			case SWASHBUCKLER:
				// +20% damage with dual wield
				// TODO: Check if dual wielding
				return (int)(damageDealt * 1.20f);

			case BRIGAND:
				// +15% damage, poison chance
				return (int)(damageDealt * 1.15f);

			case RANGER:
				// +25% ranged damage
				// TODO: Check weapon type
				return (int)(damageDealt * 1.15f);

			default:
				return damageDealt;
		}
	}

	/**
	 * Get the defense bonus for this subclass
	 * @param hero The hero
	 * @param incomingDamage The incoming damage
	 * @return Reduced damage
	 */
	public static int modifyDefense(Hero hero, int incomingDamage) {
		if (hero.subClass == HeroSubClass.NONE) {
			return incomingDamage;
		}

		switch (hero.subClass) {
			// FIGHTERS
			case GUARDIAN:
				// Reduce damage by 20%
				return (int)(incomingDamage * 0.80f);

			case MONK:
				// +15% evasion (damage reduction simulation)
				if (com.watabou.utils.Random.Int(100) < 15) {
					return 0;  // Dodged!
				}
				return incomingDamage;

			case PALADIN:
			case TEMPLAR:
				// Reduce damage by 10%
				return (int)(incomingDamage * 0.90f);

			case SHADOWKNIGHT:
				// Reduce damage by 10%
				return (int)(incomingDamage * 0.90f);

			case BERSERKER:
				// Take 10% more damage (glass cannon)
				return (int)(incomingDamage * 1.10f);

			default:
				return incomingDamage;
		}
	}

	/**
	 * Get the mana/stamina regeneration bonus
	 * @param hero The hero
	 * @return Bonus regen per turn
	 */
	public static int getManaRegenBonus(Hero hero) {
		if (hero.subClass == HeroSubClass.NONE) {
			return 0;
		}

		switch (hero.subClass) {
			case SORCERER:
				return 2;  // +2 mana per turn
			case WIZARD:
			case WARLOCK:
			case NECROMANCER:
				return 1;
			default:
				return 0;
		}
	}

	public static int getStaminaRegenBonus(Hero hero) {
		if (hero.subClass == HeroSubClass.NONE) {
			return 0;
		}

		switch (hero.subClass) {
			case MONK:
			case ASSASSIN:
			case SWASHBUCKLER:
				return 2;  // +2 stamina per turn
			case RANGER:
			case BRIGAND:
				return 1;
			default:
				return 0;
		}
	}

	/**
	 * Get critical hit chance bonus
	 * @param hero The hero
	 * @return Crit chance (0-100)
	 */
	public static int getCritChanceBonus(Hero hero) {
		if (hero.subClass == HeroSubClass.NONE) {
			return 0;
		}

		switch (hero.subClass) {
			case ASSASSIN:
				return 15;  // +15% crit chance
			case SWASHBUCKLER:
			case RANGER:
				return 10;
			case MONK:
			case BRIGAND:
				return 5;
			default:
				return 0;
		}
	}

	/**
	 * Get description of passive traits for this subclass
	 * @param subclass The subclass
	 * @return Description text
	 */
	public static String getTraitDescription(HeroSubClass subclass) {
		switch (subclass) {
			// FIGHTERS
			case GUARDIAN:
				return "Passive: -20% damage taken, improved threat generation";
			case BERSERKER:
				return "Passive: +25-50% damage (scales with missing HP), +10% damage taken";
			case MONK:
				return "Passive: +15% evasion, +15% damage when lightly armored, +2 stamina regen";
			case BRUISER:
				return "Passive: +20% damage, increased stun duration";
			case PALADIN:
				return "Passive: +10% damage vs undead, -10% damage taken, lay on hands";
			case SHADOWKNIGHT:
				return "Passive: +15% damage, 5% lifesteal, -10% damage taken";

			// PRIESTS
			case CLERIC:
				return "Passive: +20% healing power, group heal efficiency";
			case DRUID:
				return "Passive: +15% nature spell damage, +10% healing over time";
			case SHAMAN:
				return "Passive: +15% buff/debuff duration, totem power";
			case TEMPLAR:
				return "Passive: -10% damage taken, +15% reactive healing";
			case INQUISITOR:
				return "Passive: +25% damage vs undead, +10% holy damage";
			case WARDEN:
				return "Passive: +25% healing over time potency";
			case FURY:
				return "Passive: +20% elemental damage, +10% healing";
			case DEFILER:
				return "Passive: +20% DoT damage, +15% debuff potency";
			case MYSTIC:
				return "Passive: +20% ward strength, +10% vision range";

			// MAGES
			case WIZARD:
				return "Passive: +30% elemental damage, +1 mana regen";
			case WARLOCK:
				return "Passive: Chaos effects, +50% damage variance, +1 mana regen";
			case SORCERER:
				return "Passive: +15% spell damage, +2 mana regen";
			case SUMMONER:
				return "Passive: +50% pet damage/HP, +1 max summon";
			case NECROMANCER:
				return "Passive: +15% DoT damage, undead control, +1 mana regen";
			case ILLUSIONIST:
				return "Passive: +50% crowd control duration";
			case COERCER:
				return "Passive: +50% charm duration, mana drain on hit";

			// SCOUTS
			case SWASHBUCKLER:
				return "Passive: +20% dual wield damage, +10% crit, +2 stamina regen, riposte";
			case BRIGAND:
				return "Passive: +15% damage, poison on attacks, +5% crit, +1 stamina regen";
			case ASSASSIN:
				return "Passive: +50% backstab damage, +15% crit, +2 stamina regen";
			case RANGER:
				return "Passive: +25% ranged damage, +10% crit, pet companion, +1 stamina regen";
			case TROUBADOUR:
				return "Passive: +20% buff potency to party, +15% Presence effectiveness";
			case DIRGE:
				return "Passive: +20% debuff potency, −15% enemy damage in range";

			default:
				return "No passive traits.";
		}
	}
}
