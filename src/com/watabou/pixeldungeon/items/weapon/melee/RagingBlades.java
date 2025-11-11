/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class RagingBlades extends MeleeWeapon {

	{
		name = "Raging Blades";
		image = ItemSpriteSheet.SWORD;
	}

	public RagingBlades() {
		super( 5, 1.3f, 0.9f );
	}

	@Override
	public int damageRoll(Char owner) {
		// More variable damage - high risk, high reward
		int damage = super.damageRoll(owner);
		// Chance for bonus damage (berserker rage)
		if (Random.Int(4) == 0) {
			damage = (int)(damage * 1.5f);
		}
		return damage;
	}

	@Override
	public String desc() {
		return
			"These paired blades are the signature weapons of berserkers who have mastered the art of rage. " +
			"Forged in the fires of battle and quenched in the blood of enemies, the Raging Blades are twin " +
			"swords that feed on combat fury. As the berserker's rage grows, so does the power of these weapons. " +
			"They strike with unpredictable ferocity - sometimes dealing devastating blows that can cleave through " +
			"armor and bone. Berserkers who wield these blades enter a trance-like state where pain means nothing " +
			"and only carnage matters. The blades are said to whisper encouragements of violence to their wielder, " +
			"urging them to greater acts of destruction. Only those who can control their rage without being " +
			"consumed by it should attempt to master these weapons.";
	}
}
