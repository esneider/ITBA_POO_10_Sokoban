/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

/**
 * A Direction enumerator, to distinguish between the four basic directions that
 * we use: up, down, left, right
 */
public enum Direction {
	UP(2), RIGHT(3), DOWN(0), LEFT(1), INVALID(-1);

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
			case 0:	return DOWN;
			case 1: return LEFT;
			case 2: return UP;
			case 3: return RIGHT;
			default: return INVALID;
		}
	}
}
