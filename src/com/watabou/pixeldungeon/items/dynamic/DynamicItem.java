/*
 * Hollowroot Vale - Dynamic Item System
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.dynamic;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DynamicItem extends Item {
    
    // Rarity Tiers - Quality Curve
    public enum Rarity {
        CRUDE(0, "Crude", 0.4f, 1, 0.1f),           // Bottom barrel - fragile, cursed
        SIMPLE(1, "Simple", 0.7f, 2, 0.25f),        // Everyday gear - reliable  
        STANDARD(2, "Standard", 1.0f, 3, 0.5f),     // Adventurer's mainstay
        PREMIUM(3, "Premium", 1.4f, 4, 0.8f),       // Master-forged - glows with personality
        PERFECT(4, "Perfect", 2.0f, 6, 1.0f);       // Artifacts of saga and doom
        
        public final int tier;
        public final String label;
        public final float statMultiplier;
        public final int maxAffixes;
        public final float mutationChance;
        
        Rarity(int tier, String label, float statMultiplier, int maxAffixes, float mutationChance) {
            this.tier = tier;
            this.label = label;
            this.statMultiplier = statMultiplier;
            this.maxAffixes = maxAffixes;
            this.mutationChance = mutationChance;
        }
    }
    
    // Item Roots - Types & Slots
    public enum ItemRoot {
        WEAPON("Weapon", new String[]{"Sword", "Dagger", "Axe", "Sickle", "Scythe", "Pitchfork", "Wand", "Tuning Fork"}),
        TOOL("Tool", new String[]{"Hoe", "Shovel", "Pickaxe", "Lantern", "Loom", "Sieve", "Trowel", "Bellows"}),
        RELIC("Relic", new String[]{"Bone Charm", "Talisman", "Cursed Coin", "Mask", "Memory Locket", "Fractal Key"}),
        ARMOR("Armor", new String[]{"Cloak", "Mask", "Breastplate", "Scarecrow Shell", "Patchwork Hood", "Living Mantle", "Root Crown"}),
        SEEDLING("Seedling", new String[]{"Arcane Pumpkin", "Whisper Sapling", "Bloodroot", "Memory Moss", "Thorn Seed"});
        
        public final String category;
        public final String[] subtypes;
        
        ItemRoot(String category, String[] subtypes) {
            this.category = category;
            this.subtypes = subtypes;
        }
    }
    
    // Item Heritage - Origin modifiers
    public enum Heritage {
        MILITIA_ISSUE("Militia Issue", 0.9f, 1.1f, 0.8f, 1.0f),
        ROYAL("Royal", 1.3f, 1.0f, 1.1f, 1.4f),
        FUNGAL("Fungal", 0.8f, 1.2f, 0.9f, 1.1f),
        CURSED("Cursed", 1.2f, 1.3f, 0.7f, 0.6f),
        BLESSED("Blessed", 1.1f, 0.9f, 1.2f, 1.3f),
        ANCIENT("Ancient", 1.0f, 1.4f, 1.0f, 1.2f),
        MAKESHIFT("Makeshift", 0.7f, 0.8f, 1.1f, 0.9f),
        LIVING("Living", 0.9f, 1.1f, 1.0f, 1.2f);
        
        public final String name;
        public final float durabilityMod;
        public final float mysticismMod; 
        public final float skillMod;
        public final float presenceMod;
        
        Heritage(String name, float durabilityMod, float mysticismMod, float skillMod, float presenceMod) {
            this.name = name;
            this.durabilityMod = durabilityMod;
            this.mysticismMod = mysticismMod;
            this.skillMod = skillMod;
            this.presenceMod = presenceMod;
        }
    }
    
    // Core properties
    protected Rarity rarity;
    protected ItemRoot root;
    protected String subtype;
    protected Heritage heritage;
    protected List<ItemAffix> affixes;
    protected String legendaryName;
    protected int useCount;
    protected float evolutionProgress;
    
    // Four Pillars stat bonuses
    protected int durabilityBonus;
    protected int mysticismBonus;
    protected int skillBonus;
    protected int presenceBonus;
    
    public DynamicItem() {
        super();
        this.affixes = new ArrayList<>();
        this.useCount = 0;
        this.evolutionProgress = 0f;
        initializeItem();
    }
    
    private void initializeItem() {
        generateRandomItem();
    }
    
    protected void generateRandomItem() {
        // Generate rarity based on weighted distribution
        this.rarity = generateRarity();
        
        // Choose random root and subtype
        this.root = ItemRoot.values()[Random.Int(ItemRoot.values().length)];
        this.subtype = root.subtypes[Random.Int(root.subtypes.length)];
        
        // Generate heritage
        this.heritage = Heritage.values()[Random.Int(Heritage.values().length)];
        
        // Generate base stats using Four Pillars
        generateStats();
        
        // Generate affixes based on rarity
        generateAffixes();
        
        // Create name
        generateName();
    }
    
    protected Rarity generateRarity() {
        float roll = Random.Float();
        if (roll < 0.4f) return Rarity.CRUDE;
        if (roll < 0.7f) return Rarity.SIMPLE;
        if (roll < 0.9f) return Rarity.STANDARD;
        if (roll < 0.98f) return Rarity.PREMIUM;
        return Rarity.PERFECT;
    }
    
    protected void generateStats() {
        // Base stats scaled by rarity and heritage
        float baseValue = 2 + (rarity.tier * 3);
        
        durabilityBonus = Math.round(baseValue * rarity.statMultiplier * heritage.durabilityMod);
        mysticismBonus = Math.round(baseValue * rarity.statMultiplier * heritage.mysticismMod);
        skillBonus = Math.round(baseValue * rarity.statMultiplier * heritage.skillMod);
        presenceBonus = Math.round(baseValue * rarity.statMultiplier * heritage.presenceMod);
        
        // Ensure minimum values
        durabilityBonus = Math.max(0, durabilityBonus);
        mysticismBonus = Math.max(0, mysticismBonus);
        skillBonus = Math.max(0, skillBonus);
        presenceBonus = Math.max(0, presenceBonus);
    }
    
    protected void generateAffixes() {
        int affixCount = Random.Int(1, rarity.maxAffixes + 1);
        for (int i = 0; i < affixCount; i++) {
            ItemAffix affix = ItemAffix.generateRandomAffix(rarity, root);
            if (affix != null && !hasAffix(affix.type)) {
                affixes.add(affix);
            }
        }
    }
    
    protected void generateName() {
        if (rarity == Rarity.PERFECT && Random.Float() < 0.5f) {
            // Perfect items may have legendary names
            legendaryName = generateLegendaryName();
            name = legendaryName;
        } else {
            // Standard naming convention
            StringBuilder sb = new StringBuilder();
            if (heritage != Heritage.MILITIA_ISSUE) {
                sb.append(heritage.name).append(" ");
            }
            sb.append(rarity.label).append(" ");
            sb.append(subtype);
            name = sb.toString();
        }
    }
    
    protected String generateLegendaryName() {
        String[] prefixes = {"Whisper of", "Song of", "Memory of", "Dream of", "Shadow of", "Root of"};
        String[] suffixes = {"the Forgotten", "the Hollow", "the Deep", "the Ancient", "the Lost", "the Eternal"};
        return prefixes[Random.Int(prefixes.length)] + " " + suffixes[Random.Int(suffixes.length)];
    }
    
    public void onUse(Hero hero) {
        useCount++;
        evolutionProgress += 0.1f;
        
        // Check for mutation/evolution
        if (evolutionProgress >= 1.0f && Random.Float() < rarity.mutationChance) {
            evolve(hero);
        }
        
        // Track usage for Four Pillars system
        hero.trackMetric("item_use", 1);
    }
    
    protected void evolve(Hero hero) {
        evolutionProgress = 0f;
        
        // Chance to gain new affix or upgrade rarity
        if (Random.Float() < 0.3f && rarity.tier < Rarity.PERFECT.tier) {
            upgradeRarity();
        } else if (affixes.size() < rarity.maxAffixes) {
            ItemAffix newAffix = ItemAffix.generateRandomAffix(rarity, root);
            if (newAffix != null && !hasAffix(newAffix.type)) {
                affixes.add(newAffix);
            }
        }
        
        // Regenerate stats and name
        generateStats();
        generateName();
    }
    
    protected void upgradeRarity() {
        if (rarity.tier < Rarity.PERFECT.tier) {
            rarity = Rarity.values()[rarity.tier + 1];
        }
    }
    
    protected boolean hasAffix(ItemAffix.AffixType type) {
        return affixes.stream().anyMatch(affix -> affix.type == type);
    }
    
    // Four Pillars integration
    public int getDurabilityBonus() { return durabilityBonus; }
    public int getMysticismBonus() { return mysticismBonus; }
    public int getSkillBonus() { return skillBonus; }
    public int getPresenceBonus() { return presenceBonus; }
    
    public Rarity getRarity() { return rarity; }
    public ItemRoot getRoot() { return root; }
    public Heritage getHeritage() { return heritage; }
    public List<ItemAffix> getAffixes() { return new ArrayList<>(affixes); }
    public int getUseCount() { return useCount; }
    
    @Override
    public String info() {
        StringBuilder info = new StringBuilder();
        info.append(name).append("\n\n");
        info.append("Rarity: ").append(rarity.label).append("\n");
        info.append("Heritage: ").append(heritage.name).append("\n");
        info.append("Type: ").append(root.category).append(" - ").append(subtype).append("\n\n");
        
        info.append("Four Pillars Bonuses:\n");
        if (durabilityBonus > 0) info.append("Durability: +").append(durabilityBonus).append("\n");
        if (mysticismBonus > 0) info.append("Mysticism: +").append(mysticismBonus).append("\n");
        if (skillBonus > 0) info.append("Skill: +").append(skillBonus).append("\n");
        if (presenceBonus > 0) info.append("Presence: +").append(presenceBonus).append("\n");
        
        if (!affixes.isEmpty()) {
            info.append("\nSpecial Properties:\n");
            for (ItemAffix affix : affixes) {
                info.append("• ").append(affix.getDescription()).append("\n");
            }
        }
        
        info.append("\nUses: ").append(useCount);
        if (evolutionProgress > 0) {
            info.append(" | Evolution: ").append(Math.round(evolutionProgress * 100)).append("%");
        }
        
        return info.toString();
    }
    
    // Bundle serialization
    private static final String RARITY = "rarity";
    private static final String ROOT = "root";
    private static final String SUBTYPE = "subtype";
    private static final String HERITAGE = "heritage";
    private static final String AFFIXES = "affixes";
    private static final String LEGENDARY_NAME = "legendary_name";
    private static final String USE_COUNT = "use_count";
    private static final String EVOLUTION = "evolution";
    private static final String DURABILITY_BONUS = "durability_bonus";
    private static final String MYSTICISM_BONUS = "mysticism_bonus";
    private static final String SKILL_BONUS = "skill_bonus";
    private static final String PRESENCE_BONUS = "presence_bonus";
    
    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(RARITY, rarity.ordinal());
        bundle.put(ROOT, root.ordinal());
        bundle.put(SUBTYPE, subtype);
        bundle.put(HERITAGE, heritage.ordinal());
        bundle.put(AFFIXES, affixes);
        bundle.put(LEGENDARY_NAME, legendaryName);
        bundle.put(USE_COUNT, useCount);
        bundle.put(EVOLUTION, evolutionProgress);
        bundle.put(DURABILITY_BONUS, durabilityBonus);
        bundle.put(MYSTICISM_BONUS, mysticismBonus);
        bundle.put(SKILL_BONUS, skillBonus);
        bundle.put(PRESENCE_BONUS, presenceBonus);
    }
    
    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        rarity = Rarity.values()[bundle.getInt(RARITY)];
        root = ItemRoot.values()[bundle.getInt(ROOT)];
        subtype = bundle.getString(SUBTYPE);
        heritage = Heritage.values()[bundle.getInt(HERITAGE)];
        legendaryName = bundle.getString(LEGENDARY_NAME);
        useCount = bundle.getInt(USE_COUNT);
        evolutionProgress = bundle.getFloat(EVOLUTION);
        durabilityBonus = bundle.getInt(DURABILITY_BONUS);
        mysticismBonus = bundle.getInt(MYSTICISM_BONUS);
        skillBonus = bundle.getInt(SKILL_BONUS);
        presenceBonus = bundle.getInt(PRESENCE_BONUS);
        
        affixes = new ArrayList<>();
        Collection<Bundlable> collection = bundle.getCollection(AFFIXES);
        for (Bundlable obj : collection) {
            if (obj instanceof ItemAffix) {
                affixes.add((ItemAffix) obj);
            }
        }
    }
}
