package edu.itba.it.poog7.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import edu.itba.it.poog7.gamelogic.Color;

/**
 * View for the 'Model-View-Controller' pattern
 * 
 * @author dario
 *
 */
public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private Board board;
	private List<DrawableElement> elements = new ArrayList<DrawableElement>();
	
	/**
	 * Instance a {@link View}
	 */
	public View(int rows, int columns, int cellSize, int height, int width) {

		setLayout(null);
		setSize(width, height);
		board = new Board(rows, columns, cellSize );
		board.setBackground( new Color(255,255,255).getColor());
		add(board);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
