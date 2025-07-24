/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

public class DefensiveStance extends Ability {
	
	{
		name = "Defensive Stance";
		description = "Assume a defensive posture that greatly reduces incoming damage " +
					 "but limits movement and attack speed.";
		manaCost = 0;
		staminaCost = 20;
		cooldown = 10f;
		image = ItemSpriteSheet.ARMOR_PLATE;
	}
	
	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for defensive stance!");
			return false;
		}
		
		use(hero);
		
		// Apply defensive stance buff
		Buff.affect(hero, DefensiveStanceBuff.class, 20f);
		
		hero.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 6);
		GLog.p("You assume a defensive stance!");
		
		// Track action for class progression
		hero.metrics.track("tank", 1);
		
		return true;
	}
	
	public static class DefensiveStanceBuff extends Buff {
		
		@Override
		public boolean act() {
			spend(TICK);
			return true;
		}
		
		@Override
		public String toString() {
			return "Defensive Stance";
		}
		
		@Override
		public String desc() {
			return "You are in a defensive stance, taking reduced damage but with limited mobility.";
		}
	}
}
