package edu.itba.it.poog7.view.objects;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Box}
 * 
 * @author dario
 *
 */
public class DBox extends DObject {

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
	}
}
