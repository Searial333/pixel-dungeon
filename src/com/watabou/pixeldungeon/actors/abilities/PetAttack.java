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
 * Pet Attack - Ranger ability from EverQuest II
 * Command your animal companion to attack
 */
public class PetAttack extends Ability {

	public PetAttack() {
		name = "Pet Attack";
		description = "Command your animal companion to attack a target with ferocious intensity. " +
		              "Rangers bond with wild beasts, fighting alongside them as trusted partners.";
		manaCost = 0;
		staminaCost = 25;
		cooldown = 8f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough stamina for Pet Attack!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Pet Attack!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 8) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// TODO: Check if hero has a pet companion
		// For now, direct damage representing pet attack
		int damage = Random.NormalIntRange(hero.skill, hero.skill * 2);
		enemy.damage(damage, this);

		GLog.p("Your companion attacks %s for %d damage!", enemy.name, damage);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
