package edu.itba.it.poog7.gamelogic.event;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.GameTile;

/**
 * When a Game Element changes his status, this event is triggered. This can happen when:
 * <ul>
 * <li>A {@link GameTile} has no more something on top of it</li>
 * <li>A {@link GameTile} has a new object on top of it</li>
 * <li>A {@link GameObject} has changed his {@link Position}</li>
 * </ul>
 */
public class StateUpdateEvent extends Event {

	/**
	 * @param dispatcher The dispatcher firing the event.
	 */
	public StateUpdateEvent(EventDispatcher dispatcher) {
		super(dispatcher);
	}

}
