/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Group Heal - Cleric ability from EverQuest II
 * Heal all allies in a large radius
 */
public class GroupHeal extends Ability {

	public GroupHeal() {
		name = "Group Heal";
		description = "Channel divine energy to heal yourself and all nearby allies. The signature ability of Clerics, " +
		              "allowing them to keep entire parties alive through sustained healing.";
		manaCost = 60;
		staminaCost = 0;
		cooldown = 15f;
		image = ItemSpriteSheet.SOMETHING;
	}

	@Override
	public boolean execute(Hero hero, int target) {
		if (!canUse(hero)) {
			GLog.w("Not enough mana for Group Heal!");
			return false;
		}

		use(hero);

		// Heal amount based on mysticism and presence
		int healAmount = (hero.mysticism + hero.presence) * 2;

		// Heal self
		int healed = Math.min(hero.HT - hero.HP, healAmount);
		hero.HP += healed;

		int totalHealed = healed;
		int alliesHealed = 1;

		// Heal all allies in range (in multiplayer, would heal party members)
		// For now, just heal self and count potential allies
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			// TODO: In full implementation, check if mob is an ally/companion
			if (Level.distance(hero.pos, mob.pos) <= 5 && mob.isAlive()) {
				// Would heal ally here
			}
		}

		GLog.p("Divine light washes over the area, healing %d allies for %d total HP!", alliesHealed, totalHealed);

		hero.spend(1f);
		hero.busy();
		return true;
	}
}
