/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Weakness;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Song of Death - Dirge ability from EverQuest II
 * Haunting melody that weakens all enemies
 */
public class SongOfDeath extends Ability {

	public SongOfDeath() {
		name = "Song of Death";
		description = "Sing a haunting dirge that weakens all enemies within earshot, reducing their damage and armor. " +
		              "Dirges use discordant music to sap the strength of their foes.";
		manaCost = 50;
		staminaCost = 0;
		cooldown = 25f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Song of Death!");
			return false;
		}

		use(hero);

		int enemiesAffected = 0;

		// Weaken all enemies in range
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (Level.distance(hero.pos, mob.pos) <= 6) {
				float duration = Weakness.DURATION + (hero.presence * 2);
				Buff.prolong(mob, Weakness.class, duration);
				enemiesAffected++;
			}
		}

		if (enemiesAffected > 0) {
			GLog.p("Your song of death weakens %d %s!",
			       enemiesAffected, enemiesAffected == 1 ? "enemy" : "enemies");
		} else {
			GLog.i("Your song finds no enemies to curse.");
		}

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
