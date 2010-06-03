package edu.itba.it.poog7.gamelogic_;

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
		
		switch (dir) {
			case DOWN:
				return new Position(x, y+1);
			case UP:
				return new Position(x, y-1);
			case LEFT:
				return new Position(x-1, y);
			case RIGHT:
				return new Position(x+1, y);
			default:
				return new Position(x, y);
			}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Position) {

			Position p = (Position) obj;
			return p.getX() == x && p.getY() == y;
		} else {
			
			return false;
		}
	}
}