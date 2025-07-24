/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.utils;

public class GameMath {
	
	/**
	 * Clamps a value between a minimum and maximum
	 */
	public static int gate(int min, int value, int max) {
		return Math.max(min, Math.min(max, value));
	}
	
	/**
	 * Clamps a float value between a minimum and maximum
	 */
	public static float gate(float min, float value, float max) {
		return Math.max(min, Math.min(max, value));
	}
	
	/**
	 * Linear interpolation between two values
	 */
	public static float lerp(float start, float end, float t) {
		return start + t * (end - start);
	}
	
	/**
	 * Smoothstep interpolation
	 */
	public static float smoothstep(float edge0, float edge1, float x) {
		float t = gate(0.0f, (x - edge0) / (edge1 - edge0), 1.0f);
		return t * t * (3.0f - 2.0f * t);
	}
	
	/**
	 * Distance between two points
	 */
	public static float distance(float x1, float y1, float x2, float y2) {
		float dx = x2 - x1;
		float dy = y2 - y1;
		return (float)Math.sqrt(dx * dx + dy * dy);
	}
	
	/**
	 * Square of distance (faster when you don't need exact distance)
	 */
	public static float distanceSquared(float x1, float y1, float x2, float y2) {
		float dx = x2 - x1;
		float dy = y2 - y1;
		return dx * dx + dy * dy;
	}
}
