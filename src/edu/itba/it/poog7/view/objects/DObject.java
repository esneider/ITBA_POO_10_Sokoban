/**
 * 
 */
package edu.itba.it.poog7.view.objects;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.GameElement;
import edu.itba.it.poog7.gamelogic.objects.event.DestroyedEvent;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * A {@link DrawableElement} representing a {@link GameObject} from the backend
 * 
 */
public class DObject extends DrawableElement {

	/**
	 * Constructor 
	 * 
	 * @param myView the board where the object will "live" in
	 * @param element the new element
	 */
	protected DObject(View myView, GameElement element) {
		super(myView, element);

		element.subscribeListener(DestroyedEvent.class, new EventListener() {

			@Override
			public void eventTriggered(Event e) {

				remove();
			}

		});
	}
	/**
	 *	 A "destructor" of this element. It is used to stop drawing on screen 
	 */
	private void remove() {
		view.removeElement(this);
	}
}
