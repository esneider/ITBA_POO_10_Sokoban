package edu.itba.it.poog7.event_;

/**
 * Event listener for the 'listener' pattern
 * 
 * @see edu.itba.it.poog7.event_.EventDispatcher
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
