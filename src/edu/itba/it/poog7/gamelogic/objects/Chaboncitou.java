package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.LevelState;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * 
 * @author eordano
 *
 */
public abstract class Chaboncitou extends LevelObject {

	public Chaboncitou(Position pos) {
		super(pos);
	}
	
	public boolean canMove(LevelState state, Direction dir){
		if (!super.canMove(state, dir)){
			return false;
		}
		Tile nextTile = state.getTile(pos.getNeighbourPosition(dir));
		if (nextTile.getObject() != null){
			if (nextTile.getObject() instanceof Box){
				return nextTile.getObject().canMove(state, dir);
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void destructor(LevelState state) {
		state.setChaboncitouPos(null);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
