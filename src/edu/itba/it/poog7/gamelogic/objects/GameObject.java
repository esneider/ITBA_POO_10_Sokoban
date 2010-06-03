package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction; 
import edu.itba.it.poog7.gamelogic.GameElement;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Hole;
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
	public boolean canMove(Game game, Direction dir) {

		Tile fromTile = game.getTile(pos);
		if (!fromTile.canMoveFrom(dir))
			return false;

		Tile toTile = game.getTile(pos.getNeighbourPosition(dir));
		if (!toTile.canMoveTo(dir))
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
	public void move(Game state, Direction dir) {

		assert canMove(state, dir);

		Position toPos = pos.getNeighbourPosition(dir);
		Tile fromTile = state.getTile(pos);
		Tile toTile   = state.getTile(toPos);

		fromTile.setObject(null);

		if ( toTile.getObject() != null )
			toTile.getObject().move(state, dir);

		if (toTile instanceof Hole)
			destructor(state); // almighty GC
		else{
			toTile.setObject(this);
			pos = toPos;
			generateEvent();
		}
	}

    /**
     * I think this got Deprecated in the BROTT
     * 
     * @param state
     */
	public void destructor(Game state) {
		generateEvent(null);
	}
}
