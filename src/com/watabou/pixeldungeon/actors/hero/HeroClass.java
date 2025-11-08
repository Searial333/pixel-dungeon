package com.watabou.pixeldungeon.actors.hero;

/**
 * HeroClass - EverQuest II class archetypes
 *
 * In EverQuest II, all adventurers begin as one of four archetypes,
 * later specializing into specific subclasses as they gain experience.
 */
public enum HeroClass {
    FIGHTER("fighter"),
    PRIEST("priest"),
    MAGE("mage"),
    SCOUT("scout");

    private final String title;

    HeroClass(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }

    public String[] perks() {
        return switch (this) {
            case FIGHTER -> new String[]{
                "Fighters are masters of melee combat and heavy armor.",
                "Fighters start with high Durability and can withstand tremendous punishment.",
                "Fighters excel at protecting allies and dealing devastating melee damage.",
                "Fighters can wear plate armor and use all weapon types.",
                "Fighters start with increased health and stamina pools.",
                "Later specializes into: Guardian, Berserker, Monk, Bruiser, Paladin, or Shadowknight"
            };
            case PRIEST -> new String[]{
                "Priests channel divine power to heal allies and smite foes.",
                "Priests start with high Mysticism and Presence.",
                "Priests can heal wounds, cure ailments, and bolster their companions.",
                "Priests wield divine magic effective against undead creatures.",
                "Priests start with increased mana pool and healing power.",
                "Later specializes into: Cleric, Druid, Shaman, Templar, Inquisitor, Warden, Fury, Defiler, or Mystic"
            };
            case MAGE -> new String[]{
                "Mages command the arcane forces of magic to devastating effect.",
                "Mages start with exceptional Mysticism but lower Durability.",
                "Mages wield powerful elemental and arcane spells from range.",
                "Mages can control the battlefield with crowd control effects.",
                "Mages start with the largest mana pool and spell power.",
                "Later specializes into: Wizard, Warlock, Sorcerer, Summoner, Necromancer, Illusionist, or Coercer"
            };
            case SCOUT -> new String[]{
                "Scouts are agile combatants who rely on speed and precision.",
                "Scouts start with exceptional Skill and good Durability.",
                "Scouts excel at stealth, critical strikes, and ranged combat.",
                "Scouts can use bows, daggers, and light weapons with deadly efficiency.",
                "Scouts start with increased stamina and evasion.",
                "Later specializes into: Rogue, Predator, Bard, Ranger, Assassin, Swashbuckler, Brigand, Troubadour, Dirge, or Ranger"
            };
        };
    }

    public void initHero(Hero hero) {
        // Set initial Four Pillars stats based on class archetype
        switch (this) {
            case FIGHTER -> {
                hero.durability = 14;   // D.A.D. - High physical resilience
                hero.skill = 8;          // S.A.D. - Moderate combat skill
                hero.mysticism = 5;      // M.A.D. - Low magical aptitude
                hero.presence = 7;       // P.A.D. - Moderate presence
                hero.maxStaminaPool = 120;
                hero.staminaPool = 120;
            }
            case PRIEST -> {
                hero.durability = 8;     // D.A.D. - Moderate durability
                hero.skill = 6;          // S.A.D. - Lower combat skill
                hero.mysticism = 14;     // M.A.D. - High divine magic
                hero.presence = 10;      // P.A.D. - High presence for healing
                hero.maxManaPool = 80;
                hero.manaPool = 80;
            }
            case MAGE -> {
                hero.durability = 6;     // D.A.D. - Low physical resilience
                hero.skill = 7;          // S.A.D. - Moderate skill
                hero.mysticism = 16;     // M.A.D. - Exceptional magical power
                hero.presence = 6;       // P.A.D. - Low presence
                hero.maxManaPool = 100;
                hero.manaPool = 100;
            }
            case SCOUT -> {
                hero.durability = 10;    // D.A.D. - Good durability
                hero.skill = 14;         // S.A.D. - Exceptional agility and precision
                hero.mysticism = 6;      // M.A.D. - Low magic
                hero.presence = 7;       // P.A.D. - Moderate presence
                hero.maxStaminaPool = 150;
                hero.staminaPool = 150;
            }
        }

        // Set starting health based on class
        hero.HT = hero.HP = switch (this) {
            case FIGHTER -> 22;    // Highest health
            case SCOUT -> 18;      // Good health
            case PRIEST -> 16;     // Moderate health
            case MAGE -> 14;       // Lowest health
        };
    }
}
