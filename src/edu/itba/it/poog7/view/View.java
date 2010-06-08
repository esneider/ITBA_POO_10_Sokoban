package edu.itba.it.poog7.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import edu.itba.it.poog7.view.tiles.DBlank;

/**
 * A view of the board.
 * 
 */
public class View extends JPanel {

	private static final long serialVersionUID = 1L;

	private Board board;
	private int width, height;
	private List<DrawableElement> elements = new ArrayList<DrawableElement>();

	/**
	 * Instance a {@link View}
	 * 
	 * @param height the amount of columns of the map 
	 * @param width the amount of rows of the map
	 */
	public View(int height, int width) {
		this.height = height;
		this.width = width;
		setSize(width, height);
	}

	/**
	 * Set (or reset if already set) the board settings.
	 * 
	 * @param rows
	 * @param columns
	 * @param cellSize
	 */
	public void setBoardSize(int rows, int columns, int cellSize) {

		if (board != null) {
			remove(board);
		}
		board = new Board(rows, columns, cellSize);
		board.setBackground(Color.white);

		setSize(board.getSize());
		add(board);

		for (DrawableElement e : elements) {
			if (!(e instanceof DBlank)) {
				e.draw();
			}
		}
	}

	/**
	 * Add a {@link DrawableElement}
	 * 
	 * @param element
	 *            the {@link DrawableElement}
	 */
	public void addElement(DrawableElement element) {

		this.elements.add(element);
	}

	/**
	 * Remove a {@link DrawableElement}
	 * 
	 * @param element
	 *            the {@link DrawableElement}
	 */
	public void removeElement(DrawableElement element) {

		this.elements.remove(element);
	}

	/**
	 * @return the associated {@link Board}
	 */
	public Board getBoard() {

		return board;
	}
	
	/**
	 * Erase all the board
	 */
	public void clearImage() {

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board.clearImage(i, j);
			}
		}
	}
}
