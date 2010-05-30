/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

/**
 * @author champo
 *
 */
public class Position {

	final private int x;
	
	final private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Position getNeighbourPosition(Direction dir) {
		
	}
}
