package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.event.EventDispatcher;

/**
 * Abstract class that represents an object in the game board
 */
public abstract class GameElement extends EventDispatcher {

	protected Position pos;

	/**
	 * Constructor.
	 * 
	 * @param pos
	 *            The initial position.
	 */
	public GameElement(Position pos) {
		this.pos = pos;
	}

	/**
	 * Get the elements position.
	 * 
	 * @return The position.
	 */
	public Position getPosition() {
		return pos;
	}
}
