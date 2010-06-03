package edu.itba.it.poog7.view_.objects;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic_.objects.Box;
import edu.itba.it.poog7.view.*;

/**
 * {@link DrawableElement} corresponding to {@link Box}
 * 
 * @author dario
 *
 */
public class DBox extends DrawableElement {

	/**
	 * @param board  the {@link Board} in which to draw
	 * @param box    the corresponding {@link Box}
	 * 
	 * @throws IOException  if there is an error when opening the image file
	 */
	public DBox(Board board, Box box) throws IOException {
		super(board,box);
		image.setImage("resources/box.png");
		image.dye(box.getColor());
		image.setTransparent(true);
	}
}