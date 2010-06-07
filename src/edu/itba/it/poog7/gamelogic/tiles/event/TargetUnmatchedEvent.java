package edu.itba.it.poog7.gamelogic.tiles.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * @author dario
 *
 */
public class TargetUnmatchedEvent extends Event {

	/**
	 * @param dispatcher The dispatcher firing the event.
	 */
	public TargetUnmatchedEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}
}
