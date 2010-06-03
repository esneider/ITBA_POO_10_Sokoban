package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.Direction;


/**
 * Wall tile.
 * 
 * @author champo
 */
public class Wall extends Tile {

	/**
	 * Instance a new Wall tile.
	 * 
	 * @param pos The position the tile is in.
	 */
	public Wall(Position pos) {
		super(pos);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canMoveFrom(Direction dir) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canMoveTo(Direction dir) {
		return false;
	}
}
