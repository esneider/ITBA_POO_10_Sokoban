package edu.itba.it.poog7.view.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.tiles.Wall;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Wall}
 * 
 * @author dario
 *
 */
public class DWall extends DrawableElement {

	/**
	 * @param view  the {@link Board} in which to draw
	 * @param wall   the corresponding {@link Wall}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DWall(View view, Wall wall) throws IOException {
		super(view, wall);
		image.setImage("resources/wall.jpg");
	}
}
