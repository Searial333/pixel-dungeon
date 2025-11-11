/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.rings;

import com.watabou.pixeldungeon.actors.buffs.Poison;

public class RingOfTheSerpent extends Ring {

	{
		name = "Ring of the Serpent";
	}

	@Override
	protected RingBuff buff( ) {
		return new PoisonImmunity();
	}

	@Override
	public String desc() {
		return isKnown() ?
			"This ancient ring bears the symbol of a coiled serpent, an artifact from the iksar empire of old Sebilis. " +
			"The iksar, being natural reptiles, had great resistance to poisons and venoms. This ring grants its wearer " +
			"the same immunity, allowing them to walk through toxic gases and survive the deadliest poisons unharmed. " +
			"Necromancers and scouts prize this ring for the freedom it grants when facing venomous creatures and " +
			"toxic environments. The ring feels warm against the skin, as if the serpent's blood still flows through it." :
			this.typicalRing();
	}

	public class PoisonImmunity extends RingBuff {
		// Implementation would provide poison immunity
		// This is a framework for the ring's effect
	}
}
