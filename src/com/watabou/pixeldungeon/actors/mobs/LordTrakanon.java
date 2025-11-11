/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.items.armor.PlateArmor;
import com.watabou.pixeldungeon.items.potions.PotionOfExperience;
import com.watabou.pixeldungeon.items.potions.PotionOfHealing;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.WraithSprite;
import com.watabou.utils.Random;

/**
 * Lord Trakanon - Legendary undead dragon raid boss
 * The greatest of the undead dragons, cursed by Cazic-Thule
 */
public class LordTrakanon extends Mob {

	{
		name = "Lord Trakanon";
		spriteClass = WraithSprite.class;  // TODO: Create TrakanonSprite

		HP = HT = 600;  // Massive raid boss HP
		defenseSkill = 40;

		EXP = 150;
		maxLvl = 25;

		flying = true;
		immunities.add(Poison.class);  // Immune to poison, is the master of it

		loot = new ScrollOfUpgrade();
		lootChance = 1f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 35, 70 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 45;
	}

	@Override
	public int dr() {
		return 30;
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		// Trakanon's deadly poison breath
		if (Random.Int( 2 ) == 0) {
			Buff.affect( enemy, Poison.class ).set( Random.Int( 5, 10 ) );
		}

		// AoE poison cloud
		if (Random.Int( 4 ) == 0) {
			for (int i : Level.NEIGHBOURS8) {
				int cell = pos + i;
				Char ch = Char.findChar(cell);
				if (ch != null && ch != this) {
					Buff.affect( ch, Poison.class ).set( 3 );
				}
			}
		}

		// Lifetap - heal from damage dealt
		if (Random.Int( 3 ) == 0) {
			HP = Math.min(HT, HP + damage / 2);
		}

		return damage;
	}

	@Override
	public void die( Object cause ) {
		super.die( cause );

		// Legendary loot table
		Dungeon.level.drop( new Gold( Random.IntRange( 800, 1500 ) ), pos ).sprite.drop();

		// Guaranteed epic armor
		Dungeon.level.drop( new PlateArmor(), pos ).sprite.drop();

		// Multiple upgrades
		for (int i = 0; i < Random.IntRange(3, 5); i++) {
			Dungeon.level.drop( new ScrollOfUpgrade(), pos ).sprite.drop();
		}

		// Potions
		for (int i = 0; i < Random.IntRange(2, 4); i++) {
			Dungeon.level.drop( new PotionOfHealing(), pos ).sprite.drop();
		}

		// Rare XP potion
		if (Random.Int(2) == 0) {
			Dungeon.level.drop( new PotionOfExperience(), pos ).sprite.drop();
		}

		Badges.validateBossSlain();
		Badges.validateRare(new LordTrakanon());
	}

	@Override
	public String description() {
		return
			"Lord Trakanon is a name that strikes terror into the hearts of even the bravest heroes. Once a mighty green dragon, " +
			"Trakanon was cursed by Cazic-Thule, the God of Fear, and transformed into an undead abomination. His rotting flesh " +
			"still clings to massive bones, and his breath is a cloud of virulent poison that can kill in seconds. Trakanon's " +
			"intelligence remains intact, making him far more dangerous than any mindless undead. He rules over the Ruins of " +
			"Sebilis, commanding legions of Iksar skeletons and cursed spirits. His poison is so potent it's said to bypass " +
			"all known resistances and protections. Trakanon collects the weapons and armor of fallen heroes, adding them to his " +
			"vast hoard. He particularly delights in breaking the spirits of paladins and clerics, showing them that even holy " +
			"power cannot always triumph over fear and death. Defeating Trakanon is considered one of the greatest achievements " +
			"any adventuring party can claim, proof of their mastery over both combat and fear itself.";
	}
}
