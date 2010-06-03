package edu.itba.it.poog7.view_.objects;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic_.objects.Chaboncitou;
import edu.itba.it.poog7.view_.*;

/**
 * {@link DrawableElement} corresponding to {@link Chaboncitou}
 * 
 * @author dario
 *
 */
public class DChaboncitou extends DrawableElement {

	/**
	 * @param board        the {@link Board} in which to draw
	 * @param chaboncitou  the corresponding {@link Chaboncitou}
	 * 
	 * @throws IOException  if there is an error while opening the image file
	 */
	public DChaboncitou(Board board, Chaboncitou chaboncitou) throws IOException {
		super(board,chaboncitou);
		image.setImage("resources/smile.png");
		image.setTransparent(true);
	}
}
