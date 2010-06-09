package edu.itba.it.poog7.gamelogic;

/**
 * This enumerator is used when reading/saving files. It matches the integers
 * read from the file with the elements the game can have.
 * 
 */
public enum ElementType {
	CHARACTER(1), BOX(2), TARGET(3), WALL(4), HOLE(5), ONEWAY(6), BLANK(7);

	private final int dir;

	/**
	 * Instance a new element type enum.
	 * 
	 * @param dir A value associated with the element.
	 */
	private ElementType(int dir) {
		this.dir = dir;
	}

	/**
	 * Get the related integer asociated with a Element
	 * 
	 * @return a integer that represents the Element
	 */
	public int getInt() {
		return this.dir;
	}

	/**
	 * Convert from integer to this enumerator
	 * 
	 * @param type
	 *            the integer related to a element
	 * @return the enum value of the element
	 */
	public static ElementType getType(int type) {

		switch (type) {
		case 1:
			return CHARACTER;
		case 2:
			return BOX;
		case 3:
			return TARGET;
		case 4:
			return WALL;
		case 5:
			return HOLE;
		case 6:
			return ONEWAY;
		default:
			return BLANK;
		}
	}
}
