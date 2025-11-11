/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class Ghoulbane extends MeleeWeapon {

	{
		name = "Ghoulbane";
		image = ItemSpriteSheet.MACE;
	}

	public Ghoulbane() {
		super( 4, 1.1f, 1.0f );
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 40% chance for bonus holy damage (especially effective vs undead)
		if (Random.Int(10) < 4) {
			int bonusDamage = Random.Int(3, 10);
			// TODO: Could add check if enemy is undead for double bonus
			defender.damage(bonusDamage, this);
		}
	}

	@Override
	public String desc() {
		return
			"Ghoulbane is the sacred weapon of the Temple of Life in Qeynos, blessed by Rodcet Nife himself. " +
			"This silvered mace glows with soft divine light and was specifically created to combat the undead scourge " +
			"that plagues Antonica. Clerics and Templars wield this mace with righteous fury, smiting the undead and " +
			"purifying corrupted ground. The weapon's name comes from its legendary wielder, Priest Ghoulbane, who single-handedly " +
			"cleansed the catacombs beneath Qeynos of a thousand undead abominations.";
	}
}
