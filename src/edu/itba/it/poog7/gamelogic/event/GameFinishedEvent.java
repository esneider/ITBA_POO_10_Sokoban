/**
 * 
 */
package edu.itba.it.poog7.gamelogic.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;

/**
 * This event is triggered when the game is finished (i.e., the player has won)
 */
public class GameFinishedEvent extends Event {

	/**
	 * Constructor.
	 * 
	 * @param dispatcher
	 *            The dispatcher firing this event.
	 */
	public GameFinishedEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}

}
