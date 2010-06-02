/**
 * 
 */
package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * Blank tile.
 * 
 * @author champo
 */
public class Blank extends Tile {

	/**
	 * Constructor for Blank tile.
	 * 
	 * @param pos The position the tile is in.
	 */
	public Blank(Position pos) {
		super(pos);
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

		return true;
	}

	
}
