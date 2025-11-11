/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class ScytheOfSorrow extends MeleeWeapon {

	{
		name = "Scythe of Sorrow";
		image = ItemSpriteSheet.GLAIVE;
	}

	public ScytheOfSorrow() {
		super( 5, 1.0f, 1.1f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 30% chance for lifetap effect (heal wielder)
		if (Random.Int(10) < 3 && attacker instanceof Hero) {
			Hero hero = (Hero)attacker;
			int heal = damage / 3;
			hero.HP = Math.min(hero.HT, hero.HP + heal);
		}
	}

	@Override
	public String desc() {
		return
			"The Scythe of Sorrow is a weapon of dark legend, carried by the first Necromancer of Neriak. " +
			"Its blade is forged from shadowsteel and reaps not just flesh, but souls themselves. Each strike drains " +
			"the life force from enemies, sustaining the wielder with stolen vitality. The scythe moans softly when swung, " +
			"as if mourning each life it takes, hence its sorrowful name. Necromancers prize this weapon above all others, " +
			"for it embodies their philosophy: death feeds life, and life feeds death, in an eternal cycle. The handle " +
			"is wrapped in leather said to be tanned from the hide of a vampire lord.";
	}
}
