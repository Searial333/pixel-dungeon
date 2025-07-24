/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.MagicMissile;
import com.watabou.pixeldungeon.effects.particles.SparkParticle;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.mechanics.Ballistics;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class ArcaneBlast extends Ability {
	
	{
		name = "Arcane Blast";
		description = "Fire a bolt of pure magical energy at a target, dealing " +
					 "moderate damage with a chance to chain to nearby enemies.";
		manaCost = 15;
		staminaCost = 0;
		cooldown = 3f;
		image = ItemSpriteSheet.WAND_MAGIC_MISSILE;
	}
	
	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for arcane blast!");
			return false;
		}
		
		if (!Level.fieldOfView[target]) {
			GLog.w("You cannot see the target!");
			return false;
		}
		
		use(hero);
		
		// Calculate trajectory
		Ballistics.cast(hero.pos, target, false, true);
		final int cell = Ballistics.trace[Ballistics.distance];
		
		hero.sprite.zap(cell);
		
		MagicMissile.blueLight(hero.sprite.parent, hero.pos, cell, new Callback() {
			@Override
			public void call() {
				onZap(cell);
			}
		});
		
		// Track action for class progression
		hero.metrics.track("spell", 1);
		
		return true;
	}
	
	private void onZap(int cell) {
		Char ch = Actor.findChar(cell);
		if (ch != null) {
			int damage = Random.Int(8, 16);
			ch.damage(damage, this);
			ch.sprite.emitter().burst(SparkParticle.FACTORY, 5);
			
			// Chain to nearby enemies
			if (Random.Int(3) == 0) {
				chainToNearby(ch, damage / 2);
			}
		}
	}
	
	private void chainToNearby(Char origin, int damage) {
		for (int i : Level.NEIGHBOURS8) {
			int cell = origin.pos + i;
			Char ch = Actor.findChar(cell);
			if (ch != null && ch != origin && ch.hostile) {
				ch.damage(damage, this);
				ch.sprite.emitter().burst(SparkParticle.FACTORY, 3);
				GLog.i("The arcane energy chains to another target!");
				break; // Only chain to one target
			}
		}
	}
}
