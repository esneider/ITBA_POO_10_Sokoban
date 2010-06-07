package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;


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

	@Override
	public String toString() {
		return pos+","+ElementType.BLANK.getInt()+",0,"+RGBColor.black;
	}
}
