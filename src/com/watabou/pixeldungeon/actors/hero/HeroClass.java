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

import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.items.TomeOfMastery;
import com.watabou.pixeldungeon.items.armor.ClothArmor;
import com.watabou.pixeldungeon.items.bags.Keyring;
import com.watabou.pixeldungeon.items.food.Food;
import com.watabou.pixeldungeon.items.potions.PotionOfStrength;
import com.watabou.pixeldungeon.items.rings.RingOfShadows;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfIdentify;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.watabou.pixeldungeon.items.wands.WandOfMagicMissile;
import com.watabou.pixeldungeon.items.weapon.melee.Dagger;
import com.watabou.pixeldungeon.items.weapon.melee.Knuckles;
import com.watabou.pixeldungeon.items.weapon.melee.ShortSword;
import com.watabou.pixeldungeon.items.weapon.missiles.Dart;
import com.watabou.pixeldungeon.items.weapon.missiles.Boomerang;
import com.watabou.pixeldungeon.ui.QuickSlot;
import com.watabou.utils.Bundle;

public enum HeroClass {
    // Base Class
    ADVENTURER("adventurer"),
    
    // Core Archetypes (Level 5)
    FIGHTER("fighter"),
    PRIEST("priest"),
    SCOUT("scout"),
    MAGE("mage"),
    
    // Fighter Advanced Classes (Level 15)
    WARRIOR("warrior"),
    CRUSADER("crusader"),
    BRAWLER("brawler"),
    
    // Priest Advanced Classes
    CLERIC("cleric"),
    DRUID("druid"),
    SHAMAN("shaman"),
    
    // Scout Advanced Classes
    ROGUE("rogue"),
    PREDATOR("predator"),
    BARD("bard"),
    
    // Mage Advanced Classes
    SORCERER("sorcerer"),
    SUMMONER("summoner"),
    ENCHANTER("enchanter"),
    
    // Hybrid Classes
    CHANNELER("channeler"),
    BEASTLORD("beastlord");
	
	private String title;
	
	private HeroClass( String title ) {
		this.title = title;
	}
	
	public static final String[] GUARDIAN_PERKS = {
		"Guardians start with enhanced Strength and Defense.",
		"Guardians begin with Draconic Armor that evolves as they level up.",
		"Can perform powerful defensive stances that protect allies.",
		"Gain passive health regeneration in combat.",
		"Access to unique dragon-themed abilities at higher levels."
	};
	
	public static final String[] ARCANIST_PERKS = {
		"Arcanists begin with an enhanced mana pool.",
		"Master complex spellcasting with combo-based magic.",
		"Can create temporary portals for tactical advantage.",
		"Gain bonus damage with successive spell casts.",
		"Access to powerful ritual magic at higher levels."
	};
	
	public static final String[] HIEROPHANT_PERKS = {
		"Hierophants start with powerful healing abilities.",
		"Can create protective auras that shield allies.",
		"Generate bonus resources during rest periods.",
		"Improved effectiveness of all consumable items.",
		"Access to divine intervention abilities at higher levels."
	};
	
	public static final String[] VOIDWALKER_PERKS = {
		"Voidwalkers begin with shadow manipulation abilities.",
		"Can phase through reality for brief periods.",
		"Master of stealth and surprise attacks.",
		"Generate void energy from defeating enemies.",
		"Access to reality-bending powers at higher levels."
	};
	
	public void initHero( Hero hero ) {
		
		hero.heroClass = this;
		
		initCommon( hero );
		
		switch (this) {
		case ADVENTURER:
			initAdventurer( hero );
			break;
		case GUARDIAN:
			initGuardian( hero );
			break;
		case ARCANIST:
			initArcanist( hero );
			break;
		case HIEROPHANT:
			initHierophant( hero );
			break;
		case VOIDWALKER:
			initVoidwalker( hero );
			break;
		default:
			// For evolved classes, use base stats
			break;
		}
		
		if (Badges.isUnlocked( masteryBadge() )) {
			new TomeOfMastery().collect();
		}
		
		hero.updateAwareness();
	}
	
	private static void initCommon( Hero hero ) {
		(hero.belongings.armor = new ClothArmor()).identify();
		new Food().identify().collect();
		new Keyring().collect();
	}
	
	public Badges.Badge masteryBadge() {
		switch (this) {
		case FIGHTER:
		case GUARDIAN:
		case WARRIOR:
		case CRUSADER:
		case BRAWLER:
			return Badges.Badge.MASTERY_WARRIOR;
		case MAGE:
		case ARCANIST:
		case SORCERER:
		case SUMMONER:
		case ENCHANTER:
			return Badges.Badge.MASTERY_MAGE;
		case SCOUT:
		case VOIDWALKER:
		case ROGUE:
		case PREDATOR:
		case BARD:
			return Badges.Badge.MASTERY_ROGUE;
		case PRIEST:
		case HIEROPHANT:
		case CLERIC:
		case DRUID:
		case SHAMAN:
			return Badges.Badge.MASTERY_HUNTRESS;
		default:
			return null;
		}
	}
	
	private static void initAdventurer( Hero hero ) {
		// Basic starting equipment for all adventurers
		(hero.belongings.weapon = new ShortSword()).identify();
		new Dart( 8 ).identify().collect();
		
		QuickSlot.primaryValue = Dart.class;
		
		// Basic knowledge
		new PotionOfStrength().setKnown();
	}
	
	private static void initGuardian( Hero hero ) {
		// Enhanced starting stats
		hero.STR = hero.STR + 2;
		hero.HT += 10;
		hero.HP = hero.HT;
		
		// Starting equipment
		(hero.belongings.armor = new DraconicArmor()).identify();
		(hero.belongings.weapon = new DragonbladeSword()).identify();
		
		// Initial abilities
		hero.learnAbility(new DefensiveStance());
		hero.learnAbility(new DragonScale());
		
		QuickSlot.primaryValue = DefensiveStance.class;
	}
	
	private static void initArcanist( Hero hero ) {
		// Enhanced mana
		hero.maxManaPool += 50;
		hero.manaPool = hero.maxManaPool;
		
		// Starting equipment
		(hero.belongings.weapon = new ArcaneStaff()).identify();
		new SpellTome().identify().collect();
		
		// Initial abilities
		hero.learnAbility(new ArcaneBlast());
		hero.learnAbility(new ManaShield());
		
		QuickSlot.primaryValue = ArcaneBlast.class;
	}
	
	private static void initHierophant( Hero hero ) {
		// Enhanced healing
		hero.HT += 5;
		hero.HP = hero.HT;
		
		// Starting equipment
		(hero.belongings.weapon = new BlessedMace()).identify();
		(hero.belongings.armor = new DivineRobes()).identify();
		
		// Initial abilities
		hero.learnAbility(new HealingLight());
		hero.learnAbility(new ProtectiveAura());
		
		QuickSlot.primaryValue = HealingLight.class;
	}
	
	private static void initVoidwalker( Hero hero ) {
		// Enhanced stealth
		hero.awareness *= 2;
		
		// Starting equipment
		(hero.belongings.weapon = new VoidBlade()).identify();
		(hero.belongings.ring1 = new RingOfShadows()).upgrade().identify();
		
		// Initial abilities
		hero.learnAbility(new ShadowStep());
		hero.learnAbility(new VoidShield());
		
		QuickSlot.primaryValue = ShadowStep.class;
	}
	
	public String title() {
		return title;
	}
	
	public String spritesheet() {
		
		switch (this) {
		case ADVENTURER:
		case FIGHTER:
		case GUARDIAN:
		case WARRIOR:
		case CRUSADER:
		case BRAWLER:
			return Assets.WARRIOR;
		case PRIEST:
		case HIEROPHANT:
		case CLERIC:
		case DRUID:
		case SHAMAN:
			return Assets.MAGE;
		case SCOUT:
		case VOIDWALKER:
		case ROGUE:
		case PREDATOR:
		case BARD:
			return Assets.ROGUE;
		case MAGE:
		case ARCANIST:
		case SORCERER:
		case SUMMONER:
		case ENCHANTER:
			return Assets.HUNTRESS;
		default:
			return Assets.WARRIOR;
		}
	}
	}
	
	public String[] perks() {
		
		switch (this) {
		case ADVENTURER:
			return new String[]{"Adaptable to any situation", "Learns skills from all disciplines"};
		case FIGHTER:
			return GUARDIAN_PERKS;
		case PRIEST:
			return HIEROPHANT_PERKS;
		case SCOUT:
			return VOIDWALKER_PERKS;
		case MAGE:
			return ARCANIST_PERKS;
		default:
			return new String[]{"Specialized abilities unlocked"};
		}
	}

	private static final String CLASS	= "class";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( CLASS, toString() );
	}
	
	public static HeroClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( CLASS );
		return value.length() > 0 ? valueOf( value ) : ROGUE;
	}
}
