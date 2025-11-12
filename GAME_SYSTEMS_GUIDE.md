# Complete Game Systems Guide - EverQuest II Edition

## 🎮 Core Systems Overview

This guide explains how all systems in the game work together to create the authentic EverQuest II experience.

---

## 📊 **The Four Pillars Stat System**

### **Durability (D.A.D. - Damage Absorption and Defense)**
- **Primary Effect**: Increases maximum HP (+5 HP per point)
- **Secondary Effects**:
  - Increases armor/defense (+1 per point)
  - Increases stamina pool (+5 per point)
- **Best For**: Fighters (Guardians, Paladins, Berserkers)
- **Scales With**: Heavy armor, shield abilities

### **Mysticism (M.A.D. - Magical Aptitude and Divinity)**
- **Primary Effect**: Increases mana pool (+10 mana per point)
- **Secondary Effects**:
  - Increases spell damage (+2 per point)
  - Increases healing power (+1.5 per point)
- **Best For**: Mages, Priests
- **Scales With**: Robes, staves, spell abilities

### **Skill (S.A.D. - Speed, Accuracy, and Dexterity)**
- **Primary Effect**: Increases accuracy (+1 per point)
- **Secondary Effects**:
  - Increases evasion (+1 per point)
  - Increases critical hit chance (+0.5% per point)
  - Affects backstab and precision abilities
- **Best For**: Scouts (Assassins, Rangers, Swashbucklers)
- **Scales With**: Light armor, daggers, bows

### **Presence (P.A.D. - Personality, Awareness, and Divine connection)**
- **Primary Effect**: Increases healing power (+2 per point)
- **Secondary Effects**:
  - Increases buff/debuff duration
  - Affects charm and control abilities
  - Improves companion/pet effectiveness
- **Best For**: Priests, Bards
- **Scales With**: Musical instruments, holy symbols

---

## ⚔️ **Class System**

### **Progression Path**
1. **Level 1**: Choose Archetype (Fighter/Priest/Mage/Scout)
2. **Level 3**: Unlock Subclass choice
3. **Level 5+**: Gain signature abilities

### **Archetype Base Stats**
- **Fighter**: 22 HP, 100 Stamina, 0 Mana
- **Priest**: 18 HP, 50 Stamina, 80 Mana
- **Mage**: 15 HP, 30 Stamina, 100 Mana
- **Scout**: 20 HP, 150 Stamina, 0 Mana

### **Subclass Passive Traits**
Every subclass has unique passive bonuses:
- **Damage Modifiers**: +10% to +50% depending on subclass
- **Defense Modifiers**: -20% to +10% damage taken
- **Resource Regen**: +1 to +2 per turn
- **Critical Chance**: +5% to +15%

**See CLASS_ABILITIES_REFERENCE.md for complete subclass details**

---

## 🧬 **Race + Deity Synergy**

### **How Stats Stack**
Your final stats come from multiple sources:

```
Total Stat = Base + Racial Bonus + Deity Bonus + Equipment + Buffs
```

### **Example Build: Ultimate Wizard**
- **Base**: Mysticism 10
- **Race (Erudite)**: +4 Mysticism, +1 Presence = **Mysticism 14**
- **Deity (Solusek Ro)**: +4 Mysticism = **Mysticism 18**
- **Subclass (Wizard)**: +30% spell damage passive
- **Ring of Erudite**: +mana pool
- **Result**: 280 max mana, massive spell damage

### **Optimization Tips**
1. **Match race to desired stat** (Erudite for Mysticism, Ogre for Durability)
2. **Choose complementary deity** (multiply bonuses, don't diversify)
3. **Pick subclass that enhances your strength**
4. **Equip items that further amplify** your primary stat

---

## 💫 **Resource Management**

### **Mana System**
- **Used By**: Mages, Priests, some hybrid abilities
- **Base Regen**: 2 per turn
- **Bonus Regen**: +1 for Wizard/Warlock/Necromancer, +2 for Sorcerer
- **Recovery Items**: Potion of Mana Restoration (150g) - full restore

### **Stamina System**
- **Used By**: Fighters, Scouts, physical abilities
- **Base Regen**: 5 per turn
- **Bonus Regen**: +2 for Monk/Assassin/Swashbuckler, +1 for others
- **Recovery Items**: Potion of Enduring Stamina (150g) - full restore

### **Resource Tips**
- **Mages/Priests**: Manage mana carefully, save big spells for tough fights
- **Fighters/Scouts**: Stamina regens fast, use abilities liberally
- **Hybrids** (Shadowknight, Paladin): Balance both resources

---

## 🎯 **Combat Mechanics**

### **Damage Calculation**
```
Base Damage = Weapon Damage
Modified By:
  - Primary Stat (Durability for melee, Mysticism for spells, Skill for precision)
  - Subclass Passive (+10% to +50%)
  - Weapon Procs (25% to 40% chance)
  - Critical Hits (base 5% + Skill/2 + Subclass bonus)
```

### **Defense Calculation**
```
Damage Taken = Incoming Damage - Armor
Modified By:
  - Subclass Passive (Guardian -20%, Berserker +10%)
  - Evasion Chance (from Skill)
  - Resistances (from rings, armor)
```

### **Critical Hits**
- **Base Chance**: 5%
- **Modified By**: Skill stat (+0.5% per point)
- **Subclass Bonuses**:
  - Assassin: +15% (total ~20-25%)
  - Swashbuckler/Ranger: +10%
  - Other Scouts: +5%
- **Effects**: Double damage, bypasses some armor

---

## 🗡️ **Item System**

### **Weapon Tiers**
- **Tier 1**: 1-5 damage (common)
- **Tier 3**: 2-6 damage (uncommon)
- **Tier 5**: 3-8 damage (rare)
- **Tier 7**: 5-12 damage (legendary)

### **Armor Tiers**
- **Tier 1**: 2 armor (cloth)
- **Tier 3**: 4 armor (leather)
- **Tier 5**: 6 armor (scale/mail)
- **Tier 7**: 8 armor (plate)

### **Proc Effects**
Many weapons have special effects that trigger on hit:
- **Fire**: Burning damage over time (25% chance)
- **Ice**: Chill/slow effect (35% chance)
- **Poison**: Poison damage over time (35% chance)
- **Lifetap**: Heal on damage (30-40% chance)
- **Stun**: Disable enemy briefly (25% chance)

### **Ring Effects**
Rings provide passive bonuses:
- **Elemental Resistance** (Fire, Cold, Poison)
- **Stat Increases** (Mana, Stamina, Primary stats)
- **Special Effects** (Crit chance, Luck, Regen)

---

## 📜 **Heritage Quests**

Multi-step epic quests that reward legendary items:

### **Quest Structure**
1. **Initiation**: Speak with quest giver
2. **Gathering**: Collect rare materials (10+ items)
3. **Boss Fight**: Defeat a specific named enemy
4. **Component**: Retrieve quest-specific item
5. **Completion**: Return for ritual/forging
6. **Reward**: Legendary weapon

### **Available Quests**
- **The Fiery Avenger**: Paladin sword (Holy damage)
- **Innoruuk's Curse**: Shadowknight blade (Lifetap)
- **Staff of Eternal Flame**: Wizard staff (Fire mastery)

### **Completion Tips**
- Start quests early (materials are scattered throughout depths)
- Boss fights are challenging (depth 18-20)
- Rewards are best-in-slot for their class

---

## ⚡ **Alternate Advancement (AA)**

Endgame progression system with powerful abilities:

### **Earning AA Points**
- **Raid Bosses**: 5 AA points each (Vox, Trakanon, Venril)
- **Heritage Quests**: 10 AA points each
- **Achievements**: Various amounts

### **AA Abilities**
- **Perfect Form** (50 points): Ultimate buff, full resource restore
- **Time Warp** (25 points): Act twice in one turn
- **Divine Intervention** (30 points): Prevent death once
- **Second Wind** (10 points): Emergency heal when low
- **Mana Regeneration** (5-25 points): Passive mana regen +2 per rank

### **AA Strategy**
1. Save for game-changing abilities (Perfect Form, Time Warp)
2. OR invest in passive upgrades (Mana Regen ranks)
3. Divine Intervention is worth it for difficult content
4. Second Wind is cheapest survival tool

---

## 🎲 **Build Archetypes**

### **Tank Build: The Unbreakable Wall**
- **Race**: Ogre/Troll (Max Durability)
- **Class**: Fighter → Guardian
- **Deity**: Rallos Zek/Mithaniel Marr
- **Stats**: 50+ HP at level 1, -20% damage taken
- **Items**: Plate of Eternal Vigilance, Shield
- **Strategy**: Provoke enemies, soak damage, protect party

### **Nuker Build: Glass Cannon**
- **Race**: Erudite/High Elf (Max Mysticism)
- **Class**: Mage → Wizard
- **Deity**: Solusek Ro
- **Stats**: 200+ mana, +30% spell damage
- **Items**: Robes of Archmage, Staff of Eternal Flame
- **Strategy**: Fireball groups, Ice Comet bosses, manage mana

### **Assassin Build: One-Shot Wonder**
- **Race**: Halfling/Ratonga (Max Skill)
- **Class**: Scout → Assassin
- **Deity**: Bristlebane
- **Stats**: 20-25% crit chance, +50% backstab
- **Items**: Leathers of Silent Hunter, Crimson Tempest
- **Strategy**: Backstab from stealth, Eviscerate finisher

### **Healer Build: Life Saver**
- **Race**: Erudite/High Elf (Mysticism + Presence)
- **Class**: Priest → Cleric
- **Deity**: Rodcet Nife
- **Stats**: Massive healing power, 150+ mana
- **Items**: Vestments of High Priest, Water Sprinkler
- **Strategy**: Group Heal, Celestial Healing, keep party alive

### **Hybrid Build: Versatile Warrior**
- **Race**: Human (Balanced)
- **Class**: Fighter → Shadowknight OR Priest → Inquisitor
- **Deity**: Varies
- **Stats**: Mix of melee and magic
- **Items**: Shadowplate, Innoruuk's Curse
- **Strategy**: Lifetap for sustain, tank when needed, DPS when safe

---

## 💡 **Advanced Tips**

### **Combat**
- **Save ultimates for bosses** (Harm Touch, Perfect Form)
- **Use terrain** - Doorways create chokepoints
- **Focus fire** - Kill dangerous enemies first
- **Manage aggro** - Tanks provoke, DPS wait

### **Resources**
- **Carry potions** - Always have mana/stamina backup
- **Rest when safe** - Resources regenerate over time
- **Food matters** - Dwarven Ale, Elven Waybread provide bonuses

### **Progression**
- **Don't rush subclass** - Learn base class first
- **Complete Heritage Quests** - Best weapons in game
- **Fight raid bosses** - AA points are crucial
- **Explore thoroughly** - Named mobs drop better loot

### **Build Optimization**
- **Focus one stat** - Specialization > generalization
- **Match equipment to build** - All items should support your playstyle
- **Read passive traits** - Subclass bonuses are powerful
- **Test abilities** - Find rotation that works for you

---

## 🏆 **Endgame Content**

### **Raid Bosses** (Depths 18-20+)
- **Lady Vox** (500 HP): Ice dragon, freezing attacks
- **Lord Trakanon** (600 HP): Undead dragon, deadly poison
- **Venril Sathir** (450 HP): Iksar lich, summons minions

### **Strategy Per Boss**
- **Vox**: Cold resistance crucial, mobile fight
- **Trakanon**: Poison immunity helps, heal through damage
- **Venril**: Kill adds quickly, interrupt summons

### **Rewards**
- **Epic loot** (500-1000g weapons, legendary armor)
- **AA points** (5 per boss)
- **Achievement** (Raid boss slayer badge)

---

**Master these systems and become a legend of Norrath!**

Total Word Count: 1800+
Total Systems Documented: 12
Depth: Comprehensive
