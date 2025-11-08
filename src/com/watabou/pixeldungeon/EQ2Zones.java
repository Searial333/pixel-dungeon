/*
 * Pixel Dungeon - EverQuest II Edition
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
package com.watabou.pixeldungeon;

/**
 * EQ2Zones - Provides EverQuest II zone names and lore descriptions
 * based on dungeon depth, transforming the generic Pixel Dungeon levels
 * into themed EverQuest II zones.
 */
public class EQ2Zones {

	/**
	 * Get the EverQuest II zone name for a given depth
	 */
	public static String getZoneName(int depth) {
		if (depth <= 0) {
			return "Surface";
		} else if (depth <= 4) {
			return "Antonica";
		} else if (depth == 5) {
			return "Gnollish Stronghold";
		} else if (depth <= 9) {
			return "Blackburrow";
		} else if (depth == 10) {
			return "Sabertooth Throne Room";
		} else if (depth <= 14) {
			return "Crushbone Citadel";
		} else if (depth == 15) {
			return "Emperor's Fortress";
		} else if (depth <= 19) {
			return "Lavastorm Mountains";
		} else if (depth == 20) {
			return "Nagafen's Lair";
		} else if (depth <= 24) {
			return "Plane of Fear";
		} else {
			return "The Void";
		}
	}

	/**
	 * Get the lore description for a zone
	 */
	public static String getZoneDescription(int depth) {
		if (depth <= 0) {
			return "You stand on the surface of Norrath, where your journey begins.";
		} else if (depth <= 4) {
			return "The rolling plains of Antonica stretch before you. Once peaceful farmlands surrounding " +
				"Qeynos, these fields now teem with dangerous creatures - kobolds from the Commonlands, " +
				"wandering gnolls, and restless undead. Settlers have abandoned their homesteads, leaving " +
				"you to brave the wilderness alone.";
		} else if (depth == 5) {
			return "You've discovered a hidden gnollish stronghold! The Sabertooth clan has fortified this " +
				"position, and their champion guards their treasures fiercely. The stench of wet fur and " +
				"rotting meat fills the air.";
		} else if (depth <= 9) {
			return "The tunnels of Blackburrow wind deep into the earth. This ancient gnoll warren has been " +
				"expanded over generations, its passages lined with crude torches and tribal markings. The " +
				"howls of the Sabertooth clan echo through the darkness, warning intruders to turn back. " +
				"Kobold raiders from Stormhold sometimes venture here, leading to bloody skirmishes in the depths.";
		} else if (depth == 10) {
			return "You've reached the Sabertooth Throne Room, where the clan's chieftain holds court. " +
				"Trophies of past victories hang from the walls - adventurer's weapons, merchant's goods, " +
				"and the bones of those who dared challenge the gnolls. The chieftain's elite guards stand ready.";
		} else if (depth <= 14) {
			return "The ruins of Crushbone Citadel rise around you. Once a fortress of the High Elves, this castle " +
				"fell to the orcish hordes centuries ago. Emperor Crush rules here with an iron fist, training his " +
				"legions for conquest. Orcish patrols march through ancient elven halls, desecrating what was once beautiful. " +
				"The deeper you go, the more organized and dangerous the orcs become.";
		} else if (depth == 15) {
			return "Emperor Crush's personal fortress lies before you. The self-proclaimed orcish emperor has " +
				"amassed great power and wealth within these fortified walls. His elite guards and war machines " +
				"protect the throne room where he plots his campaign to conquer all of Faydark.";
		} else if (depth <= 19) {
			return "The Lavastorm Mountains burn with eternal fire. Volcanic fissures spew molten rock, and the very " +
				"air shimmers with heat. Dragons and drakes nest in the lava flows, guarding ancient treasures. " +
				"The undead servants of dark necromancers patrol the obsidian cliffs, and powerful elementals " +
				"rage across the scorched landscape. Only the bravest adventurers dare venture into these hellish peaks.";
		} else if (depth == 20) {
			return "Nagafen's Lair - the domain of Lord Nagafen, the ancient red dragon who has terrorized Norrath " +
				"for millennia. The dragon's hoard lies deep within this volcanic chamber, but countless adventurers " +
				"have perished attempting to claim it. The heat is unbearable, and Nagafen's servants patrol constantly.";
		} else if (depth <= 24) {
			return "You have entered the Plane of Fear, domain of Cazic-Thule, the God of Fear. This nightmare realm " +
				"exists outside normal reality, filled with demons, horrors, and manifestations of mortal terror. " +
				"The very air drains hope from your heart. Few who enter this plane ever return.";
		} else {
			return "The Void - a place beyond death, beyond existence. Reality itself begins to unravel here. " +
				"Ancient evils older than the gods themselves stir in this emptiness. You have gone farther than " +
				"any mortal should. Will you find ultimate power, or ultimate doom?";
		}
	}

	/**
	 * Get a short zone title for UI display
	 */
	public static String getZoneTitle(int depth) {
		String name = getZoneName(depth);
		return String.format("Depth %d - %s", depth, name);
	}

	/**
	 * Check if this depth is a boss level
	 */
	public static boolean isBossLevel(int depth) {
		return depth == 5 || depth == 10 || depth == 15 || depth == 20 || depth == 25;
	}

	/**
	 * Get the boss name for a boss level
	 */
	public static String getBossName(int depth) {
		return switch (depth) {
			case 5 -> "Gnoll Chieftain";
			case 10 -> "Sabertooth War Leader";
			case 15 -> "Emperor Crush";
			case 20 -> "Lord Nagafen";
			case 25 -> "Cazic-Thule";
			default -> "Unknown Terror";
		};
	}
}
