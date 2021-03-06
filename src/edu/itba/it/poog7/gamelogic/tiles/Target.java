package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;

/**
 * Target tile for boxes.
 * 
 */
public class Target extends GameTile {

	RGBColor color;

	/**
	 * Instance a new target tile.
	 * 
	 * @param pos
	 *            The position the tile is in.
	 * @param color
	 *            The color that the tile has.
	 */
	public Target(Position pos, RGBColor color) {
		super(pos);
		this.color = color;
	}

	/**
	 * Get the tiles color.
	 * 
	 * @return The tiles color.
	 */
	public RGBColor getColor() {
		return color;
	}

	@Override
	public String toString() {
		return pos + "," + ElementType.TARGET.getInt() + ",0," + color;
	}
}
