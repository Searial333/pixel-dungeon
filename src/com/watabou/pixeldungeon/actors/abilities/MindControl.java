/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Charm;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Mind Control - Coercer ability from EverQuest II
 * Dominate an enemy's mind, turning them into your ally
 */
public class MindControl extends Ability {

	public MindControl() {
		name = "Mind Control";
		description = "Seize control of your enemy's mind, forcing them to fight for you. The dominated creature " +
		              "will attack its former allies until the spell breaks. Coercers are masters of mental domination.";
		manaCost = 70;
		staminaCost = 0;
		cooldown = 30f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Mind Control!");
			return false;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || !Dungeon.level.fieldOfView[target]) {
			GLog.w("Invalid target for Mind Control!");
			return false;
		}

		if (Level.distance(hero.pos, enemy.pos) > 8) {
			GLog.w("Target is too far away!");
			return false;
		}

		use(hero);

		// Charm the enemy for extended duration based on Presence
		float duration = Charm.durationFactor(enemy) * (5f + hero.presence);
		Buff.affect(enemy, Charm.class, duration).object = hero.id();

		GLog.p("You seize control of %s's mind!", enemy.name);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
