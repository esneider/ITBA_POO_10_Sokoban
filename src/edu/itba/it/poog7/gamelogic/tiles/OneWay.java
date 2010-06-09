package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;


/**
 * One way tile.
 * 
 */
public class OneWay extends GameTile {
	
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

	@Override
	public String toString() {
		return pos+","+ElementType.ONEWAY.getInt()+","+direction.getInt()+","+RGBColor.black;
	}
}
