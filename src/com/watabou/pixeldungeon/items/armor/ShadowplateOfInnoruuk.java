/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.armor;

import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public class ShadowplateOfInnoruuk extends Armor {

	{
		name = "Shadowplate of Innoruuk";
		image = ItemSpriteSheet.ARMOR_PLATE;
	}

	public ShadowplateOfInnoruuk() {
		super( 6 );  // Heavy armor
	}

	@Override
	public String desc() {
		return
			"This dark armor was forged in the Temple of Hate in Neriak, blessed by Innoruuk himself. The black metal " +
			"seems to absorb light, and looking at it too long causes unease in most observers. Shadowknights who wear " +
			"this armor feel Innoruuk's dark power flowing through them, strengthening their connection to hate and " +
			"despair. The armor is decorated with spikes and cruel edges designed not for practical defense but to " +
			"inspire fear. Screaming faces are worked into the breastplate, said to be the souls of those slain by " +
			"previous wearers. Dark mist occasionally seeps from the joints, and the armor feels cold to the touch even " +
			"in blazing heat. The interior is lined with leather from darkscale drakes, providing surprising comfort " +
			"despite the ominous exterior. When the wearer channels dark magic, the armor pulses with red light like a " +
			"beating heart. Those who stand too close report hearing whispers of temptation and dark promises. The armor " +
			"is cursed to prevent removal by good-aligned beings, burning their hands with unholy fire if they try.";
	}
}
