package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Color;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * The class that represents the colored boxes in the game.
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

		if (!super.canMove(state, dir)) {
			return false;
		}

		Tile toTile = state.getTile(pos.getNeighbourPosition(dir));
		if(toTile.getObject() != null){
			return false;
		}

		return true;
	}

	@Override
    public void move(Game state, Direction dir) {

		Tile fromTile = state.getTile(pos);

		if (fromTile instanceof Target && ((Target)fromTile).getColor() == color) {
			state.decBoxesMatched();
		}

		super.move(state, dir);

		Tile toTile = state.getTile(pos);

		if (toTile instanceof Target && ((Target)toTile).getColor() == color) {
			state.incBoxesMatched();
		}
	}

	/**
	 * Didn't this got deprecated in the BROTT ? (Big Refactor Of The Tuesday)
	 */
	@Override
	public void destructor(Game state) {
		super.destructor(state);
		state.decRemainingBoxes();
	}

	/**
	 * Getter for color
	 * 
	 * @return the Color of the box
	 */
	public Color getColor() {
		return color;
	}
}
