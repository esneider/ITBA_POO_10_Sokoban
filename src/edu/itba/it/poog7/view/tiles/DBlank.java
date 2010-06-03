package edu.itba.it.poog7.view.tiles;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic_.tiles.Blank;
import edu.itba.it.poog7.view.*;

/**
 * {@link DrawableElement} corresponding to {@link Blank}
 * 
 * @author dario
 *
 */
public class DBlank extends DrawableElement {

	/**
	 * @param board  the {@link Board} in wich to draw
	 * @param blank  the corresponding {@link Blank}
	 * 
	 * @throws IOException if there is an error while opening the image file
	 */
	public DBlank(Board board, Blank blank) throws IOException {
		super(board,blank);
	}
}
