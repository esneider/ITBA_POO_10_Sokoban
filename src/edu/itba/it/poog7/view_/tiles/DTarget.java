package edu.itba.it.poog7.view_.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic_.tiles.Target;
import edu.itba.it.poog7.view_.*;

/**
 * {@link DrawableElement} corresponding to {@link Target}
 * 
 * @author dario
 *
 */
public class DTarget extends DrawableElement {

	/**
	 * @param board   the {@link Board} in which to draw
	 * @param target  the corresponding {@link Target}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DTarget(Board board, Target target) throws IOException {
		super(board,target);
		image.setImage("resources/target.png");
		image.dye(target.getColor());
	}
}
