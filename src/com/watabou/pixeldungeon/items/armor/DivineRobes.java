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

public class DivineRobes extends Armor {

	{
		name = "divine robes";
		image = ItemSpriteSheet.ARMOR_ROBE;
		
		STR = 6;
		DR = 1;
		
		tier = 1;
	}
	
	@Override
	public boolean doEquip( Hero hero ) {
		if (super.doEquip( hero )) {
			GLog.p( "The divine robes shimmer with holy light!" );
			// Enhance mana regeneration
			if (hero.maxManaPool > 0) {
				hero.manaPool = Math.min(hero.maxManaPool, hero.manaPool + 10);
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int proc( Char attacker, Char defender, int damage ) {
		
		if (Random.Int( 5 ) == 0) {
			// Divine protection
			GLog.w( "Divine light shields you from harm!" );
			damage = Math.max( damage - Random.Int( 2, 5 ), 0 );
			
			// Small chance to heal
			if (Random.Int( 3 ) == 0 && defender instanceof Hero) {
				Hero hero = (Hero)defender;
				hero.HP = Math.min(hero.HT, hero.HP + Random.Int(1, 3));
				GLog.p( "The robes mend your wounds!" );
			}
		}
		
		return damage;
	}
	
	@Override
	public String desc() {
		return 
			"These flowing robes are woven with threads of pure light. They provide " +
			"modest protection while enhancing the wearer's connection to divine energy. " +
			"The fabric seems to glow with its own inner radiance.";
	}
}
