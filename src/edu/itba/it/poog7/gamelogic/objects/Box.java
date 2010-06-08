package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;
import edu.itba.it.poog7.gamelogic.tiles.GameTile;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.gamelogic.tiles.event.TargetMatchedEvent;
import edu.itba.it.poog7.gamelogic.tiles.event.TargetUnmatchedEvent;

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

		GameTile toTile = state.getTile(pos.getNeighbourPosition(dir));
		if(toTile.getObject() != null){
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(Game state, Direction dir) {
		
		GameTile oldTile = state.getTile(pos);
		if (oldTile instanceof Target) {

			if (((Target) oldTile).getColor().equals(color)) {
			
				generateEvent(new TargetUnmatchedEvent(this));
			}
		}
		
		super.move(state, dir);
		checkMatched(state);
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

	/**
	 * Check whether this Box is matched with a proper target.
	 */
	public void checkMatched(Game state) {
		
		GameTile tile = state.getTile(pos);
		if (tile instanceof Target) {

			if (((Target) tile).getColor().equals(color)) {
			
				generateEvent(new TargetMatchedEvent(this));
			}
		}
	}
}
