/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

/**
 * @author champo
 * 
 */
public enum Direction {
	UP(0), RIGHT(1), DOWN(2), LEFT(3);

	private final int dir;

	Direction(int dir) {
		this.dir = dir;
	}

	public int getInt() {
		return this.dir;
	}

	public static Direction getTurn(int turn) {
		if (turn == 0) {
			return UP;
		}
		if (turn == 1) {
			return RIGHT;
		}
		if (turn == 2) {
			return DOWN;
		}
		if (turn == 3) {
			return LEFT;
		}
		return UP;
	}

}
