package edu.itba.it.poog7.view;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.GameElement;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.event.StateUpdateEvent;

/**
 * Listener of a {@link GameElement}, that draws it when it changes
 * 
 * @see edu.itba.it.poog7.event.EventListener
 * 
 * @author dario
 * 
 */
public abstract class DrawableElement {

	protected Image image;
	protected View view;

	/**
	 * Instance a {@link DrawableElement}
	 * @param myView 
	 *            the {@link View} in which to draw
	 * @param element
	 *            the {@link Position} in the board
	 */
	protected DrawableElement(View myView, GameElement element) {

		view = myView;
		image = new Image(element.getPosition());
		element.subscribeListener(StateUpdateEvent.class, new EventListener() {

			@Override
			public void eventTriggered(Event e) {
				setPosition((GameElement) e.getDispatcher());
				draw();
			}
		});
	}
	
	/**
	 * Set the objects position.
	 * @param element The element that contains the position info.
	 */
	protected void setPosition(GameElement element) {
		
		image.setPosition(element.getPosition());
	}

	/**
	 * Draw the element in the view we have.
	 */
	public void draw() {
		if (view.getBoard() != null) {
			view.getBoard().draw(image);
			view.repaint();
		}
	}
}
