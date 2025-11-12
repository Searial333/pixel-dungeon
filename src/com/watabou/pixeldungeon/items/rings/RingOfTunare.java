/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.rings;

public class RingOfTunare extends Ring {

	{
		name = "Ring of Tunare's Blessing";
	}

	@Override
	protected RingBuff buff( ) {
		return new NaturesBlessing();
	}

	@Override
	public String desc() {
		return isKnown() ?
			"This living wood ring was grown rather than crafted, shaped by Tunare's divine will. Small leaves sprout from " +
			"the band, staying perpetually green and fresh. The ring smells of spring rain and blooming flowers, and animals " +
			"are calmed by its presence. Druids and Rangers who wear this ring find their connection to nature greatly " +
			"strengthened, able to communicate more easily with plants and beasts. The wood is harder than steel yet grows " +
			"to fit its wearer perfectly. When healing magic is cast while wearing this ring, flowers briefly bloom around " +
			"the caster before fading away. The ring provides immunity to natural poisons and makes the wearer more resistant " +
			"to disease. In the presence of corruption or undeath, the ring grows uncomfortably warm, warning of unnatural " +
			"threats. Those who wear it report vivid dreams of ancient forests and feel compelled to protect nature from harm." :
			this.typicalRing();
	}

	public class NaturesBlessing extends RingBuff {
		// Enhances nature magic and provides poison/disease resistance
	}
}
