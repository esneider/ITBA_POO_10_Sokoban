package edu.itba.it.poog7.view;

import java.io.IOException;

import edu.itba.it.poog7.gamelogic_.Color;
import edu.itba.it.poog7.gamelogic_.Position;

import edu.itba.it.gui.ImageUtils;


/**
 * Wrapper of {@link ImageUtils}
 * 
 * @author dario
 *
 */
public class Image {

	private java.awt.Image image;
	private Position pos;
	private boolean transparent;

	/**
	 * Instance a new {@link Image}
	 *
	 * @param pos  position of the image
	 */
	Image(Position pos) {

		this.pos = pos;
		this.image = null;
		this.transparent = false;
	}

	/**
	 * Load an image from a file
	 * 
	 * @param name  name of the image file
	 * 
	 * @throws IOException  if there is an error while opening the image file
	 */
	public void setImage(String name) throws IOException {

		this.image = ImageUtils.loadImage(name);
	}

	/**
	 * Get the image in the proper format
	 * 
	 * @return the {@link java.awt.Image}
	 */
	public java.awt.Image getImage() {

		return image;
	}

	/**
	 * Change the hue of the image
	 * 
	 * @param color  the target {@link Color}
	 */
	public void dye(Color color) {

		image = ImageUtils.dye(image, color.getColor());
	}

	/**
	 * Rotate the image
	 * 
	 * @param times  times that the image is rotated 90 degrees clockwise  
	 */
	public void rotate(int times) {

		image = ImageUtils.rotateImage(image, times);
	}

	/**
	 * Increase image brightness
	 */
	public void increaseBrightness() {

		image = ImageUtils.increaseBrightness(image);
	}

	/**
	 * @return whether the image background is transparent
	 */
	public boolean isTransparent() {

		return transparent;
	}

	/**
	 * @param transparent  set transparency to transparent
	 */
	public void setTransparent(boolean transparent) {

		this.transparent = transparent;
	}

	/**
	 * Get the image {@link Position}
	 * 
	 * @return the {@link Position}
	 */
	public Position getPos() {

		return pos;
	}

	/**
	 * Set the image {@link Position}
	 * 
	 * @param pos  the {@link Position}
	 */
	public void setPos(Position pos) {

		this.pos = pos;
	}

}
