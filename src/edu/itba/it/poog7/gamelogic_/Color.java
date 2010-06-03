package edu.itba.it.poog7.gamelogic_;

public class Color {

	private final int r;
	private final int g;
	private final int b;
	
	public Color(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}
	
	public java.awt.Color getColor() {

		return new java.awt.Color(r, g, b);
	}
}
