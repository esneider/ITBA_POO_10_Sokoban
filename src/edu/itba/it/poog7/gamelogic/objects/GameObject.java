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
	
	public GameObject(Position pos) {
		super(pos);
	}

	public boolean canMove(Game game, Direction dir){
		Tile myTile = game.getTile(pos);
		if (!myTile.canMoveFrom(dir))
			return false;
		Tile newTile = game.getTile(pos.getNeighbourPosition(dir));
		if (!newTile.canMoveTo(dir))
			return false;
		return true;
	}
	
	public void move(Game state, Direction dir){
		assert canMove(state, dir);
		Tile myTile = state.getTile(this.getPos());
		myTile.setObject(null);
		Tile newTile = state.getTile(this.getPos().getNeighbourPosition(dir));
		newTile.setObject(this);
	}

	public abstract void destructor(Game state);
}
