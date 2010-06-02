package edu.itba.it.poog7.gamelogic.objects;

import java.awt.Color;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * The class that represents the color boxes in the game.
 * 
 * @author eordano
 *
 */
public class Box extends GameObject {

	Color color;

	/**
	 * Constructor of a box, takes all the information needed.
	 * 
	 * @param pos
	 * @param color
	 */
	public Box(Position pos, Color color) {
		super(pos);
		this.color = color;
	}
	
	/**
	 * A box is unable to move into a tile already occupied by another box.
	 * 
	 * @param state    The current instance of Game where the box is in.
	 * @param dir      The direction in which to move.
	 */
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

	/**
	 * Isn't this deprecated in the BROTT ? (Big Refactor Of The Tuesday)
	 */
	@Override
	public void destructor(Game state) {
		state.decRemainingBoxes();
	}
}
