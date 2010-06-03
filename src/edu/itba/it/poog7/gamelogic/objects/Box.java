package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Color;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.gamelogic.tiles.Tile;



/**
 * 
 * @author eordano
 *
 */
public abstract class Box extends GameObject {

	private Color color;

	public Box(Position pos, Color color) {
		super(pos);
		this.color = color;
	}
	
	public boolean canMove(Game state, Direction dir) {

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

	public Color getColor() {
		return color;
	}

	@Override
	public void destructor(Game state) {

		super.destructor(state);
		state.decRemainingBoxes();
	}
}
