/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.actors.hero;

import com.watabou.utils.Bundle;

public enum HeroSubClass {

	NONE( null, null, null ),

	// === FIGHTER SUBCLASSES ===
	GUARDIAN( "Guardian", HeroClass.FIGHTER,
		"The _Guardian_ is the ultimate defensive tank, using shields and heavy armor to protect allies. " +
		"Masters of aggro control and damage mitigation, they stand as an unbreakable wall between enemies and their party." ),

	BERSERKER( "Berserker", HeroClass.FIGHTER,
		"The _Berserker_ channels rage into devastating melee damage. Trading defense for offense, " +
		"they enter a frenzied state that increases damage output but leaves them vulnerable." ),

	MONK( "Monk", HeroClass.FIGHTER,
		"The _Monk_ is a master of martial arts who relies on evasion rather than heavy armor. " +
		"Using quick strikes and superior agility, they avoid attacks while delivering rapid combos." ),

	BRUISER( "Bruiser", HeroClass.FIGHTER,
		"The _Bruiser_ combines raw power with crowd control abilities. " +
		"These brawlers excel at stunning and knocking down enemies while dealing consistent damage." ),

	PALADIN( "Paladin", HeroClass.FIGHTER,
		"The _Paladin_ is a holy warrior blessed by divine powers. Combining heavy armor with healing magic, " +
		"they can tank effectively while providing support to their party." ),

	SHADOWKNIGHT( "Shadowknight", HeroClass.FIGHTER,
		"The _Shadowknight_ wields dark magic alongside martial prowess. These fallen knights use " +
		"shadow spells to drain life from enemies and bolster their defenses with unholy power." ),

	// === PRIEST SUBCLASSES ===
	CLERIC( "Cleric", HeroClass.PRIEST,
		"The _Cleric_ is a traditional healer who excels at group healing and resurrection. " +
		"Their divine magic can cure diseases, remove curses, and restore fallen allies." ),

	DRUID( "Druid", HeroClass.PRIEST,
		"The _Druid_ channels the power of nature to heal and harm. They balance restoration magic " +
		"with nature-based damage spells, and can summon natural allies." ),

	SHAMAN( "Shaman", HeroClass.PRIEST,
		"The _Shaman_ communes with spirits to provide healing and buffs. Their totems grant powerful " +
		"area effects, and they can call upon ancestral spirits for aid." ),

	TEMPLAR( "Templar", HeroClass.PRIEST,
		"The _Templar_ is a militant healer who wears plate armor and fights on the front lines. " +
		"They combine reactive healing with defensive abilities to protect their allies." ),

	INQUISITOR( "Inquisitor", HeroClass.PRIEST,
		"The _Inquisitor_ uses divine magic to smite enemies while healing allies. " +
		"These aggressive healers deal holy damage to undead and demons while keeping their party alive." ),

	WARDEN( "Warden", HeroClass.PRIEST,
		"The _Warden_ is a nature-focused healer who uses healing-over-time effects. " +
		"Their connection to plants and animals allows them to restore health gradually and efficiently." ),

	FURY( "Fury", HeroClass.PRIEST,
		"The _Fury_ is an offensive druid who harnesses storms and primal rage. " +
		"They balance healing with devastating elemental damage from nature's wrath." ),

	DEFILER( "Defiler", HeroClass.PRIEST,
		"The _Defiler_ is a dark shaman who uses plague and pestilence. " +
		"Their twisted healing comes at a cost, but they excel at debuffing enemies." ),

	MYSTIC( "Mystic", HeroClass.PRIEST,
		"The _Mystic_ uses ancient spiritual magic and wards to protect allies. " +
		"Their mystical barriers and prophetic visions keep the party safe from harm." ),

	// === MAGE SUBCLASSES ===
	WIZARD( "Wizard", HeroClass.MAGE,
		"The _Wizard_ is the master of elemental destruction, wielding fire, ice, and lightning. " +
		"Their powerful area-effect spells can devastate groups of enemies." ),

	WARLOCK( "Warlock", HeroClass.MAGE,
		"The _Warlock_ channels dark elemental forces and chaos magic. " +
		"Their unpredictable spells deal massive damage with chaotic side effects." ),

	SORCERER( "Sorcerer", HeroClass.MAGE,
		"The _Sorcerer_ is a versatile mage who can adapt their magic to any situation. " +
		"They focus on sustained damage and magical utility." ),

	SUMMONER( "Summoner", HeroClass.MAGE,
		"The _Summoner_ calls forth powerful elemental beings to fight alongside them. " +
		"Their summoned creatures provide damage, tanking, or utility based on what's needed." ),

	NECROMANCER( "Necromancer", HeroClass.MAGE,
		"The _Necromancer_ commands the undead and wields death magic. " +
		"They drain life from enemies, raise skeletal minions, and excel at damage-over-time spells." ),

	ILLUSIONIST( "Illusionist", HeroClass.MAGE,
		"The _Illusionist_ manipulates minds and creates illusions to confuse enemies. " +
		"Their crowd control abilities can charm, mesmerize, and control the battlefield." ),

	COERCER( "Coercer", HeroClass.MAGE,
		"The _Coercer_ dominates enemy minds and steals their power. " +
		"They can turn enemies against each other and siphon mana from their foes." ),

	// === SCOUT SUBCLASSES ===
	SWASHBUCKLER( "Swashbuckler", HeroClass.SCOUT,
		"The _Swashbuckler_ is a daring dual-wielding fighter who relies on agility and flair. " +
		"Their flashy combat style combines high damage with excellent evasion." ),

	BRIGAND( "Brigand", HeroClass.SCOUT,
		"The _Brigand_ is a ruthless melee combatant who uses dirty tricks and poison. " +
		"They excel at sustained damage and debilitating their enemies." ),

	ASSASSIN( "Assassin", HeroClass.SCOUT,
		"The _Assassin_ strikes from the shadows with deadly precision. " +
		"Masters of stealth, they deal massive damage from behind with critical strikes." ),

	RANGER( "Ranger", HeroClass.SCOUT,
		"The _Ranger_ is a skilled archer and tracker who can tame wild beasts. " +
		"They deal consistent ranged damage and their animal companion provides additional attacks." ),

	TROUBADOUR( "Troubadour", HeroClass.SCOUT,
		"The _Troubadour_ is a charismatic bard who bolsters allies with inspiring songs. " +
		"Their musical magic increases party damage, speed, and morale." ),

	DIRGE( "Dirge", HeroClass.SCOUT,
		"The _Dirge_ is a dark bard who weakens enemies with haunting melodies. " +
		"Their discordant songs reduce enemy damage, armor, and resistance." );
	
	private String title;
	private String desc;
	private HeroClass parentClass;

	private HeroSubClass( String title, HeroClass parentClass, String desc ) {
		this.title = title;
		this.desc = desc;
		this.parentClass = parentClass;
	}
	
	public String title() {
		return title;
	}

	public String desc() {
		return desc;
	}

	public HeroClass parentClass() {
		return parentClass;
	}

	/**
	 * Check if this subclass is available for the given hero class
	 */
	public boolean isAvailableFor(HeroClass heroClass) {
		return this == NONE || this.parentClass == heroClass;
	}

	/**
	 * Get all subclasses available for a given hero class
	 */
	public static HeroSubClass[] getSubclassesFor(HeroClass heroClass) {
		java.util.List<HeroSubClass> subclasses = new java.util.ArrayList<>();
		for (HeroSubClass subclass : values()) {
			if (subclass != NONE && subclass.parentClass == heroClass) {
				subclasses.add(subclass);
			}
		}
		return subclasses.toArray(new HeroSubClass[0]);
	}
	
	private static final String SUBCLASS	= "subClass";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( SUBCLASS, toString() );
	}
	
	public static HeroSubClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( SUBCLASS );
		try {
			return valueOf( value );
		} catch (Exception e) {
			return NONE;
		}
	}
	
}
