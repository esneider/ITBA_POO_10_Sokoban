package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.LevelElement;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.LevelObject;

/**
 * Abstract base class for all tiles.
 * 
 * @author champo
 */
public abstract class Tile extends LevelElement {
	
	LevelObject object;
	
	/**
	 * Instance a new tile. 
	 * 
	 * @param pos The position the tile is in.
	 */
	public Tile(Position pos) {
		super(pos);
	}

	/**
	 * Get the level object currently in this tile.
	 * 
	 * @return The object or null if no object is in the tile.
	 */
	public LevelObject getObject() {
		return object;
	}
	
	/**
	 * Set an level object to this tile
	 * @param object
	 */
	public void setObject(LevelObject object) {
		this.object = object;
	}
	
	/**
	 * Tell whether an object can move to this tile from a direction.
	 * 
	 * @param dir The direction the object wants to move from.
	 * 
	 * @return True if it can, false otherwise.
	 */
	public abstract boolean canMoveTo(Direction dir);
	
	/**
	 * Tell whether an object can move from this tile in a direction.
	 * 
	 * @param dir The direction the object wants to move in.
	 * 
	 * @return True if it can, false otherwise.
	 */
	public abstract boolean canMoveFrom(Direction dir);
}