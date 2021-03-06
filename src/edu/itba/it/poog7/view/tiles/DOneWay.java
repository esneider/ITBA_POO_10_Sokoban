package edu.itba.it.poog7.view.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.tiles.OneWay;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link OneWay}
 * 
 *
 */
public class DOneWay extends DrawableElement {

	/**
	 * @param view   the {@link Board} in which to draw
	 * @param oneWay  the corresponding {@link OneWay}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DOneWay(View view, OneWay oneWay) throws IOException {
		super(view, oneWay);
		image.setImage("resources/path.png");
		switch (oneWay.getDirection()) {
			case RIGHT:
				image.rotate(3);
				break;
			case UP:
				image.rotate(2);
				break;
			case LEFT:
				image.rotate(1);
				break;
		}
	}
}
