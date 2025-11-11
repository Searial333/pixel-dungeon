/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class SingingShortSword extends MeleeWeapon {

	{
		name = "Singing Short Sword";
		image = ItemSpriteSheet.SHORT_SWORD;
	}

	public SingingShortSword() {
		super( 3, 1.3f, 1.0f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// Bard's weapon - scales with Presence
		if (attacker instanceof Hero) {
			Hero hero = (Hero) attacker;
			if (Random.Int(5) == 0 && hero.presence > 10) {
				// Song empowers the strike
				int bonusDamage = hero.presence;
				defender.damage(bonusDamage, this);
			}
		}
	}

	@Override
	public String desc() {
		return
			"This enchanted short sword was crafted by the bards of Qeynos for their epic quest. The blade hums with " +
			"a constant, harmonious tone that changes pitch as it cuts through the air. When wielded by those with " +
			"strong presence and charisma, the sword's song grows louder, empowering strikes with magical resonance. " +
			"Troubadours and Dirges prize this weapon for its perfect balance and the way it seems to move in rhythm " +
			"with their battle songs. The sword's melody is said to inspire allies and unnerve enemies, though only " +
			"the wielder can hear its full beauty.";
	}
}
