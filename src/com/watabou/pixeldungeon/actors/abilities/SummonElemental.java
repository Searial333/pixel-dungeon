/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Elemental;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Summon Elemental - Summoner ability from EverQuest II
 * Call forth an elemental to fight alongside you
 */
public class SummonElemental extends Ability {

	public SummonElemental() {
		name = "Summon Elemental";
		description = "Call forth a powerful elemental being to fight by your side. The elemental will attack your " +
		              "enemies until dismissed or destroyed. Summoners command these creatures with absolute authority.";
		manaCost = 80;
		staminaCost = 0;
		cooldown = 60f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Summon Elemental!");
			return false;
		}

		if (!Dungeon.level.passable[target]) {
			GLog.w("Cannot summon there!");
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

		// Check if hero already has a summon
		// TODO: Track summons properly
		int summonCount = 0;
		for (com.watabou.pixeldungeon.actors.mobs.Mob mob : Dungeon.level.mobs.toArray(new com.watabou.pixeldungeon.actors.mobs.Mob[0])) {
			if (mob instanceof Elemental) {
				summonCount++;
			}
		}

		if (summonCount >= 1) {
			GLog.w("You can only have one elemental at a time!");
			return false;
		}

		use(hero);

		// Summon the elemental
		Elemental elemental = new Elemental();
		elemental.pos = target;
		elemental.state = elemental.HUNTING;
		Dungeon.level.mobs.add(elemental);

		GLog.p("An elemental materializes to serve you!");

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
