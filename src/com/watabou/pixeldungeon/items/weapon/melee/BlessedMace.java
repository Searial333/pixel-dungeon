/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.effects.particles.ShadowParticle;
import com.watabou.pixeldungeon.items.weapon.melee.MeleeWeapon;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class BlessedMace extends MeleeWeapon {

	{
		name = "blessed mace";
		image = ItemSpriteSheet.MACE;
		
		tier = 2;
		STR = 10;
		MIN = 3;
		MAX = 12;
		
		unique = true;
	}
	
	@Override
	public int proc( Char attacker, Char defender, int damage ) {
		
		if (attacker instanceof Hero && defender instanceof Mob) {
			
			if (Random.Int( 5 ) == 0) {
				// Divine smite against evil
				GLog.w( "Divine light purges evil!" );
				defender.sprite.emitter().burst( ShadowParticle.UP, 5 );
				
				// Extra damage vs undead and demons
				String defenderName = defender.getClass().getSimpleName().toLowerCase();
				if (defenderName.contains("undead") || defenderName.contains("demon") || 
				    defenderName.contains("wraith") || defenderName.contains("skeleton")) {
					return damage + Random.Int( 6, 12 );
				} else {
					return damage + Random.Int( 2, 6 );
				}
			}
		}
		
		return damage;
	}
	
	@Override
	public String desc() {
		return 
			"This sacred mace has been blessed by divine powers. Its head gleams with " +
			"holy light, and it deals extra damage to undead and demonic creatures. " +
			"The weapon pulses with righteous energy in the presence of evil.";
	}
}
