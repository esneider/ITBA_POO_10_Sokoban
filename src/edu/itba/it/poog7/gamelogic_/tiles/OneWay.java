package edu.itba.it.poog7.gamelogic_.tiles;

import edu.itba.it.poog7.gamelogic_.Direction;
import edu.itba.it.poog7.gamelogic_.Position;


/**
 * One way tile.
 * 
 * @author champo
 */
public class OneWay extends Tile {
	
	Direction direction;

	/**
	 * Instance a new OneWay tile.
	 * 
	 * @param pos         The position the tile is in.
	 * @param orientation The direction that objects can move in.
	 */
	public OneWay(Position pos, Direction orientation) {
		super(pos);
		this.direction = orientation;
	}
	
	/**
	 * Get the tiles direction.
	 * 
	 * @return the direction.
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canMoveFrom(Direction dir) {
		
		return dir == direction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canMoveTo(Direction dir) {
		
		return dir == direction;
	}
}
