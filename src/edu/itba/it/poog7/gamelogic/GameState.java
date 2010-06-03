package edu.itba.it.poog7.gamelogic;

/**
 *  * eordano says: I Think This should be deprecated. The app doen't have to check all the time for a game over,
 * or game finished: instead, a event should be triggered.
 * <br>
 * <br>
 * Possible states of the game:
 * <ul>
 *   <li> PLAYING
 *   <li> GAMEOVER
 *   <li> FINISHED
 * </ul>
 * 
 * @author dario
 *
 */
public enum GameState {
	
	PLAYING,
	GAMEOVER,
	FINISHED
}
