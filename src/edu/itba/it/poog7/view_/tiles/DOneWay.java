package edu.itba.it.poog7.view_.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic_.tiles.OneWay;
import edu.itba.it.poog7.view.*;

/**
 * {@link DrawableElement} corresponding to {@link OneWay}
 * 
 * @author dario
 *
 */
public class DOneWay extends DrawableElement {

	/**
	 * @param board   the {@link Board} in which to draw
	 * @param oneWay  the corresponding {@link OneWay}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DOneWay(Board board, OneWay oneWay) throws IOException {
		super(board,oneWay);
		image.setImage("resources/path.png");
		switch (oneWay.getDirection()) {
			case RIGHT:
				image.rotate(1);
				break;
			case DOWN:
				image.rotate(2);
				break;
			case LEFT:
				image.rotate(3);
				break;
		}
	}
}
