/**
 * 
 */
package edu.itba.it.poog7.gamelogic.exception;

/**
 * @author dario
 * 
 */
public class CouldNotLoadFileException extends Exception {

	private static final long serialVersionUID = 22L;

	String description;

	/**
	 * Construct.
	 * 
	 * @param string
	 *            The exceptions message.
	 */
	public CouldNotLoadFileException(String string) {
		this.description = string;
	}
}
