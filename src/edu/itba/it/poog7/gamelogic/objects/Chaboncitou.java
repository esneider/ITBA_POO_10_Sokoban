package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * 
 * @author eordano
 *
 */
public class Chaboncitou extends GameObject {

	/**
	 * Simple constructor that delegates everything to his father.
	 * 
	 * @param pos
	 */
	public Chaboncitou(Position pos) {
		super(pos);
	}
	
	/**
	 * Check for the ability to move into another square. If the square is occupied by a box, there
	 * is still a chance that he can move it.
	 * 
	 * @param state    The current instance of Game where the Chaboncitou is in.
	 * @param dir      The direction in which to move.
	 */
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
	
	/**
	 * Refactored into a dark deprecated world?
	 * 
	 * This went away in the BROTT
	 */
	@Override
	public void destructor(Game state) {
		super.destructor(state);
		state.setChaboncitouPos(null);
	}
}
