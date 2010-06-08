package edu.itba.it.poog7.gamelogic.exception;

/**
 * In case a file couldn't be saved, this exception is thrown.
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
