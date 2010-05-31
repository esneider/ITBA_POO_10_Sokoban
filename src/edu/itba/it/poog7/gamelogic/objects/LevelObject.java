package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.LevelElement;
import edu.itba.it.poog7.gamelogic.LevelState;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

public abstract class LevelObject extends LevelElement {
	
	public LevelObject(Position pos) {
		super(pos);
	}

	public boolean canMove(LevelState state, Direction dir){
		Tile myTile = state.getTile(pos);
		if (!myTile.canMoveFrom(dir))
			return false;
		Tile newTile = state.getTile(pos.getNeighbourPosition(pos));
		if (!newTile.canMoveTo(dir))
			return false;
		return true;
	}
	
	public void move(LevelState state, Direction dir){
		assert canMove(state, dir);
		Tile myTile = state.getTile(this.getPos());
		myTile.setObject(null);
		Tile newTile = state.getTile(this.getPos().getNeighbourPosition(dir));
		newTile.setObject(this);
	}

	public void destructor(LevelState state){
		return;
	}
}
