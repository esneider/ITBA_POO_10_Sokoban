	package edu.itba.it.poog7.view.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Target}
 * 
 *
 */
public class DTarget extends DrawableElement {

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
	}
}
