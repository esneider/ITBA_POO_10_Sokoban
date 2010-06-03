package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Tile;


/**
 * 
 * @author eordano
 *
 */
public abstract class Chaboncitou extends GameObject {

	public Chaboncitou(Position pos) {
		super(pos);
	}
	
	public boolean canMove(Game state, Direction dir){
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
	public void destructor(Game state) {

		super.destructor(state);
		state.setChaboncitouPos(null);
	}
}
