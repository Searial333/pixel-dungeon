/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.actors.buffs.Poison;
import com.watabou.pixeldungeon.actors.buffs.Chill;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Random;

/**
 * Chaos Blast - Warlock ability from EverQuest II
 * Unpredictable chaotic energy that has random effects
 */
public class ChaosBlast extends Ability {

	public ChaosBlast() {
		name = "Chaos Blast";
		description = "Unleash raw chaos energy with unpredictable results. The blast may burn, poison, freeze, " +
		              "or simply deal massive damage. Warlocks embrace the chaos, knowing great risk brings great reward.";
		manaCost = 55;
		staminaCost = 0;
		cooldown = 12f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Chaos Blast!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Chaos Blast!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 8) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Base chaotic damage - highly variable
		int baseDamage = Random.NormalIntRange(hero.mysticism / 2, hero.mysticism * 4);
		enemy.damage(baseDamage, this);

		// Random secondary effect
		int effect = Random.Int(6);
		switch (effect) {
			case 0:
				Buff.affect(enemy, Burning.class).reignite(enemy);
				GLog.p("Chaos engulfs %s in flames for %d damage!", enemy.name, baseDamage);
				break;
			case 1:
				Buff.affect(enemy, Poison.class).set(5);
				GLog.p("Chaos poisons %s for %d damage!", enemy.name, baseDamage);
				break;
			case 2:
				Buff.prolong(enemy, Chill.class, 5f);
				GLog.p("Chaos freezes %s for %d damage!", enemy.name, baseDamage);
				break;
			case 3:
				// Extra damage!
				int bonus = Random.Int(10, 30);
				enemy.damage(bonus, this);
				GLog.p("Pure chaos strikes %s for %d damage + %d bonus!", enemy.name, baseDamage, bonus);
				break;
			case 4:
				// AOE splash
				for (int i : Level.NEIGHBOURS8) {
					int cell = enemy.pos + i;
					Char ch = Char.findChar(cell);
					if (ch != null && ch != hero) {
						ch.damage(baseDamage / 2, this);
					}
				}
				GLog.p("Chaos explodes, hitting %s and nearby enemies for %d damage!", enemy.name, baseDamage);
				break;
			default:
				GLog.p("Chaotic energy strikes %s for %d damage!", enemy.name, baseDamage);
				break;
		}

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
