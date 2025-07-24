/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.buffs.Invisibility;
import com.watabou.pixeldungeon.effects.particles.ShadowParticle;
import com.watabou.pixeldungeon.items.weapon.melee.MeleeWeapon;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class VoidBlade extends MeleeWeapon {

	{
		name = "void blade";
		image = ItemSpriteSheet.SWORD;
		
		tier = 2;
		STR = 9;
		MIN = 2;
		MAX = 14;
		
		unique = true;
	}
	
	@Override
	public int proc( Char attacker, Char defender, int damage ) {
		
		if (attacker instanceof Hero) {
			
			if (Random.Int( 6 ) == 0) {
				// Shadow strike - brief invisibility
				GLog.w( "The void blade cloaks you in shadows!" );
				attacker.sprite.emitter().burst( ShadowParticle.UP, 5 );
				
				// Grant brief invisibility after attack
				Invisibility.affect( attacker, 2f );
				
				return damage + Random.Int( 3, 8 );
			}
		}
		
		return damage;
	}
	
	@Override
	public String desc() {
		return 
			"This blade seems to absorb light itself, its edges wreathed in shadow. " +
			"Forged from void-touched metal, it occasionally grants its wielder " +
			"brief concealment in darkness. The weapon whispers with otherworldly power.";
	}
}
