package edu.itba.it.poog7.gamelogic.exceptions;

public class GameOverException extends Exception {

	/**
	 * This is the exception thrown when the user gets into a hole
	 */
	private static final long serialVersionUID = 31415L;

	public GameOverException() {
		// TODO Auto-generated constructor stub
	}

	public GameOverException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GameOverException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public GameOverException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
