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
public abstract class OneWay extends Tile {
	
	Direction dir;

	public OneWay(Position pos, Direction dir) {
		super(pos);
		this.dir = dir;
	}
	
	public Direction getDir() {
		return dir;
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
