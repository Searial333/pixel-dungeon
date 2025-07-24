/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.particles.SparkParticle;
import com.watabou.pixeldungeon.items.weapon.melee.MeleeWeapon;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class ArcaneStaff extends MeleeWeapon {

	{
		name = "arcane staff";
		image = ItemSpriteSheet.WAND_MAGIC_MISSILE;
		
		tier = 1;
		STR = 8;
		MIN = 2;
		MAX = 8;
		
		unique = true;
	}
	
	@Override
	public int proc( Char attacker, Char defender, int damage ) {
		
		if (attacker instanceof Hero) {
			Hero hero = (Hero)attacker;
			
			if (Random.Int( 4 ) == 0) {
				// Mana restoration
				if (hero.manaPool < hero.maxManaPool) {
					hero.manaPool = Math.min(hero.maxManaPool, hero.manaPool + Random.Int(5, 10));
					GLog.p( "The staff channels arcane energy!" );
					attacker.sprite.emitter().burst( SparkParticle.FACTORY, 3 );
				}
			}
		}
		
		return damage;
	}
	
	@Override
	public String desc() {
		return 
			"This crystalline staff thrums with arcane power. Carved from a single piece of " +
			"enchanted crystal, it amplifies magical energies and occasionally restores mana " +
			"to its wielder during combat. Ancient runes spiral along its length.";
	}
}
