package edu.itba.it.poog7.event;

/**
 * Event listener for the 'listener' pattern
 * 
 * @see edu.itba.it.poog7.event.EventDispatcher
 * 
 * @author dario
 *
 */
public interface EventListener {

	/**
	 * Event listener
	 * 
	 * @param e  the event
	 */
	public void eventTriggered(Object e);
}
