package edu.itba.it.poog7.gamelogic.tiles.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * This event is triggered by boxes when they leave a tile of the same color as
 * them.
 * 
 */
public class TargetUnmatchedEvent extends Event {

	/**
	 * @param dispatcher
	 *            The dispatcher firing the event.
	 */
	public TargetUnmatchedEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}
}
