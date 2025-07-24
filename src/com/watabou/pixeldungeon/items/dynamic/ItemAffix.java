/*
 * Hollowroot Vale - Item Affix System
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.dynamic;

import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class ItemAffix implements Bundlable {
    
    public enum AffixType {
        // Combat Affixes
        SHARP("Sharp", "Increases damage by {0}%"),
        KEEN("Keen", "Critical hit chance +{0}%"),
        HEAVY("Heavy", "Knockback on hit"),
        LIGHT("Light", "Attack speed +{0}%"),
        
        // Magic Affixes  
        ARCANE("Arcane", "Spell power +{0}%"),
        MYSTICAL("Mystical", "Mana regeneration +{0}%/turn"),
        CURSED("Cursed", "Deals {0} damage to self on use"),
        BLESSED("Blessed", "Heals {0} HP on use"),
        
        // Utility Affixes
        STURDY("Sturdy", "Durability does not decrease"),
        GROWING("Growing", "Gains +1 to random stat per 10 uses"),
        ECHOING("Echoing", "33% chance to not consume on use"),
        THIRSTING("Thirsting", "Absorbs enemy essence on kill"),
        
        // Hollowroot Vale Specific
        ROOTED("Rooted", "Connects to the Vale's memory"),
        WHISPERING("Whispering", "Reveals hidden paths"),
        NIGHTMARE("Nightmare", "Haunted by previous owner"),
        FOLKLORIC("Folkloric", "NPCs react with fear/reverence"),
        
        // Seasonal/Environmental  
        AUTUMNAL("Autumnal", "Gains power during harvest season"),
        FUNGAL("Fungal", "Spreads spores when damaged"),
        VERDANT("Verdant", "Regenerates when near plants"),
        HOLLOW("Hollow", "Echoes with ancient voices");
        
        public final String name;
        public final String description;
        
        AffixType(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
    
    public AffixType type;
    public int power;
    public String flavorText;
    
    public ItemAffix() {
        // Default constructor for Bundle restoration
    }
    
    public ItemAffix(AffixType type, int power) {
        this.type = type;
        this.power = power;
        this.flavorText = generateFlavorText();
    }
    
    public static ItemAffix generateRandomAffix(DynamicItem.Rarity rarity, DynamicItem.ItemRoot root) {
        AffixType[] availableAffixes = getAffixesForRoot(root);
        if (availableAffixes.length == 0) return null;
        
        AffixType chosenType = availableAffixes[Random.Int(availableAffixes.length)];
        int power = calculatePower(chosenType, rarity);
        
        return new ItemAffix(chosenType, power);
    }
    
    private static AffixType[] getAffixesForRoot(DynamicItem.ItemRoot root) {
        switch (root) {
            case WEAPON:
                return new AffixType[]{
                    AffixType.SHARP, AffixType.KEEN, AffixType.HEAVY, AffixType.LIGHT,
                    AffixType.CURSED, AffixType.BLESSED, AffixType.THIRSTING, 
                    AffixType.NIGHTMARE, AffixType.FOLKLORIC
                };
            case TOOL:
                return new AffixType[]{
                    AffixType.STURDY, AffixType.GROWING, AffixType.ECHOING,
                    AffixType.ROOTED, AffixType.VERDANT, AffixType.AUTUMNAL
                };
            case RELIC:
                return new AffixType[]{
                    AffixType.ARCANE, AffixType.MYSTICAL, AffixType.WHISPERING,
                    AffixType.HOLLOW, AffixType.FOLKLORIC, AffixType.NIGHTMARE
                };
            case ARMOR:
                return new AffixType[]{
                    AffixType.STURDY, AffixType.BLESSED, AffixType.GROWING,
                    AffixType.FUNGAL, AffixType.VERDANT, AffixType.ROOTED
                };
            case SEEDLING:
                return new AffixType[]{
                    AffixType.GROWING, AffixType.VERDANT, AffixType.AUTUMNAL,
                    AffixType.ROOTED, AffixType.WHISPERING, AffixType.FUNGAL
                };
            default:
                return new AffixType[0];
        }
    }
    
    private static int calculatePower(AffixType type, DynamicItem.Rarity rarity) {
        int basePower = 1 + rarity.tier;
        
        switch (type) {
            case SHARP:
            case KEEN:
            case LIGHT:
            case ARCANE:
            case MYSTICAL:
                return basePower * 5; // Percentage-based affixes
            case CURSED:
            case BLESSED:
                return basePower * 2; // HP-based affixes
            case HEAVY:
            case STURDY:
            case ECHOING:
            case THIRSTING:
            case ROOTED:
            case WHISPERING:
            case NIGHTMARE:
            case FOLKLORIC:
            case AUTUMNAL:
            case FUNGAL:
            case VERDANT:
            case HOLLOW:
                return 1; // Binary/special effect affixes
            case GROWING:
                return basePower; // Stat growth affixes
            default:
                return basePower;
        }
    }
    
    private String generateFlavorText() {
        switch (type) {
            case SHARP: return "The blade gleams with wicked intent.";
            case KEEN: return "It seems to find weakness by instinct.";
            case HEAVY: return "The weight behind each blow tells a story.";
            case LIGHT: return "Swift as autumn wind through bare branches.";
            case ARCANE: return "Starlight dwells within its form.";
            case MYSTICAL: return "The very air hums around it.";
            case CURSED: return "Something hungry stirs within.";
            case BLESSED: return "Warmth radiates from its core.";
            case STURDY: return "Built to outlast empires.";
            case GROWING: return "It learns from every touch.";
            case ECHOING: return "Use echoes strangely, as if time hiccups.";
            case THIRSTING: return "It drinks deep of defeat.";
            case ROOTED: return "Threads of memory bind it to the Vale.";
            case WHISPERING: return "Secrets spill from its presence.";
            case NIGHTMARE: return "The previous owner's terror lingers.";
            case FOLKLORIC: return "Legends are born from such things.";
            case AUTUMNAL: return "Harvest moon strengthens its power.";
            case FUNGAL: return "Life finds a way to spread.";
            case VERDANT: return "Green life calls to green life.";
            case HOLLOW: return "Ancient voices echo from within.";
            default: return "Strange power flows through it.";
        }
    }
    
    public String getDescription() {
        String desc = String.format(type.description, power);
        if (flavorText != null && !flavorText.isEmpty()) {
            desc += "\n\"" + flavorText + "\"";
        }
        return desc;
    }
    
    public String getName() {
        return type.name;
    }
    
    public int getPower() {
        return power;
    }
    
    public AffixType getType() {
        return type;
    }
    
    // Bundle serialization
    private static final String TYPE = "type";
    private static final String POWER = "power";
    private static final String FLAVOR = "flavor";
    
    @Override
    public void storeInBundle(Bundle bundle) {
        bundle.put(TYPE, type.ordinal());
        bundle.put(POWER, power);
        bundle.put(FLAVOR, flavorText);
    }
    
    @Override
    public void restoreFromBundle(Bundle bundle) {
        type = AffixType.values()[bundle.getInt(TYPE)];
        power = bundle.getInt(POWER);
        flavorText = bundle.getString(FLAVOR);
    }
}
