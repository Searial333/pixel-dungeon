/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Backstab - Assassin/Rogue ability from EverQuest II
 * Devastating attack from behind that deals massive damage
 */
public class Backstab extends Ability {

	public Backstab() {
		name = "Backstab";
		description = "Strike from the shadows with a deadly backstab attack. When attacking an enemy from behind, " +
		              "deal massive critical damage. Assassins and Swashbucklers live for these perfect moments, " +
		              "when a single strike can fell even the mightiest foe.";
		manaCost = 0;
		staminaCost = 35;
		cooldown = 8f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for backstab!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || !Dungeon.level.adjacent(hero.pos, target)) {
			GLog.w("No adjacent target!");
			return false;
		}

		use(hero);

		// Calculate backstab damage based on Skill
		int baseDamage = Random.NormalIntRange(10, 20);
		int bonusDamage = hero.skill * 3;
		int totalDamage = baseDamage + bonusDamage;

		// Check if attacking from behind for extra damage
		boolean fromBehind = isAttackingFromBehind(hero.pos, enemy.pos);
		if (fromBehind) {
			totalDamage = (int)(totalDamage * 2.5f);
			GLog.p("Perfect backstab! Devastating critical hit for %d damage!", totalDamage);
		} else {
			GLog.i("Backstab from the front deals %d damage.", totalDamage);
		}

		// Deal damage
		enemy.damage(totalDamage, this);

		hero.trackMetric("melee", totalDamage);
		hero.trackMetric("stealth", fromBehind ? 5 : 1);

		hero.spend(1f);
		hero.busy();
		return true;
	}

	private boolean isAttackingFromBehind(int attackerPos, int defenderPos) {
		// Simplified check - could be enhanced with directional logic
		return Random.Int(3) == 0; // 33% chance to be from behind
	}
}
