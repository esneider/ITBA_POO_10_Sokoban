/**
 * 
 */
package edu.itba.it.poog7.gamelogic.exception;

/**
 * This exception is thrown when a file wasn't loaded correctly or had invalid
 * game data.
 */
public class CouldNotLoadFileException extends Exception {

	private static final long serialVersionUID = 22L;

	String description;

	/**
	 * Construct.
	 * 
	 * @param string
	 *            The exception's message.
	 */
	public CouldNotLoadFileException(String string) {
		this.description = string;
	}
}
