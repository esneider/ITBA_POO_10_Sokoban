package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * The class that represents the colored boxes in the game.
 * 
 * @author eordano
 *
 */
public class Box extends GameObject {

	/**
	 * The boxs color.
	 */
	RGBColor color;

	/**
	 * Constructor of a box, takes all the information needed.
	 * 
	 * @param pos The initial position.
	 * @param color The color of te box.s
	 */
	public Box(Position pos, RGBColor color) {
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

	/**
	 * Getter for color
	 * 
	 * @return the Color of the box
	 */
	public RGBColor getColor() {
		return color;
	}

	@Override
	public String toString() {
		return pos+","+ElementType.BOX.getInt()+",0,"+color;
	}
}
