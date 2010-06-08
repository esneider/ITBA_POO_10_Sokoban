/**
 * 
 */
package edu.itba.it.poog7.gamelogic.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * If the game is over and the player has not won, this event is dispatched
 */
public class GameOverEvent extends Event {

	/**
	 * @param dispatcher
	 *            The Dispatcher firing this event.
	 */
	public GameOverEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}
}
