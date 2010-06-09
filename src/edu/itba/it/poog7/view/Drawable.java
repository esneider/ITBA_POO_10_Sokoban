package edu.itba.it.poog7.view;

import edu.itba.it.poog7.gamelogic.Position;


/**
 * Interface for backend objects that can be drawn in screen. 
 * 
 */

public interface Drawable {

	/**
	 * Get the image that represents this object.
	 * 
	 * @return The image.
	 */
	public Image getImage();

	/**
	 * Get the position this object is meant to be drawn in.
	 * @return The position.
	 */
	public Position getPosition();

}
