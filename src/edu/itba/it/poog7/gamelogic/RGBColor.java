package edu.itba.it.poog7.gamelogic;

import java.awt.Color;

/**
 * Representation of a color by simply using it's three components: Red, Green,
 * Blue
 */
public class RGBColor {

	public final static RGBColor black = new RGBColor(0, 0, 0);
	
	private final int r;
	private final int g;
	private final int b;

	/**
	 * Constructor for a new Color
	 * 
	 * @param r the red component
	 * @param g the green component
	 * @param b the blue component
	 */
	public RGBColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * Getter for the red component.
	 * @return the red component of this {@link RGBColor}
	 */
	public int getR() {
		return r;
	}

	/**
	 * Getter for the green component.
	 * @return the green component of this {@link RGBColor}
	 */
	public int getG() {
		return g;
	}

	/**
	 * Getter for the blue component.
	 * @return the blue component of this {@link RGBColor}
	 */
	public int getB() {
		return b;
	}

	/**
	 * Translate a RGB color into a Java's AWT {@link Color}.
	 * @return the equivalent {@link Color}
	 */
	public java.awt.Color getColor() {

		return new java.awt.Color(r, g, b);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof RGBColor) {

			RGBColor color = (RGBColor) obj;
			return r == color.r && g == color.g && b == color.b;
		} else {

			return false;
		}
	}

	@Override
	public String toString() {
		return r+","+g+","+b;
	}
}
