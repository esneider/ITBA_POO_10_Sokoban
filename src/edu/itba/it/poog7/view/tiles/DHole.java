package edu.itba.it.poog7.view.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.tiles.Hole;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;

/**
 * {@link DrawableElement} corresponding to {@link Hole}
 * 
 * @author dario
 *
 */
public class DHole extends DrawableElement {

	/**
	 * @param board  the {@link Board} in which to draw
	 * @param hole   the corresponding {@link Hole}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DHole(Board board, Hole hole) throws IOException {
		super(board, hole);
		image.setImage("resources/hole.png");
	}
}
