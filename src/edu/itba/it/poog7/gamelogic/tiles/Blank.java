/**
 * 
 */
package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * @author champo
 *
 */
public abstract class Blank extends Tile {

	public Blank(Position pos) {
		super(pos);
	}

	@Override
	public boolean canMoveFrom(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMoveTo(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
