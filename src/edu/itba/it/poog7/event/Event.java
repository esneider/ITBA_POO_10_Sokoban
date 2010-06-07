package edu.itba.it.poog7.event;

/**
 * Event from whom all internal events inherit. <br/>
 * <br/>
 * 
 * This implementation of the Event Observer/Dispatcher pattern works as
 * follows: There are objects called Event Dispatchers that send messages to
 * anyone who listens to that particular kind of event. When a class wants to
 * listen for a kind of event, it implements the {@link EventListener} and
 * suscribes herself to a {@link EventDispatcher} . A reference to the class is
 * saved to be called the next time a {@link Event} of that kind is called.
 */
public abstract class Event {

	private EventDispatcher dispatcher;

	/**
	 * Constructor for a new event.
	 * 
	 * @param dispatcher the {@link EventDispatcher} in charge of calling all {@link EventListener}s
	 */
	public Event(EventDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * Get the Dispatcher in charge of this kind of event.
	 * 
	 * @return a {@link EventDispatcher} that is able to trigger these Events.
	 */
	public EventDispatcher getDispatcher() {
		return dispatcher;
	}

}
