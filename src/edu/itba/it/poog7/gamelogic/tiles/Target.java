/**
 * 
 */
package edu.itba.it.poog7.gamelogic.tiles;

import java.awt.Color;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * Target tile for boxes.
 * 
 * @author champo
 */
public abstract class Target extends Tile {

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canMoveFrom(Direction dir) {
		
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canMoveTo(Direction dir) {
		
		return false;
	}
}
