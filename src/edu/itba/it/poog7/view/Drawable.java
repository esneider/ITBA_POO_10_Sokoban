package edu.itba.it.poog7.view;

import edu.itba.it.poog7.gamelogic.Position;


/**
 * Interface for backend objects that can be drawn in screen. 
 * 
 * @author: eordano
 */

public interface Drawable {

	public Image getImage();

	public Position getPosition();

}
