/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class SpellTome extends Item {

	{
		name = "spell tome";
		image = ItemSpriteSheet.TOME;
		
		stackable = false;
		unique = true;
	}
	
	@Override
	public boolean doPickUp( Hero hero ) {
		if (super.doPickUp( hero )) {
			GLog.p( "The spell tome pulses with arcane knowledge!" );
			
			// Grant additional mana
			hero.maxManaPool += 15;
			hero.manaPool = hero.maxManaPool;
			
			// Teach basic spells
			GLog.i( "You learn the fundamentals of arcane magic!" );
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String desc() {
		return 
			"This ancient tome contains the fundamental principles of arcane magic. " +
			"Its pages are filled with glowing runes and mystical diagrams that " +
			"enhance the reader's magical potential and understanding.";
	}
	
	@Override
	public int price() {
		return 100;
	}
}
