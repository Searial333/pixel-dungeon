/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class SoulreapersScythe extends MeleeWeapon {

	{
		name = "Soulreaper's Scythe";
		image = ItemSpriteSheet.GLAIVE;
	}

	public SoulreapersScythe() {
		super( 5, 0.9f, 1.2f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 40% chance for lifetap
		if (Random.Int(10) < 4 && attacker instanceof Hero) {
			Hero hero = (Hero)attacker;
			int heal = damage / 3;
			hero.HP = Math.min(hero.HT, hero.HP + heal);
		}
	}

	@Override
	public String desc() {
		return
			"The Soulreaper's Scythe is a weapon of dark legend, said to harvest not just lives but souls themselves. " +
			"Its curved blade is forged from shadowsteel mined in the depths of Nektulos Forest, and the metal seems " +
			"to absorb light rather than reflect it. Necromancers of the Bloodline covenant wielded these scythes in " +
			"the Age of Turmoil, using them to fuel their dark rituals. Each strike drains a portion of the victim's " +
			"life force, transferring it to the wielder. The handle is wrapped in leather from some unidentifiable " +
			"creature, and whispers emanate from the blade when swung. Some say the weapon is semi-sentient, hungry " +
			"for souls to feed its endless appetite. Only those who have embraced death magic can truly master this " +
			"fearsome implement.";
	}
}
