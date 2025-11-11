/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class TwinFangsOfTheSerpent extends MeleeWeapon {

	{
		name = "Twin Fangs of the Serpent";
		image = ItemSpriteSheet.DAGGER;
	}

	public TwinFangsOfTheSerpent() {
		super( 3, 1.0f, 1.5f );  // Fast attack speed
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 35% chance to poison (dual daggers = double strikes)
		if (Random.Int(10) < 35) {
			Buff.affect(defender, Poison.class).set(Random.Int(3, 7));
		}
		// 15% chance for bonus "off-hand" strike
		if (Random.Int(100) < 15) {
			int bonusDamage = Random.Int(min(), max()) / 2;
			defender.damage(bonusDamage, this);
		}
	}

	@Override
	public String desc() {
		return
			"These matched daggers were forged from the fangs of the great serpent Nagafen... or so the legend claims. " +
			"The truth is murkier, but their deadliness is not. Each blade curves like a serpent's fang and drips with " +
			"a mystical venom that never dries. Swashbucklers and Brigands favor these weapons for their speed and lethality. " +
			"Fighting with the Twin Fangs feels like dancing with death itself, as the wielder flows between strikes with " +
			"serpentine grace. Some say the daggers whisper to each other in Nagafen's ancient tongue.";
	}
}
