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
 * Shield Bash - Guardian ability from EverQuest II
 * Powerful shield strike that stuns the target and deals damage
 */
public class ShieldBash extends Ability {

	public ShieldBash() {
		name = "Shield Bash";
		description = "Strike your enemy with your shield, dealing damage and stunning them. " +
		              "A Guardian's signature ability that interrupts enemy attacks and creates openings for allies.";
		manaCost = 0;
		staminaCost = 35;
		cooldown = 12f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for Shield Bash!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Shield Bash!");
			return false;
		}

		if (!Level.adjacent(hero.pos, enemy.pos)) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Deal damage based on durability stat
		int damage = Random.NormalIntRange(hero.durability / 2, hero.durability);
		enemy.damage(damage, this);

		// Stun the target
		Buff.prolong(enemy, Stun.class, 3f);

		GLog.p("You bash %s with your shield for %d damage!", enemy.name, damage);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
