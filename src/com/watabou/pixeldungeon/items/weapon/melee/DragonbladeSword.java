/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.particles.FlameParticle;
import com.watabou.pixeldungeon.items.weapon.melee.MeleeWeapon;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class DragonbladeSword extends MeleeWeapon {

	{
		name = "dragonblade sword";
		image = ItemSpriteSheet.SWORD;
		
		tier = 2;
		STR = 12;
		MIN = 3;
		MAX = 15;
		
		unique = true;
	}
	
	@Override
	public int proc( Char attacker, Char defender, int damage ) {
		
		if (Random.Int( 6 ) == 0) {
			// Dragon fire proc
			GLog.w( "The dragonblade erupts in flames!" );
			defender.sprite.emitter().burst( FlameParticle.FACTORY, 5 );
			return damage + Random.Int( 4, 8 );
		}
		
		return damage;
	}
	
	@Override
	public String desc() {
		return 
			"This magnificent blade was forged in dragonfire and tempered with ancient magic. " +
			"The steel gleams with an inner flame, and occasionally erupts in draconic fury " +
			"when striking enemies. The blade grows stronger with its wielder.";
	}
}
