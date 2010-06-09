package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;


/**
 * Wall tile.
 */
public class Wall extends GameTile {

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

	@Override
	public String toString() {
		return pos+","+ElementType.WALL.getInt()+",0,"+RGBColor.black;
	}
}
