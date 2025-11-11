/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Cripple;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.CellEmitter;
import com.watabou.pixeldungeon.effects.particles.SnowParticle;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.mechanics.Ballistica;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Ice Comet - Wizard ability from EverQuest II
 * Devastating ice spell that damages and slows enemies
 */
public class IceComet extends Ability {

	public IceComet() {
		name = "Ice Comet";
		description = "Summon a comet of pure ice to crash down upon your enemies, dealing massive cold damage " +
		              "and slowing their movement. Wizards who master this spell can turn the tide of battle, " +
		              "freezing entire groups of enemies in their tracks.";
		manaCost = 60;
		staminaCost = 0;
		cooldown = 12f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for ice comet!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || !Dungeon.level.fieldOfView[target]) {
			GLog.w("No valid target!");
			return false;
		}

		use(hero);

		// Calculate damage based on Mysticism
		int damage = Random.NormalIntRange(15, 25) + (hero.mysticism * 2);

		// Deal damage
		enemy.damage(damage, this);

		// Apply slow effect
		Buff.prolong(enemy, Cripple.class, Cripple.DURATION * 2);

		// Visual effect
		CellEmitter.get(target).burst(SnowParticle.FACTORY, 10);

		GLog.p("A comet of ice crashes down, dealing %d damage!", damage);
		hero.trackMetric("spell", damage);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
