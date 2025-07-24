/*
 * Hollowroot Vale - Dynamic Weapon Implementation
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.dynamic;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class DynamicWeapon extends DynamicItem {
    
    // Weapon-specific properties
    protected int minDamage;
    protected int maxDamage;
    protected float accuracy;
    protected float criticalChance;
    
    public DynamicWeapon() {
        super();
        // Force weapon root
        this.root = ItemRoot.WEAPON;
        this.subtype = root.subtypes[Random.Int(root.subtypes.length)];
        generateWeaponStats();
        setWeaponImage();
    }
    
    private void generateWeaponStats() {
        // Base weapon stats that scale with rarity and Four Pillars
        int baseMin = 2 + rarity.tier;
        int baseMax = 6 + (rarity.tier * 2);
        
        // Apply heritage modifiers
        minDamage = Math.round(baseMin * heritage.durabilityMod * rarity.statMultiplier);
        maxDamage = Math.round(baseMax * heritage.durabilityMod * rarity.statMultiplier);
        
        accuracy = 0.8f + (rarity.tier * 0.05f);
        criticalChance = 0.05f + (rarity.tier * 0.03f);
        
        // Ensure minimum values
        minDamage = Math.max(1, minDamage);
        maxDamage = Math.max(minDamage + 1, maxDamage);
    }
    
    private void setWeaponImage() {
        // Set sprite based on subtype
        switch (subtype) {
            case "Sword":
                image = ItemSpriteSheet.SWORD;
                break;
            case "Dagger":
                image = ItemSpriteSheet.DAGGER;
                break;
            case "Axe":
                image = ItemSpriteSheet.BATTLE_AXE;
                break;
            case "Sickle":
            case "Scythe":
                image = ItemSpriteSheet.GLAIVE;
                break;
            case "Wand":
                image = ItemSpriteSheet.WAND_MAGIC_MISSILE;
                break;
            default:
                image = ItemSpriteSheet.SWORD;
                break;
        }
    }
    
    public int damageRoll(Hero hero) {
        int damage = Random.Int(minDamage, maxDamage + 1);
        
        // Apply Four Pillars bonuses
        damage += Math.round(hero.durability * 0.1f); // Durability adds damage
        
        // Apply affixes
        for (ItemAffix affix : affixes) {
            switch (affix.getType()) {
                case SHARP:
                    damage = Math.round(damage * (1 + affix.getPower() / 100f));
                    break;
                case HEAVY:
                    damage += 2; // Flat bonus for heavy weapons
                    break;
                case CURSED:
                    // Cursed weapons deal damage to wielder
                    hero.damage(affix.getPower(), this);
                    break;
                case BLESSED:
                    // Blessed weapons heal wielder
                    hero.HP = Math.min(hero.HT, hero.HP + affix.getPower());
                    break;
            }
        }
        
        return Math.max(1, damage);
    }
    
    public boolean isAccurateHit(Hero attacker, Char defender) {
        float hitChance = accuracy;
        
        // Apply skill bonus
        hitChance += attacker.skill * 0.01f;
        
        // Apply affix bonuses
        for (ItemAffix affix : affixes) {
            if (affix.getType() == ItemAffix.AffixType.KEEN) {
                hitChance += affix.getPower() / 100f;
            }
        }
        
        return Random.Float() < hitChance;
    }
    
    public boolean isCriticalHit(Hero attacker) {
        float critChance = criticalChance;
        
        // Apply skill bonus
        critChance += attacker.skill * 0.005f;
        
        // Apply affix bonuses
        for (ItemAffix affix : affixes) {
            if (affix.getType() == ItemAffix.AffixType.KEEN) {
                critChance += affix.getPower() / 200f; // Half effect for crit
            }
        }
        
        return Random.Float() < critChance;
    }
    
    @Override
    public void onUse(Hero hero) {
        super.onUse(hero);
        
        // Weapon-specific evolution triggers
        if (useCount % 25 == 0) { // Every 25 uses
            checkWeaponEvolution(hero);
        }
        
        // Apply weapon-specific affixes
        applyWeaponAffixes(hero);
    }
    
    private void checkWeaponEvolution(Hero hero) {
        // Weapons can evolve based on usage patterns
        if (Random.Float() < 0.2f) {
            if (hero.durability > hero.mysticism + hero.skill) {
                // Heavy usage - might gain HEAVY affix
                if (!hasAffix(ItemAffix.AffixType.HEAVY)) {
                    ItemAffix heavyAffix = new ItemAffix(ItemAffix.AffixType.HEAVY, 1);
                    affixes.add(heavyAffix);
                    hero.sprite.showStatus(0x00FF00, "Weapon Evolved!");
                }
            } else if (hero.skill > hero.durability + hero.mysticism) {
                // Precise usage - might gain KEEN affix
                if (!hasAffix(ItemAffix.AffixType.KEEN)) {
                    ItemAffix keenAffix = new ItemAffix(ItemAffix.AffixType.KEEN, 10 + rarity.tier * 5);
                    affixes.add(keenAffix);
                    hero.sprite.showStatus(0x00FF00, "Weapon Evolved!");
                }
            }
        }
    }
    
    private void applyWeaponAffixes(Hero hero) {
        for (ItemAffix affix : affixes) {
            switch (affix.getType()) {
                case THIRSTING:
                    // Weapons remember their kills
                    evolutionProgress += 0.05f;
                    break;
                case GROWING:
                    // Weapons that grow with use
                    if (useCount % 10 == 0) {
                        if (Random.Int(4) == 0) {
                            minDamage++;
                        } else {
                            maxDamage++;
                        }
                    }
                    break;
                case ROOTED:
                    // Connected to the Vale's memory
                    if (Random.Float() < 0.1f) {
                        hero.trackMetric("exploration", 1);
                    }
                    break;
            }
        }
    }
    
    @Override
    public String info() {
        StringBuilder info = new StringBuilder();
        info.append(super.info()).append("\n\n");
        
        info.append("Weapon Statistics:\n");
        info.append("Damage: ").append(minDamage).append("-").append(maxDamage).append("\n");
        info.append("Accuracy: ").append(Math.round(accuracy * 100)).append("%\n");
        info.append("Critical: ").append(Math.round(criticalChance * 100)).append("%\n");
        
        if (rarity == Rarity.PERFECT) {
            info.append("\nThis legendary weapon pulses with otherworldly power...\n");
        }
        
        return info.toString();
    }
    
    @Override
    public String toString() {
        return name + " (" + minDamage + "-" + maxDamage + ")";
    }
}
