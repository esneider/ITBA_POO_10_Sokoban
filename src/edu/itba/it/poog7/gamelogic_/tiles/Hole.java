package edu.itba.it.poog7.gamelogic_.tiles;

import edu.itba.it.poog7.gamelogic_.Position;


/**
 * Hole tile. Any object that falls into this tile is erased from the game.
 * 
 * @author champo
 */
public class Hole extends Tile {

	/**
	 * Constructor for Hole tile.
	 * 
	 * @param pos The position the hole is in.
	 */
	public Hole(Position pos) {
		super(pos);
	}
}
