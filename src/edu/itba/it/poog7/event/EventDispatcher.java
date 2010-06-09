package edu.itba.it.poog7.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Event dispatcher for the 'Listener' pattern
 * 
 * @see edu.itba.it.poog7.event.EventListener
 * 
 */
public class EventDispatcher {

	private Map<String, List<EventListener>> subscribed = new HashMap<String, List<EventListener>>();

	/**
	 * Subscribe an {@link EventListener} to an {@link Event} dispatcher
	 * 
	 * @param e
	 *            the {@link EventListener}
	 * @param eListener
	 *            the {@link Event}
	 */
	public void subscribeListener(Class<? extends Event> e,
			EventListener eListener) {

		String eName = e.getName();

		if (!subscribed.containsKey(eName)) {
			subscribed.put(eName, new LinkedList<EventListener>());
		}
		subscribed.get(eName).add(eListener);
	}

	/**
	 * Dispatch an {@link Event} to all the {@link EventListener} assigned to it
	 * 
	 * @param e
	 *            the event
	 */
	protected void generateEvent(Event e) {

		String eName = e.getClass().getName();

		List<EventListener> listenerList = subscribed.get(eName);

		if (listenerList != null) {

			for (EventListener listener : listenerList) {
				listener.eventTriggered(e);
			}
		}
	}
}
