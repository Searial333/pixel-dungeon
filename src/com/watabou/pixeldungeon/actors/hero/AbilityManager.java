/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 *
 * Ability Manager - Handles ability acquisition and usage
 */
package com.watabou.pixeldungeon.actors.hero;

import com.watabou.pixeldungeon.actors.abilities.*;
import java.util.ArrayList;
import java.util.List;

public class AbilityManager {

	/**
	 * Get all abilities available to a hero based on class and subclass
	 */
	public static List<Ability> getAvailableAbilities(Hero hero) {
		List<Ability> abilities = new ArrayList<>();

		// Base class abilities
		switch (hero.heroClass) {
			case FIGHTER:
				abilities.add(new Provoke());
				abilities.add(new BerserkRage());
				break;
			case PRIEST:
				abilities.add(new CelestialHealing());
				abilities.add(new SpiritualRenewal());
				break;
			case MAGE:
				abilities.add(new IceComet());
				break;
			case SCOUT:
				abilities.add(new Backstab());
				break;
		}

		// Subclass abilities
		if (hero.subClass != HeroSubClass.NONE) {
			addSubclassAbilities(abilities, hero.subClass);
		}

		return abilities;
	}

	/**
	 * Add subclass-specific abilities
	 */
	private static void addSubclassAbilities(List<Ability> abilities, HeroSubClass subclass) {
		switch (subclass) {
			// FIGHTERS
			case GUARDIAN:
				abilities.add(new ShieldBash());
				break;
			case BERSERKER:
				abilities.add(new Rampage());
				break;
			case MONK:
				abilities.add(new FlyingKick());
				break;
			case BRUISER:
				abilities.add(new Haymaker());
				break;
			case PALADIN:
				abilities.add(new LayOnHands());
				abilities.add(new ShieldBash());
				break;
			case SHADOWKNIGHT:
				abilities.add(new Lifetap());
				abilities.add(new HarmTouch());
				break;

			// PRIESTS
			case CLERIC:
				abilities.add(new GroupHeal());
				break;
			case DRUID:
				abilities.add(new Thorncoat());
				abilities.add(new Regrowth());
				break;
			case SHAMAN:
				abilities.add(new SpiritTotem());
				break;
			case TEMPLAR:
				abilities.add(new ReactiveHeal());
				abilities.add(new GroupHeal());
				break;
			case INQUISITOR:
				abilities.add(new SmiteEvil());
				break;
			case WARDEN:
				abilities.add(new Regrowth());
				abilities.add(new Thorncoat());
				break;
			case FURY:
				abilities.add(new Tempest());
				break;
			case DEFILER:
				abilities.add(new Plague());
				abilities.add(new SpiritTotem());
				break;
			case MYSTIC:
				abilities.add(new MysticWard());
				break;

			// MAGES
			case WIZARD:
				abilities.add(new Fireball());
				abilities.add(new ArcaneBarrage());
				break;
			case WARLOCK:
				abilities.add(new ChaosBlast());
				abilities.add(new Fireball());
				break;
			case SORCERER:
				abilities.add(new ArcaneBarrage());
				abilities.add(new Fireball());
				break;
			case SUMMONER:
				abilities.add(new SummonElemental());
				break;
			case NECROMANCER:
				abilities.add(new Lifetap());
				abilities.add(new RaiseDead());
				break;
			case ILLUSIONIST:
			case COERCER:
				abilities.add(new Mesmerize());
				abilities.add(new MindControl());
				break;

			// SCOUTS
			case SWASHBUCKLER:
				abilities.add(new Riposte());
				break;
			case BRIGAND:
				abilities.add(new PoisonedBlades());
				break;
			case ASSASSIN:
				abilities.add(new Eviscerate());
				break;
			case RANGER:
				abilities.add(new Snare());
				abilities.add(new PetAttack());
				break;
			case TROUBADOUR:
				abilities.add(new HeroicAnthem());
				break;
			case DIRGE:
				abilities.add(new SongOfDeath());
				break;
		}
	}

	/**
	 * Get ability by name
	 */
	public static Ability getAbilityByName(String name, Hero hero) {
		for (Ability ability : getAvailableAbilities(hero)) {
			if (ability.name().equals(name)) {
				return ability;
			}
		}
		return null;
	}

	/**
	 * Check if hero can use ability
	 */
	public static boolean canUseAbility(Hero hero, Ability ability) {
		return ability.canUse(hero);
	}

	/**
	 * Get ability description with current cooldown
	 */
	public static String getAbilityDescription(Ability ability) {
		StringBuilder desc = new StringBuilder();
		desc.append(ability.name()).append("\n\n");
		desc.append(ability.desc()).append("\n\n");

		// Cost information
		if (ability.manaCost() > 0) {
			desc.append("Mana Cost: ").append(ability.manaCost()).append("\n");
		}
		if (ability.staminaCost() > 0) {
			desc.append("Stamina Cost: ").append(ability.staminaCost()).append("\n");
		}
		if (ability.cooldown() > 0) {
			desc.append("Cooldown: ").append(ability.cooldown()).append(" turns\n");
		}

		return desc.toString();
	}
}
