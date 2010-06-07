package edu.itba.it.poog7.gamelogic.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * @author dario
 *
 */
public class StateUpdateEvent extends Event {

	/**
	 * @param dispatcher The dispatcher firing the event.
	 */
	public StateUpdateEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}

}
