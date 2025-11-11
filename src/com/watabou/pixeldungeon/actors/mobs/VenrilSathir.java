/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.Badges;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Terror;
import com.watabou.pixeldungeon.actors.buffs.Weakness;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.items.potions.PotionOfExperience;
import com.watabou.pixeldungeon.items.rings.RingOfMight;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.watabou.pixeldungeon.items.wands.WandOfDisintegration;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.SkeletonSprite;
import com.watabou.utils.Random;

/**
 * Venril Sathir - Legendary Iksar lich raid boss
 * Ancient emperor transformed into an undead sorcerer
 */
public class VenrilSathir extends Mob {

	{
		name = "Venril Sathir";
		spriteClass = SkeletonSprite.class;  // TODO: Create VenrilSprite

		HP = HT = 450;  // Raid boss HP
		defenseSkill = 38;

		EXP = 120;
		maxLvl = 25;

		loot = new ScrollOfUpgrade();
		lootChance = 1f;
	}

	private int summonCooldown = 0;

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 25, 55 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 42;
	}

	@Override
	public int dr() {
		return 20;
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		// Venril's necromantic magic
		if (Random.Int( 3 ) == 0) {
			Buff.prolong( enemy, Weakness.class, Weakness.DURATION );
		}

		// Fear spell
		if (Random.Int( 5 ) == 0) {
			Buff.affect( enemy, Terror.class, Terror.DURATION ).object = id();
		}

		return damage;
	}

	@Override
	protected boolean act() {
		// Summon skeletal minions periodically
		if (summonCooldown <= 0 && HP < HT / 2) {
			summonCooldown = Random.IntRange(10, 15);

			for (int i : Level.NEIGHBOURS8) {
				int cell = pos + i;
				if (Level.passable[cell] && Char.findChar(cell) == null) {
					Skeleton minion = new Skeleton();
					minion.pos = cell;
					minion.state = minion.HUNTING;
					Dungeon.level.mobs.add(minion);
					minion.sprite.emitter().burst( com.watabou.pixeldungeon.effects.particles.ShadowParticle.CURSE, 5 );
					break;
				}
			}
		} else if (summonCooldown > 0) {
			summonCooldown--;
		}

		return super.act();
	}

	@Override
	public void die( Object cause ) {
		super.die( cause );

		// Legendary loot befitting an ancient emperor
		Dungeon.level.drop( new Gold( Random.IntRange( 1000, 2000 ) ), pos ).sprite.drop();

		// Epic wand
		if (Random.Int(2) == 0) {
			Dungeon.level.drop( new WandOfDisintegration(), pos ).sprite.drop();
		}

		// Ring of Might
		if (Random.Int(3) == 0) {
			Dungeon.level.drop( new RingOfMight(), pos ).sprite.drop();
		}

		// Many scrolls of upgrade
		for (int i = 0; i < Random.IntRange(3, 6); i++) {
			Dungeon.level.drop( new ScrollOfUpgrade(), pos ).sprite.drop();
		}

		// Guaranteed XP potion
		Dungeon.level.drop( new PotionOfExperience(), pos ).sprite.drop();

		Badges.validateBossSlain();
		Badges.validateRare(new VenrilSathir());
	}

	@Override
	public String description() {
		return
			"Venril Sathir was once the greatest emperor of the Iksar Empire, ruling with wisdom and might for over a century. " +
			"But his fear of death drove him to dark necromantic rituals, and he transformed himself into a lich to achieve " +
			"immortality. Now he is a skeletal horror wrapped in tattered imperial robes, his crown fused to his skull. " +
			"Venril possesses vast magical power, wielding necromancy, enchantment, and evocation with equal mastery. He rules " +
			"from his crypt deep within Karnor's Castle, served by legions of undead Iksar who were once his loyal subjects. " +
			"Venril can summon skeletal warriors at will, cast devastating spells, and drain the life force from his enemies. " +
			"His phylactery is hidden somewhere in the castle, making him extremely difficult to permanently destroy. He views " +
			"living creatures with cold contempt, seeing them as resources to be harvested for his dark experiments. His ultimate " +
			"goal is to rebuild the Iksar Empire as an undead kingdom that will rule Norrath for eternity. Defeating Venril Sathir " +
			"requires not just strength, but tactical brilliance and unwavering courage in the face of ancient evil.";
	}
}
