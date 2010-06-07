package edu.itba.it.poog7.gamelogic.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;
import edu.itba.it.poog7.gamelogic.Direction;

/**
 * @author dario
 *
 */
public class MoveChaboncitouEvent extends Event{

	private Direction dir;
	
	/**
	 * @param dispatcher The dispatcher firing this event.
	 * @param dir The direction to move in.
	 */
	public MoveChaboncitouEvent(EventDispatcher dispatcher, Direction dir) {
		super(dispatcher);
		this.dir = dir;
	}

	/**
	 * @return The direction to move in.
	 */
	public Direction getDirection() {
		return dir;
	}
}
