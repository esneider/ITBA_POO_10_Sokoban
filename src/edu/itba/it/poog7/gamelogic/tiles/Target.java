package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Color;
import edu.itba.it.poog7.gamelogic.Position;



/**
 * Target tile for boxes.
 * 
 * @author champo
 */
public class Target extends Tile {

	Color color;

	/**
	 * Instance a new target tile.
	 * 
	 * @param pos   The position the tile is in.
	 * @param color The color that the tile has.
	 */
	public Target(Position pos, Color color) {
		super(pos);
		this.color = color;
	}
	
	/**
	 * Get the tiles color.
	 * 
	 * @return The tiles color.
	 */
	public Color getColor() {
		return color;
	}
}
