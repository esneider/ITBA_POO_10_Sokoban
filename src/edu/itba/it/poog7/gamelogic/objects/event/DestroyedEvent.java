package edu.itba.it.poog7.gamelogic.objects.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * This event is dispatched whenever a GameObject is erased by a hole
 *
 */
public class DestroyedEvent extends Event {

	/**
	 * @param dispatcher The dispatcher firing this event.
	 */
	public DestroyedEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}
}
