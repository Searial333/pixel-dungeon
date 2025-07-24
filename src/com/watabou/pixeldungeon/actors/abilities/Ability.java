/*
 * N3v3rQu3st
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.actors.abilities;

import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public abstract class Ability {
	
	protected String name;
	protected String description;
	protected int manaCost;
	protected int staminaCost;
	protected float cooldown;
	protected int image = ItemSpriteSheet.SOMETHING;
	
	public abstract boolean execute(Hero hero, int target);
	
	public boolean canUse(Hero hero) {
		return hero.manaPool >= manaCost && 
		       hero.staminaPool >= staminaCost;
	}
	
	public void use(Hero hero) {
		hero.manaPool -= manaCost;
		hero.staminaPool -= staminaCost;
	}
	
	public String name() {
		return name;
	}
	
	public String desc() {
		return description;
	}
	
	public int manaCost() {
		return manaCost;
	}
	
	public int staminaCost() {
		return staminaCost;
	}
	
	public float cooldown() {
		return cooldown;
	}
	
	public int image() {
		return image;
	}
}
