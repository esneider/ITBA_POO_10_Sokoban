package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction; 
import edu.itba.it.poog7.gamelogic.GameElement;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * 
 * @author eordano
 *
 */
public abstract class GameObject extends GameElement {
	/**
	 * Make a new GameObject. This method just calls the one in the parent class.
	 * 
	 * @param pos
	 */
	public GameObject(Position pos) {
		super(pos);
	}

	/**
	 * Basic checking that needs to be made in order for a Object to move into another Tile.
	 * For example, is the next tile a Wall? We delegate that to the tile to say.
	 * Also, check for the current Tile: Can I move out of this tile in this direction? Perhaps it's a 
	 * one-way-only kind of tile.
	 * 
	 * @param game     The current instance of Game where the Object is in.
	 * @param dir      The direction in which to move.
	 * 
	 * @return         a boolean specifying whether the Object can move         
	 */
	public boolean canMove(Game game, Direction dir){
		Tile myTile = game.getTile(pos);
		if (!myTile.canMoveFrom(dir))
			return false;
		Tile newTile = game.getTile(pos.getNeighbourPosition(dir));
		if (!newTile.canMoveTo(dir))
			return false;
		return true;
	}
	
	/**
	 * Move the object into another tile.
	 * Just in case, there is an assertion to warn if the canMove() method wasn't called.
	 * 
	 * @param state
	 * @param dir
	 */
	public void move(Game state, Direction dir){
		assert canMove(state, dir);
		Tile myTile = state.getTile(this.getPos());
		myTile.setObject(null);
		Tile newTile = state.getTile(this.getPos().getNeighbourPosition(dir));
		newTile.setObject(this);
	}

	/**
	 * Deprecated in the BROTT
	 * 
	 * @param state
	 */
	public abstract void destructor(Game state);
}
