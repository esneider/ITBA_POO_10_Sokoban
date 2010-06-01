package edu.itba.it.poog7;

import java.awt.Color;
import java.io.IOException;

import edu.itba.it.poog7.gamelogic.Position;
import gui.ImageUtils;


/**
 * Image class that is a wrapper of {@link ImageUtils}
 * 
 * @author dario
 *
 */
public class Image {

	java.awt.Image image;
	Position pos;
	Color color;
	boolean transparent;

	
	/**
	 * Instance a new Image
	 *
	 * @param pos  position of the Image
	 */
	Image(Position pos) {
		this.pos = pos;
		this.image = null;
		this.color = null;
		this.transparent = false;
	}

	/**
	 * Load an image from a file
	 * 
	 * @param name  name of the image file
	 */
	public void setImage(String name) {
		try {
			this.image = ImageUtils.loadImage(name);
		} catch (IOException e) {
		}
	}

	/**
	 * Get the image in the proper format
	 * 
	 * @return the image
	 */
	public java.awt.Image getImage() {
		return image;
	}

	/**
	 * Change the hue of the image
	 * 
	 * @param color  the target color
	 */
	public void dye(Color color) {
		image = ImageUtils.dye(image, color);
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
	 * Get the image position
	 * 
	 * @return the position
	 */
	public Position getPos() {
		return pos;
	}

}
