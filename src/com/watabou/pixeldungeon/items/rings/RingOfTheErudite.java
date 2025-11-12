/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.rings;

public class RingOfTheErudite extends Ring {

	{
		name = "Ring of the Erudite Scholar";
	}

	@Override
	protected RingBuff buff( ) {
		return new ScholarlyMind();
	}

	@Override
	public String desc() {
		return isKnown() ?
			"Crafted in the libraries of Erudin, this platinum ring is set with a sapphire that seems to contain swirling " +
			"formulas and equations. Erudite mages created these rings to enhance their already formidable intellect and " +
			"magical prowess. The band is inscribed with the High Erudite motto: 'Knowledge is the only true power.' " +
			"Wearing this ring expands the wearer's mana pool and makes spell formulas come more easily to mind. Complex " +
			"magical calculations that would normally take minutes happen in seconds. The sapphire glows when the wearer " +
			"learns new spells or gains knowledge, recording their progress like a living journal. Sorcerers, Wizards, and " +
			"other scholarly casters prize these rings for the edge they provide in magical research and combat. The ring " +
			"occasionally projects relevant knowledge directly into the wearer's mind, as if accessing a vast library. Some " +
			"wearers report hearing whispered lessons from long-dead Erudite archmages, their wisdom preserved in the ring's " +
			"enchantment." :
			this.typicalRing();
	}

	public class ScholarlyMind extends RingBuff {
		// Increases mana pool and spell effectiveness
	}
}
