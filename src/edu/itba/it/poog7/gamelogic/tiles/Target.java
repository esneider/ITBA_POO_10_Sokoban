/**
 * 
 */
package edu.itba.it.poog7.gamelogic.tiles;

import java.awt.Color;

/**
 * @author champo
 *
 */
public class Target extends Tile {

	Color color;
	
	public Target(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
