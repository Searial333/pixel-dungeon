/*
 * Hollowroot Vale - Dynamic Item System Demo
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.dynamic;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.utils.GLog;

public class DynamicItemDemo {
    
    public static void demonstrateHollowrootValeSystem() {
        GLog.p("=== HOLLOWROOT VALE DYNAMIC ITEM SYSTEM DEMO ===");
        
        // Create a test hero
        Hero hero = new Hero();
        
        GLog.p("\n🌾 Creating Dynamic Items...\n");
        
        // Create various dynamic items
        DynamicWeapon weapon = new DynamicWeapon();
        DynamicTool tool = new DynamicTool();
        
        GLog.p("Generated Weapon: " + weapon.toString());
        GLog.p("  ➤ Rarity: " + weapon.getRarity().label);
        GLog.p("  ➤ Heritage: " + weapon.getHeritage().name);
        GLog.p("  ➤ Type: " + weapon.subtype);
        GLog.p("  ➤ Damage: " + weapon.minDamage + "-" + weapon.maxDamage);
        GLog.p("  ➤ Four Pillars Bonuses:");
        GLog.p("    • Durability: +" + weapon.getDurabilityBonus());
        GLog.p("    • Mysticism: +" + weapon.getMysticismBonus());
        GLog.p("    • Skill: +" + weapon.getSkillBonus());
        GLog.p("    • Presence: +" + weapon.getPresenceBonus());
        
        if (!weapon.getAffixes().isEmpty()) {
            GLog.p("  ➤ Special Properties:");
            for (ItemAffix affix : weapon.getAffixes()) {
                GLog.p("    • " + affix.getName() + ": " + affix.getPower());
            }
        }
        
        GLog.p("\nGenerated Tool: " + tool.toString());
        GLog.p("  ➤ Rarity: " + tool.getRarity().label);
        GLog.p("  ➤ Heritage: " + tool.getHeritage().name);
        GLog.p("  ➤ Type: " + tool.subtype);
        GLog.p("  ➤ Efficiency: " + tool.efficiency);
        GLog.p("  ➤ Durability: " + tool.durabilityRating);
        
        if (!tool.getAffixes().isEmpty()) {
            GLog.p("  ➤ Special Properties:");
            for (ItemAffix affix : tool.getAffixes()) {
                GLog.p("    • " + affix.getName() + ": " + affix.getPower());
            }
        }
        
        GLog.p("\n⚔️ Testing Combat with Dynamic Weapon...\n");
        
        // Simulate combat usage
        for (int i = 0; i < 10; i++) {
            weapon.onUse(hero);
            int damage = weapon.damageRoll(hero);
            GLog.p("Attack " + (i+1) + ": " + damage + " damage dealt");
            
            if (weapon.useCount % 5 == 0) {
                GLog.p("  • Weapon gains experience from battle...");
            }
        }
        
        GLog.p("\n🔨 Testing Tool Usage...\n");
        
        // Simulate tool usage
        String[] tasks = {"farm the fields", "mine for ore", "dig for treasure", "light the darkness"};
        for (String task : tasks) {
            boolean success = tool.useTool(hero, task);
            GLog.p("Attempt to " + task + ": " + (success ? "SUCCESS" : "FAILED"));
        }
        
        GLog.p("\n📊 Item Evolution Progress:");
        GLog.p("Weapon Evolution: " + Math.round(weapon.evolutionProgress * 100) + "%");
        GLog.p("Tool Evolution: " + Math.round(tool.evolutionProgress * 100) + "%");
        
        GLog.p("\n🎭 Demonstrating Rarity System...\n");
        
        // Show different rarity items
        for (DynamicItem.Rarity rarity : DynamicItem.Rarity.values()) {
            GLog.p(rarity.label + " Rarity:");
            GLog.p("  ➤ Max Affixes: " + rarity.maxAffixes);
            GLog.p("  ➤ Stat Multiplier: " + rarity.statMultiplier + "x");
            GLog.p("  ➤ Mutation Chance: " + Math.round(rarity.mutationChance * 100) + "%");
        }
        
        GLog.p("\n🏰 Heritage System Examples...\n");
        
        // Show different heritage effects
        for (DynamicItem.Heritage heritage : DynamicItem.Heritage.values()) {
            GLog.p(heritage.name + " Heritage:");
            GLog.p("  ➤ D.A.D. Modifier: " + heritage.durabilityMod + "x");
            GLog.p("  ➤ M.A.D. Modifier: " + heritage.mysticismMod + "x");
            GLog.p("  ➤ S.A.D. Modifier: " + heritage.skillMod + "x");
            GLog.p("  ➤ P.A.D. Modifier: " + heritage.presenceMod + "x");
        }
        
        GLog.p("\n✨ HOLLOWROOT VALE SYSTEM FEATURES DEMONSTRATED:");
        GLog.p("✓ Five-tier rarity system (Crude → Perfect)");
        GLog.p("✓ Heritage-based stat modifications");
        GLog.p("✓ Four Pillars integration (D.A.D., M.A.D., S.A.D., P.A.D.)");
        GLog.p("✓ Dynamic affix system with 16 unique properties");
        GLog.p("✓ Item evolution through usage");
        GLog.p("✓ Procedural naming with legendary variants");
        GLog.p("✓ Deep integration with hero progression");
        GLog.p("✓ Memory-in-metal: items that remember their history");
        
        GLog.p("\n🌿 Items in Hollowroot Vale aren't just loot—they're living records!");
        GLog.p("Every rusted trowel and haunted mask tells a story...");
    }
}
