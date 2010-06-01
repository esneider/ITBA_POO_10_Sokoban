package edu.itba.it.poog7.gamelogic.objects;

import java.awt.Color;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * 
 * @author eordano
 *
 */
public abstract class Box extends GameObject {

	Color color;

	public Box(Position pos, Color color) {
		super(pos);
		this.color = color;
	}
	
	public boolean canMove(Game state, Direction dir){
		if (!super.canMove(state, dir)){
			return false;
		}
		Tile nextTile = state.getTile(pos.getNeighbourPosition(dir));
		if(nextTile.getObject() != null){
			return false;
		}
		return true;
	}

	@Override
	public void destructor(Game state) {
		state.decRemainingBoxes();
	}
}
