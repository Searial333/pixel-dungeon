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
 * Harm Touch - Shadowknight signature ability from EverQuest II
 * Deal massive dark damage with a single touch
 */
public class HarmTouch extends Ability {

	public HarmTouch() {
		name = "Harm Touch";
		description = "Channel Innoruuk's dark power through your hand to inflict devastating damage with a single touch. " +
		              "The ultimate shadowknight ability, usable once per day.";
		manaCost = 50;
		staminaCost = 0;
		cooldown = 600f;  // 10 minute cooldown - once per dungeon run
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Harm Touch!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Harm Touch!");
			return false;
		}

		if (!Level.adjacent(hero.pos, enemy.pos)) {
			GLog.w("You must be adjacent to use Harm Touch!");
			return false;
		}

		use(hero);

		// Massive damage - scales with both mysticism and durability
		int damage = Random.NormalIntRange(
			(hero.mysticism + hero.durability) * 3,
			(hero.mysticism + hero.durability) * 5
		);
		enemy.damage(damage, this);

		// Heal for 25% of damage dealt
		int heal = damage / 4;
		hero.HP = Math.min(hero.HT, hero.HP + heal);

		GLog.n("Dark energy flows through your hand! You deal %d damage and heal %d HP!", damage, heal);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
