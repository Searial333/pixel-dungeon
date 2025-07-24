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

	NONE( null, null ),
	
	DRAKESTRIKE_GUARDIAN( "drakestrike", 
		"The _Drakestrike Guardian_ channels ancient draconic power, gaining the ability to " +
		"imbue attacks with elemental energy and perform limited flight maneuvers. Mastery of " + 
		"dragon lore grants them unique aerial combat capabilities." ),
		
	ABYSSAL_VOIDWALKER( "voidwalker", 
		"The _Abyssal Voidwalker_ manipulates space and reality itself, " +
		"gaining powers of short-range teleportation and gravity manipulation. " +
		"Can phase temporarily out of reality for defensive purposes." ),
		
	CANTOR_TEMPLAR( "cantor-templar", 
		"The _Cantor-Templar_ combines Hierophant healing with Guardian defensive abilities, " +
		"becoming a powerful front-line support tank. Can create protective auras " +
		"while maintaining significant martial prowess." ),
		
	ASSASSIN_MONK( "assassin-monk", 
		"The _Assassin-Monk_ blends stealth and martial arts mastery, " +
		"excelling at both single-target elimination and crowd control. " +
		"Gains enhanced mobility and critical strike capabilities." ),
	
	ASSASSIN( "assassin", 
		"When performing a surprise attack, the _Assassin_ inflicts additional damage to his target." ),
	FREERUNNER( "freerunner", 
		"The _Freerunner_ can move almost twice faster, than most of the monsters. When he " +
		"is running, the Freerunner is much harder to hit. For that he must be unencumbered and not starving." ),
		
	SNIPER( "sniper", 
		"_Snipers_ are able to detect weak points in an enemy's armor, " +
		"effectively ignoring it when using a missile weapon." ),
	WARDEN( "warden", 
		"Having a strong connection with forces of nature gives _Wardens_ an ability to gather dewdrops and " +
		"seeds from plants. Also trampling a high grass grants them a temporary armor buff." ),
		
	// Additional constants for compatibility
	PALADIN( "paladin", "A holy warrior with defensive abilities." ),
	SHADOWKNIGHT( "shadowknight", "A dark warrior with shadow magic." ),
	GUARDIAN( "guardian", "A defensive specialist." ),
	BERSERKER( "berserker", "A frenzied warrior." ),
	MONK( "monk", "A martial artist." ),
	BRUISER( "bruiser", "A heavy combat specialist." );
	
	private String title;
	private String desc;
	
	private HeroSubClass( String title, String desc ) {
		this.title = title;
		this.desc = desc;
	}
	
	public String title() {
		return title;
	}
	
	public String desc() {
		return desc;
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
