/*
 * Hollowroot Vale - Dynamic Tool Implementation  
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.dynamic;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class DynamicTool extends DynamicItem {
    
    // Tool-specific properties
    protected int efficiency;
    protected int durabilityRating;
    protected boolean canBreak;
    
    public DynamicTool() {
        super();
        // Force tool root
        this.root = ItemRoot.TOOL;
        this.subtype = root.subtypes[Random.Int(root.subtypes.length)];
        generateToolStats();
        setToolImage();
    }
    
    private void generateToolStats() {
        // Tools have efficiency and durability rather than damage
        efficiency = Math.round((3 + rarity.tier * 2) * heritage.skillMod * rarity.statMultiplier);
        durabilityRating = Math.round((5 + rarity.tier * 3) * heritage.durabilityMod * rarity.statMultiplier);
        canBreak = rarity.tier < 3; // Premium and Perfect tools don't break
        
        // Ensure minimum values
        efficiency = Math.max(1, efficiency);
        durabilityRating = Math.max(3, durabilityRating);
    }
    
    private void setToolImage() {
        // Set sprite based on subtype
        switch (subtype) {
            case "Hoe":
            case "Shovel":
                image = ItemSpriteSheet.PICKAXE; // Use pickaxe sprite for farming tools
                break;
            case "Pickaxe":
                image = ItemSpriteSheet.PICKAXE;
                break;
            case "Lantern":
                image = ItemSpriteSheet.TORCH;
                break;
            case "Trowel":
                image = ItemSpriteSheet.DAGGER; // Small tool
                break;
            default:
                image = ItemSpriteSheet.PICKAXE;
                break;
        }
    }
    
    public boolean useTool(Hero hero, String task) {
        onUse(hero);
        
        // Calculate success chance based on tool efficiency and hero skill
        float successChance = (efficiency + hero.skill) / 20f;
        successChance = Math.min(0.95f, successChance); // Cap at 95%
        
        // Apply affix effects
        for (ItemAffix affix : affixes) {
            switch (affix.getType()) {
                case STURDY:
                    // Sturdy tools don't break
                    canBreak = false;
                    break;
                case ECHOING:
                    // 33% chance to not consume durability
                    if (Random.Int(3) == 0) {
                        GLog.p("The " + name + " echoes with power - no wear!");
                        return Random.Float() < successChance;
                    }
                    break;
                case VERDANT:
                    // Better with plant-related tasks
                    if (task.contains("plant") || task.contains("farm") || task.contains("garden")) {
                        successChance += 0.2f;
                    }
                    break;
                case AUTUMNAL:
                    // Stronger during harvest season (simulate with random chance)
                    if (Random.Int(4) == 0) { // 25% of the time it's "harvest season"
                        successChance += 0.15f;
                        GLog.i("The harvest moon strengthens your tool!");
                    }
                    break;
            }
        }
        
        boolean success = Random.Float() < successChance;
        
        // Tool wear and potential breaking
        if (canBreak && !hasAffix(ItemAffix.AffixType.STURDY)) {
            durabilityRating--;
            if (durabilityRating <= 0) {
                GLog.n("Your " + name + " breaks from overuse!");
                hero.belongings.backpack.items.remove(this);
                return false;
            }
        }
        
        // Success effects
        if (success) {
            applySuccessEffects(hero, task);
        } else {
            GLog.w("Your attempt with the " + name + " fails.");
        }
        
        return success;
    }
    
    private void applySuccessEffects(Hero hero, String task) {
        // Tool-specific success effects
        switch (subtype) {
            case "Hoe":
            case "Shovel":
                if (task.contains("farm") || task.contains("dig")) {
                    hero.trackMetric("exploration", 1);
                    // Farming tools might uncover items
                    if (Random.Int(10) == 0) {
                        GLog.p("You uncover something buried!");
                    }
                }
                break;
            case "Pickaxe":
                if (task.contains("mine") || task.contains("break")) {
                    hero.trackMetric("exploration", 2);
                    // Mining might yield resources
                    if (Random.Int(8) == 0) {
                        GLog.p("Your pickaxe strikes something valuable!");
                    }
                }
                break;
            case "Lantern":
                if (task.contains("light") || task.contains("explore")) {
                    hero.trackMetric("exploration", 1);
                    // Lanterns reveal secrets
                    if (Random.Int(12) == 0) {
                        GLog.p("The light reveals a hidden passage!");
                    }
                }
                break;
        }
        
        // Apply affix success effects
        for (ItemAffix affix : affixes) {
            switch (affix.getType()) {
                case GROWING:
                    // Tools that learn
                    if (Random.Int(20) == 0) {
                        efficiency++;
                        GLog.p("Your " + name + " seems more effective!");
                    }
                    break;
                case ROOTED:
                    // Connected to the Vale
                    if (Random.Int(15) == 0) {
                        GLog.i("The tool resonates with ancient memories...");
                        hero.trackMetric("exploration", 1);
                    }
                    break;
                case WHISPERING:
                    // Reveals secrets
                    if (Random.Int(10) == 0) {
                        GLog.i("The tool whispers of hidden things nearby...");
                    }
                    break;
            }
        }
    }
    
    @Override
    public void onUse(Hero hero) {
        super.onUse(hero);
        
        // Tool-specific evolution
        if (useCount % 30 == 0) { // Every 30 uses
            checkToolEvolution(hero);
        }
    }
    
    private void checkToolEvolution(Hero hero) {
        // Tools evolve based on how they're used
        if (Random.Float() < 0.15f) {
            if (hero.skill > 15 && !hasAffix(ItemAffix.AffixType.GROWING)) {
                // Skilled use makes tools learn
                ItemAffix growingAffix = new ItemAffix(ItemAffix.AffixType.GROWING, 1);
                affixes.add(growingAffix);
                GLog.p("Your " + name + " has learned from your skilled use!");
            } else if (hero.presence > 15 && !hasAffix(ItemAffix.AffixType.ROOTED)) {
                // High presence connects tools to the Vale
                ItemAffix rootedAffix = new ItemAffix(ItemAffix.AffixType.ROOTED, 1);
                affixes.add(rootedAffix);
                GLog.i("Your " + name + " becomes one with the Vale's memory...");
            }
        }
    }
    
    @Override
    public String info() {
        StringBuilder info = new StringBuilder();
        info.append(super.info()).append("\n\n");
        
        info.append("Tool Statistics:\n");
        info.append("Efficiency: ").append(efficiency).append("\n");
        info.append("Durability: ").append(durabilityRating);
        if (!canBreak) {
            info.append(" (Unbreakable)");
        }
        info.append("\n");
        
        // Tool-specific flavor
        switch (subtype) {
            case "Hoe":
                info.append("\nTurns soil for planting. Essential for any farmer.\n");
                break;
            case "Shovel":
                info.append("\nDigs deep holes and uncovers buried secrets.\n");
                break;
            case "Pickaxe":
                info.append("\nBreaks through stone and hard earth.\n");
                break;
            case "Lantern":
                info.append("\nCasts light into dark places, revealing hidden paths.\n");
                break;
            case "Trowel":
                info.append("\nA delicate tool for precise work and gentle cultivation.\n");
                break;
        }
        
        if (rarity == Rarity.PERFECT) {
            info.append("\nThis legendary tool seems to work of its own accord...\n");
        }
        
        return info.toString();
    }
    
    @Override
    public String toString() {
        return name + " (Eff:" + efficiency + " Dur:" + durabilityRating + ")";
    }
}
