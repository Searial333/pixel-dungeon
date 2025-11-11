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
package com.watabou.pixeldungeon.actors.hero;

/**
 * HeroRace - EverQuest II playable races with unique racial bonuses
 *
 * Each race provides bonuses to the Four Pillars stats:
 * - D.A.D. (Durability): Physical power, health, defense
 * - M.A.D. (Mysticism): Magic power, mana, spell effectiveness
 * - S.A.D. (Skill): Accuracy, evasion, critical, stealth
 * - P.A.D. (Presence): Charisma, awareness, companion abilities
 */
public enum HeroRace {

    // GOOD ALIGNED RACES (Qeynos)

    HUMAN(
        "Human",
        "Versatile and adaptable, Humans are well-rounded adventurers from Qeynos who excel in all aspects of combat and magic.",
        1, 1, 1, 1,  // Balanced stats
        "Diplomacy" // Racial trait
    ),

    BARBARIAN(
        "Barbarian",
        "Fierce warriors from the frozen lands of Halas, Barbarians possess tremendous strength and endurance.",
        3, -1, 1, 0,  // High Durability, low Mysticism
        "Berserker Rage"
    ),

    HIGH_ELF(
        "High Elf",
        "Noble and ancient, High Elves of Felwithe are masters of arcane magic and possess exceptional wisdom.",
        -1, 3, 0, 1,  // High Mysticism, low Durability
        "Ancient Knowledge"
    ),

    HALF_ELF(
        "Half Elf",
        "Born of two worlds, Half Elves combine human versatility with elven grace and magical aptitude.",
        0, 2, 1, 0,  // Good Mysticism and Skill
        "Dual Heritage"
    ),

    DWARF(
        "Dwarf",
        "Stout and resilient, Dwarves of Kaladim are master craftsmen and stalwart defenders with exceptional constitution.",
        2, 0, 0, 1,  // High Durability
        "Stone Resilience"
    ),

    GNOME(
        "Gnome",
        "Clever and inventive, Gnomes of Ak'Anon are brilliant tinkerers with sharp minds and quick reflexes.",
        -1, 2, 2, 0,  // High Mysticism and Skill
        "Tinkerer's Insight"
    ),

    HALFLING(
        "Halfling",
        "Small but brave, Halflings from Rivervale are naturally lucky and possess remarkable agility and stealth.",
        0, 0, 3, 0,  // High Skill
        "Halfling Luck"
    ),

    ERUDITE(
        "Erudite",
        "Highly intelligent scholars from Erudin, Erudites possess unparalleled mastery of arcane and divine magic.",
        -1, 4, -1, 1,  // Very high Mysticism
        "Scholar's Brilliance"
    ),

    KERRA(
        "Kerra",
        "Feline warriors with exceptional agility and hunting instincts, the Kerra are fierce and honorable.",
        1, 0, 2, 0,  // High Skill
        "Feline Agility"
    ),

    FAE(
        "Fae",
        "Mystical fairy-folk connected to nature, the Fae possess powerful magic and charming personalities.",
        -2, 3, 0, 2,  // High Mysticism and Presence
        "Faerie Fire"
    ),

    FROGLOK(
        "Froglok",
        "Honorable and devout amphibious warriors, Frogloks are natural jumpers with strong constitutions.",
        2, 0, 1, 0,  // Good Durability and Skill
        "Amphibious Nature"
    ),

    // EVIL ALIGNED RACES (Freeport)

    DARK_ELF(
        "Dark Elf",
        "Cunning and cruel, Dark Elves of Neriak are masters of shadow magic and deception.",
        0, 2, 1, 0,  // Good Mysticism and Skill
        "Shadow Affinity"
    ),

    IKSAR(
        "Iksar",
        "Ancient lizard-men from Cabilis, Iksar are naturally armored and possess incredible regeneration.",
        3, 0, 0, -1,  // High Durability
        "Scaled Hide"
    ),

    TROLL(
        "Troll",
        "Massive and intimidating, Trolls regenerate wounds rapidly and possess devastating strength.",
        4, -2, -1, 0,  // Very high Durability
        "Regeneration"
    ),

    OGRE(
        "Ogre",
        "Brutish giants with overwhelming power, Ogres are natural warriors but lack finesse and charm.",
        4, -2, -1, -1,  // Very high Durability
        "Devastating Blow"
    ),

    RATONGA(
        "Ratonga",
        "Cunning rat-folk from the sewers of Freeport, Ratongas are masters of stealth and trickery.",
        -1, 0, 3, 0,  // High Skill
        "Urban Prowler"
    ),

    ARASAI(
        "Arasai",
        "Corrupted fairy-folk serving dark powers, Arasai wield shadow magic and possess twisted charm.",
        -2, 3, 0, 2,  // High Mysticism and Presence
        "Shadow Wings"
    ),

    SARNAK(
        "Sarnak",
        "Draconic warriors with scales and breath weapons, Sarnaks combine strength with magical resistance.",
        2, 1, 0, 0,  // Good Durability and Mysticism
        "Draconic Blood"
    ),

    // SPECIAL RACES (Can be either alignment)

    FREEBLOOD(
        "Freeblood",
        "Vampires who have broken free from the Bloodline curse, Freebloods are undead beings who retain their free will. " +
        "Neither fully good nor evil, they walk a path between light and shadow, using their vampiric powers to pursue their own goals. " +
        "Freebloods possess supernatural presence, dark magical aptitude, and predatory instincts, but their undead nature makes them physically fragile.",
        -1, 2, 1, 3,  // Low Durability, High Mysticism and Presence
        "Vampiric Essence"
    );

    private final String name;
    private final String description;
    private final int durabilityBonus;
    private final int mysticismBonus;
    private final int skillBonus;
    private final int presenceBonus;
    private final String racialTrait;

    HeroRace(String name, String description,
             int durabilityBonus, int mysticismBonus,
             int skillBonus, int presenceBonus,
             String racialTrait) {
        this.name = name;
        this.description = description;
        this.durabilityBonus = durabilityBonus;
        this.mysticismBonus = mysticismBonus;
        this.skillBonus = skillBonus;
        this.presenceBonus = presenceBonus;
        this.racialTrait = racialTrait;
    }

    public String raceName() {
        return name;
    }

    public String raceDescription() {
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

    public String getRacialTrait() {
        return racialTrait;
    }

    /**
     * Check if this race is aligned with Good (Qeynos)
     */
    public boolean isGoodAligned() {
        switch (this) {
            case HUMAN:
            case BARBARIAN:
            case HIGH_ELF:
            case HALF_ELF:
            case DWARF:
            case GNOME:
            case HALFLING:
            case ERUDITE:
            case KERRA:
            case FAE:
            case FROGLOK:
                return true;
            default:
                return false;
        }
    }

    /**
     * Check if this race is aligned with Evil (Freeport)
     */
    public boolean isEvilAligned() {
        if (this == FREEBLOOD) {
            return false;  // Freeblood are neutral, can choose
        }
        return !isGoodAligned();
    }

    /**
     * Check if this race is neutral (can choose alignment)
     */
    public boolean isNeutralAligned() {
        return this == FREEBLOOD;
    }

    /**
     * Get the starting city for this race
     */
    public String getStartingCity() {
        if (this == FREEBLOOD) {
            return "Qeynos or Freeport";  // Freeblood can choose
        }
        return isGoodAligned() ? "Qeynos" : "Freeport";
    }

    /**
     * Get display string showing all racial bonuses
     */
    public String getBonusDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" Bonuses:\n");

        if (durabilityBonus != 0) {
            sb.append("Durability: ").append(durabilityBonus > 0 ? "+" : "").append(durabilityBonus).append("\n");
        }
        if (mysticismBonus != 0) {
            sb.append("Mysticism: ").append(mysticismBonus > 0 ? "+" : "").append(mysticismBonus).append("\n");
        }
        if (skillBonus != 0) {
            sb.append("Skill: ").append(skillBonus > 0 ? "+" : "").append(skillBonus).append("\n");
        }
        if (presenceBonus != 0) {
            sb.append("Presence: ").append(presenceBonus > 0 ? "+" : "").append(presenceBonus).append("\n");
        }

        sb.append("\nRacial Trait: ").append(racialTrait);

        return sb.toString();
    }
}
