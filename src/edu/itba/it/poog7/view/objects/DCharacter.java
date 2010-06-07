package edu.itba.it.poog7.view.objects;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic.objects.Character;
import edu.itba.it.poog7.view.Board;
import edu.itba.it.poog7.view.DrawableElement;
import edu.itba.it.poog7.view.View;

/**
 * {@link DrawableElement} corresponding to {@link Character}
 * 
 * @author dario
 *
 */
public class DCharacter extends DObject {

	/**
	 * @param myView        the {@link Board} in which to draw
	 * @param character  the corresponding {@link Character}
	 * 
	 * @throws IOException  if there is an error while opening the image file
	 */
	public DCharacter(View myView, Character character) throws IOException {
		super(myView, character);
		image.setImage("resources/smile.gif");
		image.setTransparent(true);
	}
}
