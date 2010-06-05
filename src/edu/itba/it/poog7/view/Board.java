package edu.itba.it.poog7.view;

import edu.itba.it.gui.BoardPanel;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * Wrapper of {@link BoardPanel} that implements Image drawing
 * 
 * @author dario
 *
 */
public class Board extends BoardPanel{

	private static final long serialVersionUID = 1L;

	/**
	 * Instance a new {@link Board}
	 * 
	 * @param rows      number of rows of the board
	 * @param columns   number of columns of the board
	 * @param cellSize  size of each cell
	 */
	Board(int rows, int columns, int cellSize)	{

		super(rows,columns,cellSize);
	}
	
	/**
	 * Draw an {@link Image}
	 * 
	 * @param image  the {@link Image}
	 */
	public void draw(Image image) { 

		if (image == null)
			return;

		Position pos = image.getPosition();

		if (image.getImage() == null) {
			clearImage(pos.getY(), pos.getX());
			return;
		}
		if (image.isTransparent()) {
			appendImage(pos.getY(), pos.getX(), image.getImage());
			return;
		}
		setImage(pos.getY(), pos.getX(), image.getImage());
	}
}
