/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Stun;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Flying Kick - Monk ability from EverQuest II
 * Leap through the air and strike with a powerful kick
 */
public class FlyingKick extends Ability {

	public FlyingKick() {
		name = "Flying Kick";
		description = "Launch yourself through the air with a devastating kick. Damages and stuns the target. " +
		              "Monks use this to close distance and disrupt enemy attacks.";
		manaCost = 0;
		staminaCost = 40;
		cooldown = 10f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for Flying Kick!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Flying Kick!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 3) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Deal damage based on skill stat (monks are dexterous)
		int damage = Random.NormalIntRange(hero.skill * 2, hero.skill * 3);
		enemy.damage(damage, this);

		// 50% chance to stun
		if (Random.Int(2) == 0) {
			Buff.prolong(enemy, Stun.class, 2f);
		}

		GLog.p("You launch a flying kick at %s for %d damage!", enemy.name, damage);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
