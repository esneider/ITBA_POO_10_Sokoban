/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

/**
 * A Direction enumerator, to distinguish between the four basic directions that
 * we use: up, down, left, right
 */
public enum Direction {
	UP(0), RIGHT(1), DOWN(2), LEFT(3);

	private final int dir;

	/**
	 * @param dir
	 *            The integer representing the direction.
	 */
	Direction(int dir) {
		this.dir = dir;
	}

	/**
	 * @return The integer representing the direction.
	 */
	public int getInt() {
		return this.dir;
	}

	/**
	 * Get a direction given its integer form.
	 * 
	 * @param turn
	 *            The integer to transform.
	 * @return the direction that represents the integer.
	 */
	public static Direction getTurn(int turn) {
		
		switch (turn) {
			case 0:	return UP;
			case 1: return RIGHT;
			case 2: return DOWN;
			default: return LEFT;
		}
	}
}
