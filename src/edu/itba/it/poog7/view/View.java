package edu.itba.it.poog7.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.RGBColor;
import edu.itba.it.poog7.view.tiles.DBlank;

/**
 * View for the 'Model-View-Controller' pattern
 * 
 * @author dario
 *
 */
public class View extends JPanel {

	private static final long serialVersionUID = 1L;

	private Board board;
	private List<DrawableElement> elements = new ArrayList<DrawableElement>();
	
	/**
	 * Instance a {@link View}
	 */
	public View(int height, int width) {

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
		board = new Board(rows, columns, cellSize );
		board.setBackground( new RGBColor(255,255,255).getColor());
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
	 * @param element  the {@link DrawableElement}
	 */
	public void addElement(DrawableElement element) {

		this.elements.add(element);
	}

	/**
	 * Remove a {@link DrawableElement}
	 * 
	 * @param element  the {@link DrawableElement}
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
}
