package edu.itba.it.poog7.gamelogic;

/**
 * @author champo
 * 
 */
public class Position {
	final private int x;
	final private int y;

	/**
	 * Constructor for a new position
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x component of this position
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y component of this position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the neighbor of this position.
	 * 
	 * @param dir
	 *            the {@link Direction} we are looking for
	 * @return a new Position with the {@link Position} of the Neighbor
	 */
	public Position getNeighbourPosition(Direction dir) {

		switch (dir) {
		case DOWN:
			return new Position(x, y + 1);
		case UP:
			return new Position(x, y - 1);
		case LEFT:
			return new Position(x - 1, y);
		case RIGHT:
			return new Position(x + 1, y);
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

	@Override
	public String toString() {
		return y + "," + x;
	}
}