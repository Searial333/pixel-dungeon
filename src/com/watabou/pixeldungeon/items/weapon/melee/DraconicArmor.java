/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.armor.Armor;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class DraconicArmor extends Armor {

	{
		name = "draconic armor";
		image = ItemSpriteSheet.ARMOR_SCALE;
		
		STR = 8;
		DR = 2;
		
		tier = 1;
	}
	
	@Override
	public boolean doEquip( Hero hero ) {
		if (super.doEquip( hero )) {
			GLog.p( "The draconic armor pulses with ancient power!" );
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int proc( Char attacker, Char defender, int damage ) {
		
		if (Random.Int( 4 ) == 0) {
			// Dragon scale deflection
			GLog.w( "The dragon scales deflect some of the attack!" );
			damage = Math.max( damage - Random.Int( 3 ), 0 );
		}
		
		return damage;
	}
	
	@Override
	public String desc() {
		return 
			"This ancient armor is crafted from the scales of dragons long past. " +
			"It provides exceptional protection and occasionally deflects incoming attacks. " +
			"The scales seem to shimmer with their own inner fire.";
	}
}
