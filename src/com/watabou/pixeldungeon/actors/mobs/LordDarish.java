/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.blobs.ToxicGas;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.watabou.pixeldungeon.items.weapon.enchantments.Death;
import com.watabou.pixeldungeon.sprites.SkeletonSprite;
import com.watabou.utils.Random;

/**
 * Lord Darish - Named undead knight from the Commonlands
 * A fallen paladin now serving dark powers
 */
public class LordDarish extends UndeadKnight {

	{
		name = "Lord Darish the Fallen";
		spriteClass = SkeletonSprite.class;

		HP = HT = 50;
		defenseSkill = 15;

		EXP = 12;
		maxLvl = 18;

		loot = new ScrollOfUpgrade();
		lootChance = 0.4f;

		immunities.add( Death.class );
		immunities.add( Poison.class );
		immunities.add( ToxicGas.class );
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 10, 18 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 22;
	}

	@Override
	public int dr() {
		return 12;
	}

	@Override
	public String description() {
		return
			"Lord Darish was once a noble paladin of Mithaniel Marr who fell to darkness during the Age of Turmoil. " +
			"Betrayed by those he trusted, his faith shattered, and he turned to Innoruuk in his despair. Now an undead " +
			"knight bound to eternal service, Darish patrols the Commonlands as a dark champion, his blackened armor " +
			"still bearing the tarnished symbols of his former glory. His greatsword, once blessed by holy light, now " +
			"drips with necrotic energy. Darish retains all his combat skills from life but uses them in service of evil. " +
			"He commands lesser undead and can drain the life force of his enemies. Those who face him report hearing " +
			"whispers of regret beneath his battle cries, suggesting a fragment of his former self still suffers within.";
	}
}
