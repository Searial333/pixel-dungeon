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
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.mobs.Yog.BurningFist;
import com.watabou.pixeldungeon.actors.mobs.Yog.RottingFist;
import com.watabou.utils.Random;

public class Bestiary {

	public static Mob mob( int depth ) {
		@SuppressWarnings("unchecked")
		Class<? extends Mob> cl = (Class<? extends Mob>)mobClass( depth );
		try {
			return cl.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Mob mutable( int depth ) {
		@SuppressWarnings("unchecked")
		Class<? extends Mob> cl = (Class<? extends Mob>)mobClass( depth );
		
		if (Random.Int( 30 ) == 0) {
			if (cl == Rat.class) {
				cl = Albino.class;
			} else if (cl == Thief.class) {
				cl = Bandit.class;
			} else if (cl == Brute.class) {
				cl = Shielded.class;
			} else if (cl == Monk.class) {
				cl = Senior.class;
			} else if (cl == Scorpio.class) {
				cl = Acidic.class;
			}
		}
		
		try {
			return cl.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
	private static Class<?> mobClass( int depth ) {

		float[] chances;
		Class<?>[] classes;

		// EverQuest II Themed Spawns by Zone
		switch (depth) {
		// ANTONICA / COMMONLANDS (Depths 1-4)
		case 1:
			chances = new float[]{ 2, 1 };
			classes = new Class<?>[]{ Kobold.class, Rat.class };
			break;
		case 2:
			chances = new float[]{ 2, 1, 1 };
			classes = new Class<?>[]{ Kobold.class, Gnoll.class, Rat.class };
			break;
		case 3:
			chances = new float[]{ 2, 2, 1, 1,   0.02f };
			classes = new Class<?>[]{ Kobold.class, Gnoll.class, Crab.class, Rat.class,   Swarm.class };
			break;
		case 4:
			chances = new float[]{ 2, 3, 1, 1,   0.02f, 0.01f };
			classes = new Class<?>[]{ Kobold.class, Gnoll.class, Skeleton.class, Crab.class,   Swarm.class, Thief.class };
			break;
			
		// BOSS: Goo (adapted to EQ2 theme)
		case 5:
			chances = new float[]{ 1 };
			classes = new Class<?>[]{ Goo.class };
			break;

		// BLACKBURROW / STORMHOLD (Depths 6-9)
		case 6:
			chances = new float[]{ 3, 2, 2, 1,   0.2f };
			classes = new Class<?>[]{ Gnoll.class, Kobold.class, Skeleton.class, Thief.class,   Shaman.class };
			break;
		case 7:
			chances = new float[]{ 3, 2, 2, 1, 1 };
			classes = new Class<?>[]{ Gnoll.class, Skeleton.class, Kobold.class, Shaman.class, Thief.class };
			break;
		case 8:
			chances = new float[]{ 3, 2, 2, 1, 1,   0.02f };
			classes = new Class<?>[]{ Gnoll.class, Skeleton.class, Shaman.class, Kobold.class, Thief.class,   Bat.class };
			break;
		case 9:
			chances = new float[]{ 3, 3, 2, 1,   0.02f, 0.01f };
			classes = new Class<?>[]{ Gnoll.class, Skeleton.class, Shaman.class, Thief.class,   Bat.class, Brute.class };
			break;
			
		// BOSS: Tengu (adapted to EQ2 theme)
		case 10:
			chances = new float[]{ 1 };
			classes = new Class<?>[]{ Tengu.class };
			break;

		// CRUSHBONE / RUNNYEYE (Depths 11-14)
		case 11:
			chances = new float[]{ 2, 1,   0.2f };
			classes = new Class<?>[]{ Orc.class, Bat.class,   Brute.class };
			break;
		case 12:
			chances = new float[]{ 2, 1, 1,   0.2f };
			classes = new Class<?>[]{ Orc.class, Brute.class, Bat.class,   Spinner.class };
			break;
		case 13:
			chances = new float[]{ 2, 2, 1, 1, 1,   0.02f };
			classes = new Class<?>[]{ Orc.class, Brute.class, UndeadKnight.class, Shaman.class, Spinner.class,    Elemental.class };
			break;
		case 14:
			chances = new float[]{ 2, 2, 2, 1, 1,    0.02f, 0.01f };
			classes = new Class<?>[]{ Orc.class, UndeadKnight.class, Brute.class, Spinner.class, Shaman.class,    Elemental.class, Monk.class };
			break;
			
		// BOSS: DM300 (adapted to EQ2 theme)
		case 15:
			chances = new float[]{ 1 };
			classes = new Class<?>[]{ DM300.class };
			break;

		// LAVASTORM / HIGH-LEVEL ZONES (Depths 16-19)
		case 16:
			chances = new float[]{ 2, 1, 1,   0.2f };
			classes = new Class<?>[]{ UndeadKnight.class, Elemental.class, Warlock.class,    Monk.class };
			break;
		case 17:
			chances = new float[]{ 2, 1, 1, 1 };
			classes = new Class<?>[]{ UndeadKnight.class, Drake.class, Elemental.class, Warlock.class };
			break;
		case 18:
			chances = new float[]{ 2, 2, 1, 1, 1 };
			classes = new Class<?>[]{ UndeadKnight.class, Drake.class, Elemental.class, Golem.class, Warlock.class };
			break;
		case 19:
			chances = new float[]{ 2, 2, 2, 1, 1,    0.02f };
			classes = new Class<?>[]{ UndeadKnight.class, Drake.class, Golem.class, Warlock.class, Elemental.class,    Succubus.class };
			break;
			
		case 20:
			chances = new float[]{ 1 };
			classes = new Class<?>[]{ King.class };
			break;
			
		case 22:
			chances = new float[]{ 1, 1 };
			classes = new Class<?>[]{ Succubus.class, Eye.class };
			break;
		case 23:
			chances = new float[]{ 1, 2, 1 };
			classes = new Class<?>[]{ Succubus.class, Eye.class, Scorpio.class };
			break;
		case 24:
			chances = new float[]{ 1, 2, 3 };
			classes = new Class<?>[]{ Succubus.class, Eye.class, Scorpio.class };
			break;
			
		case 25:
			chances = new float[]{ 1 };
			classes = new Class<?>[]{ Yog.class };
			break;
			
		default:
			chances = new float[]{ 1 };
			classes = new Class<?>[]{ Eye.class };
		}
		
		return classes[ Random.chances( chances )];
	}
	
	public static boolean isBoss( Char mob ) {
		return 
			mob instanceof Goo || 
			mob instanceof Tengu || 
			mob instanceof DM300 || 
			mob instanceof King || 
			mob instanceof Yog || mob instanceof BurningFist || mob instanceof RottingFist;
	}
}
