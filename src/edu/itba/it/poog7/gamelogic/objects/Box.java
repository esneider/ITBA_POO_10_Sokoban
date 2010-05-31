package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.LevelState;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * 
 * @author eordano
 *
 */
public abstract class Box extends LevelObject {

	public Box(Position pos) {
		super(pos);
	}
	
	public boolean canMove(LevelState state, Direction dir){
		if (!super.canMove(state, dir)){
			return false;
		}
		Tile nextTile = state.getTile(pos.getNeighbourPosition(dir));
		if(nextTile.getObject() != null){
			return false;
		}
		return true;
	}
}
