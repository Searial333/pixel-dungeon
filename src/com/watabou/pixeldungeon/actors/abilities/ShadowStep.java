/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.buffs.Invisibility;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.particles.ShadowParticle;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;

public class ShadowStep extends Ability {
	
	{
		name = "Shadow Step";
		description = "Instantly teleport to a nearby location through the shadows, " +
					 "gaining brief invisibility upon arrival.";
		manaCost = 0;
		staminaCost = 25;
		cooldown = 12f;
		image = ItemSpriteSheet.RING;
	}
	
	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for shadow step!");
			return false;
		}
		
		if (!Level.fieldOfView[target]) {
			GLog.w("You cannot see the target location!");
			return false;
		}
		
		if (!Level.passable[target] || Actor.findChar(target) != null) {
			GLog.w("You cannot teleport there!");
			return false;
		}
		
		// Check range (max 5 tiles)
		int distance = Level.distance(hero.pos, target);
		if (distance > 5) {
			GLog.w("That location is too far away!");
			return false;
		}
		
		use(hero);
		
		// Shadow effect at departure
		hero.sprite.emitter().burst(ShadowParticle.UP, 8);
		
		// Move hero
		int oldPos = hero.pos;
		hero.pos = target;
		hero.sprite.move(oldPos, target);
		
		// Shadow effect at arrival
		hero.sprite.emitter().burst(ShadowParticle.CURSE, 8);
		
		// Grant brief invisibility
		Invisibility.affect(hero, 3f);
		
		Sample.INSTANCE.play(Assets.SND_TELEPORT);
		GLog.p("You step through the shadows!");
		
		// Track action for class progression
		hero.metrics.track("stealth", 1);
		
		return true;
	}
}
