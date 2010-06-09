package edu.itba.it.poog7.event;

/**
 * Event listener for the 'listener' pattern
 * 
 * @see edu.itba.it.poog7.event.EventDispatcher
 *
 */
public interface EventListener {

	/**
	 * Event listener
	 * 
	 * @param e  the event
	 */
	public void eventTriggered(Event e);
}
