package edu.itba.it.poog7.view_.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic_.tiles.Wall;
import edu.itba.it.poog7.view.*;

/**
 * {@link DrawableElement} corresponding to {@link Wall}
 * 
 * @author dario
 *
 */
public class DWall extends DrawableElement {

	/**
	 * @param board  the {@link Board} in which to draw
	 * @param wall   the corresponding {@link Wall}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DWall(Board board, Wall wall) throws IOException {
		super(board,wall);
		image.setImage("resources/wall.png");
	}
}
