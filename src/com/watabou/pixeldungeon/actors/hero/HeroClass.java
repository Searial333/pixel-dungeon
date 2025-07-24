package com.watabou.pixeldungeon.actors.hero;

public enum HeroClass {
    WARRIOR("warrior"),
    MAGE("mage"),
    ROGUE("rogue"),
    HUNTRESS("huntress");
    
    private final String title;
    
    HeroClass(String title) {
        this.title = title;
    }
    
    public String title() {
        return title;
    }
    
    public String[] perks() {
        return switch (this) {
            case WARRIOR -> new String[]{
                "Warriors start with 11 points of Strength.",
                "Warriors start with a unique short sword. This sword can be later \"reforged\" to upgrade another melee weapon.",
                "Warriors are less proficient with missile weapons.",
                "Any piece of food restores some health when eaten.",
                "Potions of Strength are identified from the beginning."
            };
            case MAGE -> new String[]{
                "Mages start with a unique Wand of Magic Missile. This wand can be later \"disenchanted\" to upgrade another wand.",
                "Mages recharge their wands faster.",
                "When eaten, any piece of food restores 1 charge for all wands in the inventory.",
                "Mages can use wands as a melee weapon.",
                "Scrolls of Identify are identified from the beginning."
            };
            case ROGUE -> new String[]{
                "Rogues start with a Ring of Shadows+1.",
                "Rogues identify a type of a ring on equipping it.",
                "Rogues are proficient with light armor, dodging better while wearing one.",
                "Rogues are proficient in detecting hidden doors and traps.",
                "Rogues can go without food longer.",
                "Scrolls of Magic Mapping are identified from the beginning."
            };
            case HUNTRESS -> new String[]{
                "Huntresses start with 15 points of Health.",
                "Huntresses start with a unique upgradeable boomerang.",
                "Huntresses are proficient with missile weapons and get damage bonus for excessive strength when using them.",
                "Huntresses gain more health from dewdrops.",
                "Potions of Healing are identified from the beginning."
            };
        };
    }
    
    public void initHero(Hero hero) {
        // Set initial Four Pillars stats based on class
        switch (this) {
            case WARRIOR -> {
                hero.fourPillars.dad = 12;
                hero.fourPillars.sad = 8;
                hero.fourPillars.mad = 5;
                hero.fourPillars.pad = 7;
            }
            case MAGE -> {
                hero.fourPillars.dad = 6;
                hero.fourPillars.sad = 8;
                hero.fourPillars.mad = 14;
                hero.fourPillars.pad = 6;
            }
            case ROGUE -> {
                hero.fourPillars.dad = 8;
                hero.fourPillars.sad = 14;
                hero.fourPillars.mad = 6;
                hero.fourPillars.pad = 6;
            }
            case HUNTRESS -> {
                hero.fourPillars.dad = 10;
                hero.fourPillars.sad = 12;
                hero.fourPillars.mad = 6;
                hero.fourPillars.pad = 6;
            }
        }
        
        hero.HT = hero.HP = switch (this) {
            case WARRIOR -> 20;
            case HUNTRESS -> 18;
            case MAGE, ROGUE -> 16;
        };
    }
}
