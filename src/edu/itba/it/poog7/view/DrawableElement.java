package edu.itba.it.poog7.view;

import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * Listener of a {@link GameElement}, that draws it when it changes
 * 
 * @see  edu.itba.it.poog7.event.EventListener
 * 
 * @author dario
 *
 */
public abstract class DrawableElement implements EventListener {

	protected Image image;
	protected View view;

	/**
	 * Instance a {@link DrawableElement}
	 * 
	 * @param board    the {@link Board} in which to draw
	 * @param element  the {@link Position} in the board
	 */
	protected DrawableElement(View view, GameElement element) {

		this.view = view;
		this.image = new Image(element.getPosition());
		//FIXME: Theres no such method? Is GameElement missing an extends or an implements?
		element.subscribeListener(this);
	}

	@Override
	public void eventTriggered(Object e) {

		if (e == null) {
			view.removeElement(this);
		}else{
			image.setPosition((Position)e);
			view.getBoard().draw(image);
		}
	}
}
