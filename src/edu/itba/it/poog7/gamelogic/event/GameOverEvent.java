/**
 * 
 */
package edu.itba.it.poog7.gamelogic.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * @author dario
 *
 */
public class GameOverEvent extends Event {

	/**
	 * @param dispatcher The Dispatcher firing this event.
	 */
	public GameOverEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}
}
