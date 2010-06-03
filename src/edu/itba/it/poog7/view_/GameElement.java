package edu.itba.it.poog7.view_;

import edu.itba.it.poog7.gamelogic_.Position;

/**
 * Abstract class to draw things representable in a tile on screen.
 * 
 * @author eordano
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
