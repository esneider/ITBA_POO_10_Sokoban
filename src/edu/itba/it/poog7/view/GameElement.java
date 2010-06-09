package edu.itba.it.poog7.view;

import edu.itba.it.poog7.gamelogic.Position;


/**
 * Abstract class to draw things representable in a tile on screen.
 * 
 *
 */
public abstract class GameElement implements Drawable {
	Image img;
	Position pos;
	
	public Position getPosition() {
		return pos;
	}

	public Image getImage() {
		return getImage();
	}

}
