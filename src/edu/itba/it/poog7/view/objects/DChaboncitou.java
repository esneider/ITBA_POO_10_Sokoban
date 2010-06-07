package edu.itba.it.poog7.view.objects;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.objects.Chaboncitou;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Chaboncitou}
 * 
 * @author dario
 *
 */
public class DChaboncitou extends DObject {

	/**
	 * @param myView        the {@link Board} in which to draw
	 * @param chaboncitou  the corresponding {@link Chaboncitou}
	 * 
	 * @throws IOException  if there is an error while opening the image file
	 */
	public DChaboncitou(View myView, Chaboncitou chaboncitou) throws IOException {
		super(myView, chaboncitou);
		image.setImage("resources/smile.gif");
		image.setTransparent(true);
	}
}
