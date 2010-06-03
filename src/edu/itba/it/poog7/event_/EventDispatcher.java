package edu.itba.it.poog7.event_;

import java.util.LinkedList;
import java.util.List;

/**
 * Event dispatcher for the 'Listener' pattern
 * 
 * @see edu.itba.it.poog7.event_.EventListener
 * 
 * @author dario
 *
 */
public class EventDispatcher {

	private List<EventListener> subscribed = new LinkedList<EventListener>();

	/**
	 * Subscribe an {@link EventListener} to the event dispatcher
	 * 
	 * @param e  the {@link EventListener}
	 */
	public void subscribeListener( EventListener e ) {

		subscribed.add(e);
	}

	/**
	 * Dispatch an event to all listeners
	 * 
	 * @param e  the event
	 */
	public void generateEvent(Object e) {

		for (EventListener listener : subscribed) {
			listener.eventTriggered(e);
		}
	}
}
