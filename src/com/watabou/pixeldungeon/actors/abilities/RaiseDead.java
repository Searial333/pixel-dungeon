/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Skeleton;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Raise Dead - Necromancer ability from EverQuest II
 * Animate a skeleton to fight for you
 */
public class RaiseDead extends Ability {

	public RaiseDead() {
		name = "Raise Dead";
		description = "Animate a skeletal warrior from the bones of the fallen. Your undead minion will fight " +
		              "until destroyed. Necromancers command legions of undead through dark magic.";
		manaCost = 60;
		staminaCost = 0;
		cooldown = 45f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Raise Dead!");
			return false;
		}

		if (!Dungeon.level.passable[target]) {
			GLog.w("Cannot raise undead there!");
			return false;
		}

		if (Char.findChar(target) != null) {
			GLog.w("Space is occupied!");
			return false;
		}

		if (Level.distance(hero.pos, target) > 3) {
			GLog.w("Too far away!");
			return false;
		}

		use(hero);

		// Raise the skeleton
		Skeleton skeleton = new Skeleton();
		skeleton.pos = target;
		skeleton.state = skeleton.HUNTING;
		Dungeon.level.mobs.add(skeleton);

		GLog.n("Bones rise from the ground, animated by your dark will!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
