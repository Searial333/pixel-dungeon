/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.rings;

public class RingOfVox extends Ring {

	{
		name = "Ring of Vox's Frost";
	}

	@Override
	protected RingBuff buff( ) {
		return new FrostResistance();
	}

	@Override
	public String desc() {
		return isKnown() ?
			"Carved from a single piece of eternal ice from Lady Vox's lair, this ring is perpetually cold to the touch. " +
			"The band is crystalline blue and seems to contain swirling snowflakes within its depths. Those who wear this " +
			"ring gain immunity to cold and ice magic, protected by the ancient white dragon's power. The ring was allegedly " +
			"crafted by frost giants who served Vox in ages past, using ice that will never melt even in the hottest fire. " +
			"Wearing it makes your breath visible as mist even in warm rooms, and frost patterns form on nearby surfaces. " +
			"The cold of Permafrost itself flows through this artifact, making its wearer master of ice and winter." :
			this.typicalRing();
	}

	public class FrostResistance extends RingBuff {
		// Provides cold resistance and immunity to chill effects
	}
}
