package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;
import edu.itba.it.poog7.gamelogic.objects.GameObject;


/**
 * Hole tile. Any object that falls into this tile is erased from the game.
 * 
 */
public class Hole extends GameTile {

	/**
	 * Constructor for Hole tile.
	 * 
	 * @param pos The position the hole is in.
	 */
	public Hole(Position pos) {
		super(pos);
	}
	
	@Override
	public void setObject(GameObject object) {

		super.setObject(null);
		if (object != null) {
			object.destructor();
		}
		super.setObject(null);
	}

	@Override
	public String toString() {
		return pos+","+ElementType.HOLE.getInt()+",0,"+RGBColor.black;
	}
}
