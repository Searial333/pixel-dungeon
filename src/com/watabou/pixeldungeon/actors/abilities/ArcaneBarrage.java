/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Arcane Barrage - Sorcerer ability from EverQuest II
 * Rapid-fire magic missiles
 */
public class ArcaneBarrage extends Ability {

	public ArcaneBarrage() {
		name = "Arcane Barrage";
		description = "Fire multiple rapid arcane missiles at your target. Each missile deals moderate damage, " +
		              "but together they create devastating burst damage. Sorcerers use this for reliable DPS.";
		manaCost = 45;
		staminaCost = 0;
		cooldown = 8f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Arcane Barrage!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Arcane Barrage!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 8) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Fire 3-5 missiles
		int missiles = Random.Int(3, 6);
		int totalDamage = 0;

		for (int i = 0; i < missiles; i++) {
			int damage = Random.NormalIntRange(hero.mysticism / 2, hero.mysticism);
			enemy.damage(damage, this);
			totalDamage += damage;
		}

		GLog.p("You fire %d arcane missiles at %s for %d total damage!", missiles, enemy.name, totalDamage);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
