package edu.itba.it.poog7.gamelogic.tiles.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * @author dario
 *
 */
public class TargetMatchedEvent extends Event {
	
	/**
	 * @param dispatcher The dispatcher that fired the event.
	 */
	public TargetMatchedEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}
}
