	package edu.itba.it.poog7.view.tiles;

import java.io.IOException;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.event.TargetMatchedEvent;
import edu.itba.it.poog7.gamelogic.event.TargetUnmatchedEvent;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.Image;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Target}
 * 
 * @author dario
 *
 */
public class DTarget extends DrawableElement {

	private Image brightImage;
	private Image normalImage;
	
	/**
	 * @param view   the {@link Board} in which to draw
	 * @param target  the corresponding {@link Target}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DTarget(View view, Target target) throws IOException {
		super(view, target);
		image.setImage("resources/target.png");
		image.dye(target.getColor());
	
		normalImage = image;
		brightImage = new Image(target.getPosition());
		brightImage.setImage("resources/target.png");
		brightImage.increaseBrightness();
		
		target.subscribeListener(TargetMatchedEvent.class, new EventListener() {
			
			@Override
			public void eventTriggered(Event e) {

				image = brightImage;
				draw();
			}
		});
		
		target.subscribeListener(TargetUnmatchedEvent.class, new EventListener() {
			
			@Override
			public void eventTriggered(Event e) {
				image = normalImage;
				draw();
			}
		});
	}
}
