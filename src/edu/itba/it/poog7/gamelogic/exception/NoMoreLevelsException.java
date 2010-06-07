/**
 * 
 */
package edu.itba.it.poog7.gamelogic.exception;

/**
 * @author dario
 *
 */
public class NoMoreLevelsException extends Exception {

	private static final long serialVersionUID = 23L;
	
	public NoMoreLevelsException() {
		super();
	}
	
	public NoMoreLevelsException(String message) {
		super(message);
	}
}
