/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.mobs;

import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

public abstract class Companion extends Mob {
	
	protected Hero owner;
	protected int companionLevel = 1;
	protected int experience = 0;
	protected int maxExperience = 10;
	
	{
		hostile = false;
		ally = true;
		intelligent = true;
		state = HUNTING;
	}
	
	public void setOwner(Hero hero) {
		this.owner = hero;
	}
	
	@Override
	protected boolean act() {
		if (owner == null || !owner.isAlive()) {
			die(null);
			return true;
		}
		
		// Stay close to owner
		if (Level.distance(pos, owner.pos) > 5) {
			pos = owner.pos;
			sprite.place(pos);
		}
		
		return super.act();
	}
	
	@Override
	protected Char chooseEnemy() {
		// Target owner's enemy or nearest hostile
		if (owner.enemy != null && Level.fieldOfView[owner.enemy.pos]) {
			return owner.enemy;
		}
		return super.chooseEnemy();
	}
	
	public void gainExperience(int exp) {
		experience += exp;
		if (experience >= maxExperience) {
			levelUp();
		}
	}
	
	protected void levelUp() {
		companionLevel++;
		experience = 0;
		maxExperience = companionLevel * 15;
		
		// Improve stats
		HT += 5;
		HP = HT;
		attackSkill++;
		defenseSkill++;
		
		GLog.p("Your companion grows stronger!");
		
		// Apply level-specific benefits
		onLevelUp();
	}
	
	protected abstract void onLevelUp();
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put("companionLevel", companionLevel);
		bundle.put("experience", experience);
		bundle.put("maxExperience", maxExperience);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		companionLevel = bundle.getInt("companionLevel");
		experience = bundle.getInt("experience");
		maxExperience = bundle.getInt("maxExperience");
	}
}
