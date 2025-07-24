/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.effects.particles.ShadowParticle;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class HealingLight extends Ability {
	
	{
		name = "Healing Light";
		description = "Channel divine energy to heal yourself and nearby allies. " +
					 "The amount healed increases with your level.";
		manaCost = 20;
		staminaCost = 0;
		cooldown = 8f;
		image = ItemSpriteSheet.POTION_HEALING;
	}
	
	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for healing light!");
			return false;
		}
		
		use(hero);
		
		// Heal the hero
		int healAmount = Random.Int(10, 15) + hero.lvl;
		hero.HP = Math.min(hero.HT, hero.HP + healAmount);
		hero.sprite.emitter().burst(Speck.factory(Speck.HEALING), 5);
		
		GLog.p("Divine light restores your vitality!");
		
		// Heal nearby allies (if any)
		for (int i : Level.NEIGHBOURS8) {
			int cell = hero.pos + i;
			Char ch = Actor.findChar(cell);
			if (ch != null && !ch.hostile && ch != hero) {
				int allyHeal = healAmount / 2;
				ch.HP = Math.min(ch.HT, ch.HP + allyHeal);
				ch.sprite.emitter().burst(Speck.factory(Speck.HEALING), 3);
				GLog.i("Your healing light aids your ally!");
			}
		}
		
		// Track action for class progression
		hero.metrics.track("heal", 1);
		
		return true;
	}
}
