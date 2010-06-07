package edu.itba.it.poog7.gamelogic.exception;

/**
 * @author dario
 * 
 */
public class CouldNotSaveFileException extends Exception {

	private static final long serialVersionUID = 25L;

	/**
	 * @param msg
	 *            The exceptions message.
	 */
	public CouldNotSaveFileException(String msg) {
		super(msg);
	}
}
