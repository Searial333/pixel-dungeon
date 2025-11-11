/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Provoke - Guardian/Tank ability from EverQuest II
 * Forces nearby enemies to attack the fighter, drawing aggro
 */
public class Provoke extends Ability {

	public Provoke() {
		name = "Provoke";
		description = "A powerful taunt that forces all nearby enemies to focus their attacks on you. " +
		              "Essential for Guardians and tanks to protect their allies. Costs stamina.";
		manaCost = 0;
		staminaCost = 30;
		cooldown = 10f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina to provoke!");
			return false;
		}

		use(hero);

		int taunted = 0;
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (Level.distance(hero.pos, mob.pos) <= 8 && Dungeon.level.fieldOfView[mob.pos]) {
				mob.aggro(hero);
				taunted++;
			}
		}

		if (taunted > 0) {
			GLog.p("You provoke %d %s to attack you!", taunted, taunted == 1 ? "enemy" : "enemies");
			hero.trackMetric("tank", taunted);
		} else {
			GLog.i("No enemies nearby to provoke.");
		}

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
