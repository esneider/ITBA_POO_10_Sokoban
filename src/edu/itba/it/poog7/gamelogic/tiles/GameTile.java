package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.GameElement;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.event.StateUpdateEvent;
import edu.itba.it.poog7.gamelogic.objects.GameObject;


/**
 * Abstract base class for all tiles.
 * 
 * @author champo
 */
public abstract class GameTile extends GameElement {
	
	GameObject object;
	
	/**
	 * Instance a new tile. 
	 * 
	 * @param pos The position the tile is in.
	 */
	public GameTile(Position pos) {
		super(pos);
	}

	/**
	 * Get the level object currently in this tile.
	 * 
	 * @return The object or null if no object is in the tile.
	 */
	public GameObject getObject() {
		return object;
	}
	
	/**
	 * Set an level object to this tile
	 * 
	 * @param object The object being set.
	 */
	public void setObject(GameObject object) {
		this.object = object;
		generateEvent(new StateUpdateEvent(this));
	}
	
	/**
	 * Tell whether an object can move to this tile from a direction.
	 * 
	 * @param dir The direction the object wants to move from.
	 * 
	 * @return True if it can, false otherwise.
	 */
	public boolean canMoveTo(Direction dir) {
		return true;
	}
	
	/**
	 * Tell whether an object can move from this tile in a direction.
	 * 
	 * @param dir The direction the object wants to move in.
	 * 
	 * @return True if it can, false otherwise.
	 */
	public boolean canMoveFrom(Direction dir) {
		return true;
	}

}