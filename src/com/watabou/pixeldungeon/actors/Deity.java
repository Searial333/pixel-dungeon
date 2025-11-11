/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors;

/**
 * Deity - EverQuest II gods and goddesses
 * Players can worship deities to gain blessings and bonuses
 */
public enum Deity {

	// GOOD DEITIES
	MITHANIEL_MARR(
		"Mithaniel Marr",
		"The Lightbringer",
		Alignment.GOOD,
		"God of valor and honor, Mithaniel Marr blesses paladins and those who fight for justice.",
		2, 0, 1, 2  // Durability, Mysticism, Skill, Presence bonuses
	),

	TUNARE(
		"Tunare",
		"The Mother of All",
		Alignment.GOOD,
		"Goddess of nature and growth, Tunare watches over rangers, druids, and all who respect the natural order.",
		0, 2, 1, 2  // Mysticism and Presence
	),

	QUELLIOUS(
		"Quellious",
		"The Tranquil",
		Alignment.GOOD,
		"Goddess of peace and tranquility, Quellious grants serenity to clerics and monks who seek balance.",
		1, 2, 0, 2  // Mysticism and Presence
	),

	RODCET_NIFE(
		"Rodcet Nife",
		"The Prime Healer",
		Alignment.GOOD,
		"God of healing and medicine, Rodcet Nife empowers all healers and those who preserve life.",
		0, 3, 0, 2  // High Mysticism
	),

	EROLLISI_MARR(
		"Erollisi Marr",
		"The Queen of Love",
		Alignment.GOOD,
		"Goddess of love and beauty, Erollisi Marr blesses bards and those who spread joy.",
		0, 1, 1, 3  // High Presence
	),

	// NEUTRAL DEITIES
	BRISTLEBANE(
		"Bristlebane",
		"The King of Thieves",
		Alignment.NEUTRAL,
		"God of mischief and trickery, Bristlebane favors rogues, thieves, and jesters who live by their wits.",
		0, 0, 4, 1  // High Skill
	),

	SOLUSEK_RO(
		"Solusek Ro",
		"The Burning Prince",
		Alignment.NEUTRAL,
		"God of fire and magic, Solusek Ro grants power to wizards and sorcerers who seek ultimate arcane knowledge.",
		0, 4, 0, 0  // Very high Mysticism
	),

	KARANA(
		"Karana",
		"The Rainkeeper",
		Alignment.NEUTRAL,
		"God of storms and weather, Karana blesses those who respect the power of nature's fury.",
		1, 2, 1, 1  // Balanced
	),

	// EVIL DEITIES
	INNORUUK(
		"Innoruuk",
		"The Prince of Hate",
		Alignment.EVIL,
		"God of hate and malice, Innoruuk empowers shadowknights and dark elves who embrace cruelty.",
		2, 2, 1, 0  // Durability and Mysticism
	),

	CAZIC_THULE(
		"Cazic-Thule",
		"The Faceless",
		Alignment.EVIL,
		"God of fear and terror, Cazic-Thule grants power to those who spread dread and despair.",
		1, 2, 1, 1  // Balanced with dark power
	),

	BERTOXXULOUS(
		"Bertoxxulous",
		"The Plaguebringer",
		Alignment.EVIL,
		"God of disease and decay, Bertoxxulous blesses necromancers who command death and pestilence.",
		0, 3, 0, 1  // High Mysticism
	),

	RALLOS_ZEK(
		"Rallos Zek",
		"The Warlord",
		Alignment.EVIL,
		"God of war and conquest, Rallos Zek empowers warriors who seek glory in battle and domination.",
		3, 0, 2, 0  // High Durability and Skill
	),

	NONE(
		"Agnostic",
		"Faithless",
		Alignment.NEUTRAL,
		"You worship no deity and walk your own path.",
		0, 0, 0, 0  // No bonuses
	);

	private final String name;
	private final String title;
	private final Alignment alignment;
	private final String description;
	private final int durabilityBonus;
	private final int mysticismBonus;
	private final int skillBonus;
	private final int presenceBonus;

	Deity(String name, String title, Alignment alignment, String description,
	      int durabilityBonus, int mysticismBonus, int skillBonus, int presenceBonus) {
		this.name = name;
		this.title = title;
		this.alignment = alignment;
		this.description = description;
		this.durabilityBonus = durabilityBonus;
		this.mysticismBonus = mysticismBonus;
		this.skillBonus = skillBonus;
		this.presenceBonus = presenceBonus;
	}

	public String deityName() {
		return name;
	}

	public String title() {
		return title;
	}

	public Alignment alignment() {
		return alignment;
	}

	public String description() {
		return description;
	}

	public int getDurabilityBonus() {
		return durabilityBonus;
	}

	public int getMysticismBonus() {
		return mysticismBonus;
	}

	public int getSkillBonus() {
		return skillBonus;
	}

	public int getPresenceBonus() {
		return presenceBonus;
	}

	public enum Alignment {
		GOOD,
		NEUTRAL,
		EVIL
	}

	/**
	 * Get deity blessing description
	 */
	public String getBlessingDescription() {
		if (this == NONE) {
			return "No deity blessings";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" - ").append(title).append("\n\n");
		sb.append(description).append("\n\nBlessings:\n");

		if (durabilityBonus > 0) {
			sb.append("  +").append(durabilityBonus).append(" Durability\n");
		}
		if (mysticismBonus > 0) {
			sb.append("  +").append(mysticismBonus).append(" Mysticism\n");
		}
		if (skillBonus > 0) {
			sb.append("  +").append(skillBonus).append(" Skill\n");
		}
		if (presenceBonus > 0) {
			sb.append("  +").append(presenceBonus).append(" Presence\n");
		}

		return sb.toString();
	}
}
