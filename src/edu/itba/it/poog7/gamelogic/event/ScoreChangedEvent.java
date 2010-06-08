package edu.itba.it.poog7.gamelogic.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * Every time the score changes, the class {@link Game} triggers this event.
 */
public class ScoreChangedEvent extends Event {
	/**
	 * @param dispatcher
	 *            The dispatcher firing this event.
	 */
	public ScoreChangedEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}
}
