package edu.itba.it.poog7.view.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Blank}
 * 
 * @author dario
 *
 */
public class DBlank extends DrawableElement {

	/**
	 * @param view  the {@link Board} in which to draw
	 * @param blank  the corresponding {@link Blank}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DBlank(View view, Blank blank) throws IOException {
		super(view, blank);
	}
}
