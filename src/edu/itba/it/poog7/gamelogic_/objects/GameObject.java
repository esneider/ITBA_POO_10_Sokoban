package edu.itba.it.poog7.gamelogic_.objects;

import edu.itba.it.poog7.gamelogic_.Direction;
import edu.itba.it.poog7.gamelogic_.Game;
import edu.itba.it.poog7.gamelogic_.GameElement;
import edu.itba.it.poog7.gamelogic_.Position;
import edu.itba.it.poog7.gamelogic_.tiles.Hole;
import edu.itba.it.poog7.gamelogic_.tiles.Tile;


/**
 * 
 * @author eordano
 *
 */
public abstract class GameObject extends GameElement {

	public GameObject(Position pos) {
		super(pos);
	}

	public boolean canMove(Game game, Direction dir) {

		Tile fromTile = game.getTile(pos);
		if (!fromTile.canMoveFrom(dir))
			return false;

		Tile toTile = game.getTile(pos.getNeighbourPosition(dir));
		if (!toTile.canMoveTo(dir))
			return false;

		return true;
	}

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

	public void destructor(Game state) {
		generateEvent(null);
	}
}