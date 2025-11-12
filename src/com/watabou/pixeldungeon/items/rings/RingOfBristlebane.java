/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.rings;

public class RingOfBristlebane extends Ring {

	{
		name = "Ring of Bristlebane's Luck";
	}

	@Override
	protected RingBuff buff( ) {
		return new TrickstersFavor();
	}

	@Override
	public String desc() {
		return isKnown() ?
			"This ring seems ordinary at first glance—a simple silver band with no decorations. But those who know to look " +
			"closer see tiny, ever-changing etchings that form jokes and pranks in multiple languages. The ring was blessed " +
			"by Bristlebane, the King of Thieves, and brings uncanny luck to rogues and tricksters. Critical strikes happen " +
			"more often, locks seem easier to pick, and guards always seem to look the other way at just the right moment. " +
			"The ring has a mischievous personality, occasionally becoming difficult to remove or swapping to different " +
			"fingers when its wearer isn't paying attention. Thieves who wear it report hearing faint laughter during their " +
			"most daring heists, as if Bristlebane himself is amused by their antics. The ring makes its wearer unnaturally " +
			"lucky—dice rolls favor them, attacks miss by inches, and treasure chests contain better loot. However, the luck " +
			"comes with a price: the wearer becomes compelled to play pranks and take unnecessary risks, as if infected by " +
			"the god's chaotic nature." :
			this.typicalRing();
	}

	public class TrickstersFavor extends RingBuff {
		// Increases critical hit chance and luck-based effects
	}
}
