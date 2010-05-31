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
		
		Position ret;
		switch (dir) {
		case DOWN:
			ret = new Position(x+1, y);
			break;
			
		case UP:
			ret = new Position(x-1, y);
			break;
		
		case LEFT:
			ret = new Position(x, y-1);
			break;
		
		case RIGHT:
			ret = new Position(x, y+1);
			break;

		default:
			ret = new Position(x, y);
			break;
		}
		
		return ret;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Position) {
			
			Position b = (Position) obj;
			return b.getX() == x && b.getY() == y;
		} else {
			
			return false;
		}
	}
}