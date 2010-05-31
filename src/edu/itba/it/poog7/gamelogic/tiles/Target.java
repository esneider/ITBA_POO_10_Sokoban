/**
 * 
 */
package edu.itba.it.poog7.gamelogic.tiles;

import java.awt.Color;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * @author champo
 *
 */
public abstract class Target extends Tile {

	Color color;
	
	public Target(Position pos, Color color) {
		super(pos);
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public boolean canMoveFrom(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMoveTo(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}
}
