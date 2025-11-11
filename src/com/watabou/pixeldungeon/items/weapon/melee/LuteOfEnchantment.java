/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Charm;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class LuteOfEnchantment extends MeleeWeapon {

	{
		name = "Lute of Enchantment";
		image = ItemSpriteSheet.QUARTERSTAFF;
	}

	public LuteOfEnchantment() {
		super( 2, 1.0f, 1.0f );  // Weak physical damage, bards aren't fighters
	}

	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		// 20% chance to charm the enemy with music
		if (Random.Int(5) == 0) {
			Buff.affect(defender, Charm.class, Charm.durationFactor(defender) * 3f).object = attacker.id();
		}
		// Small mana regen when attacking (music empowers the bard)
		if (attacker instanceof Hero) {
			Hero hero = (Hero)attacker;
			if (hero.manaPool < hero.maxManaPool) {
				hero.manaPool = Math.min(hero.maxManaPool, hero.manaPool + 2);
			}
		}
	}

	@Override
	public String desc() {
		return
			"The Lute of Enchantment is the masterwork of the legendary bard Bristlebane (though whether it's actually related " +
			"to the deity is hotly debated). Crafted from moonwood and strung with silver strings, this lute produces music " +
			"so beautiful it can charm even the most hostile creatures. While not primarily a weapon, bards have been known " +
			"to use it as a club in desperate situations, and its magic still works through percussive strikes. " +
			"Troubadours and Dirges seek this instrument above all others, as it amplifies their songs and harmonizes with " +
			"their magic. The lute plays itself softly when left alone, filling the air with haunting melodies.";
	}
}
