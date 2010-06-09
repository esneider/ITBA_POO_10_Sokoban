package edu.itba.it.poog7.view.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.tiles.Hole;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Hole}
 * 
 *
 */
public class DHole extends DrawableElement {

	/**
	 * @param view  the {@link Board} in which to draw
	 * @param hole   the corresponding {@link Hole}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DHole(View view, Hole hole) throws IOException {
		super(view, hole);
		image.setImage("resources/hole.png");
	}
}
