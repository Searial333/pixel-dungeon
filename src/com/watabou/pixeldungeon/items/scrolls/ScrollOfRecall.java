/*
 * Pixel Dungeon - EverQuest II Edition
 * Copyright (C) 2025
 */
package com.watabou.pixeldungeon.items.scrolls;

import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.scenes.InterlevelScene;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.noosa.Game;

public class ScrollOfRecall extends Scroll {

	{
		name = "Scroll of Recall";
	}

	@Override
	protected void doRead() {
		// Similar to EQ2's "Call to Home" ability
		GLog.w( "The scroll glows with teleportation magic, but you resist its pull!" );
		GLog.i( "Perhaps it would be too dangerous to teleport within these cursed dungeons..." );

		setKnown();
	}

	@Override
	public String desc() {
		return
			"These magical scrolls are inscribed by the wizards of the Concordium, the mages' guild of Qeynos. " +
			"A Scroll of Recall is designed to instantly transport the reader back to a safe location - typically " +
			"their home city or the nearest inn. However, powerful wards within these ancient dungeons seem to " +
			"interfere with teleportation magic, preventing the scroll from functioning properly. The scroll still " +
			"contains its magic, but cannot overcome the dungeon's enchantments. Seasoned adventurers know better " +
			"than to rely on magical escape from places like these - the only way out is through. In the open world " +
			"of Norrath, these scrolls are invaluable for quick escapes from danger.";
	}

	@Override
	public int price() {
		return isKnown() ? 35 * quantity : super.price();
	}
}
