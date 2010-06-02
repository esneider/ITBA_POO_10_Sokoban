/**
 * 
 */
package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * Hole tile. Any object that falls into this tile is erased from the game.
 * 
 * @author champo
 */
public class Hole extends Tile {

	/**
	 * Constructor for Hole tile.
	 * 
	 * @param pos The position the hole is in.
	 */
	public Hole(Position pos) {
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
