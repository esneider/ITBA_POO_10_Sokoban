package edu.itba.it.poog7.view.objects;

import java.io.IOException;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.GameElement;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.tiles.event.TargetMatchedEvent;
import edu.itba.it.poog7.gamelogic.tiles.event.TargetUnmatchedEvent;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.Image;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Box}
 * 
 * @author dario
 *
 */
public class DBox extends DObject {

	private Image normalImage;
	private Image brightImage;

	/**
	 * @param view  the {@link Board} in which to draw
	 * @param box    the corresponding {@link Box}
	 * 
	 * @throws IOException  if there is an error when opening the image file
	 */
	public DBox(View view, Box box) throws IOException {
		super(view, box);
		image.setImage("resources/box.png");
		image.dye(box.getColor());
		image.setTransparent(true);
		
		normalImage = image;
		
		brightImage = new Image(box.getPosition());
		brightImage.setImage("resources/box.png");
		brightImage.dye(box.getColor());
		brightImage.increaseBrightness();
		brightImage.setTransparent(true);
		
		box.subscribeListener(TargetMatchedEvent.class, new EventListener() {
			
			@Override
			public void eventTriggered(Event e) {

				image = brightImage;
				draw();
			}
		});
		
		box.subscribeListener(TargetUnmatchedEvent.class, new EventListener() {
			
			@Override
			public void eventTriggered(Event e) {
				image = normalImage;
				draw();
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setPosition(GameElement element) {
		
		super.setPosition(element);
		brightImage.setPosition(element.getPosition());
	}
}
