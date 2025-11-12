/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.rings;

public class RingOfTheShadowKnight extends Ring {

	{
		name = "Ring of the Shadow Knight";
	}

	@Override
	protected RingBuff buff( ) {
		return new DarkEmpowerment();
	}

	@Override
	public String desc() {
		return isKnown() ?
			"This obsidian ring is set with a gem that seems to contain captured darkness. The first Shadow Knight order " +
			"forged these rings to mark their members and amplify their dark powers. The band is inscribed with Tier'Dal " +
			"runes that translate to 'Power Through Darkness.' Wearing this ring enhances lifetap abilities and strengthens " +
			"the connection between the wearer and Innoruuk. Dark wisps occasionally spiral around the ring's surface, and " +
			"the gem pulses with a dim red glow when dark magic is nearby. Those who wear it report feeling a cold presence " +
			"at their back, as if someone—or something—is always watching. The ring grows warmer when the wearer inflicts " +
			"pain or suffering, feeding on negative energy. Evil-aligned clerics and paladins avoid this ring as it corrupts " +
			"holy magic, but Shadow Knights treasure it above almost all other relics." :
			this.typicalRing();
	}

	public class DarkEmpowerment extends RingBuff {
		// Enhances dark magic and lifetap effects
	}
}
