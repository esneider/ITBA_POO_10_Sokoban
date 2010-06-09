package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventDispatcher;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.event.GameFinishedEvent;
import edu.itba.it.poog7.gamelogic.event.GameOverEvent;
import edu.itba.it.poog7.gamelogic.event.ScoreChangedEvent;
import edu.itba.it.poog7.gamelogic.objects.Character;
import edu.itba.it.poog7.gamelogic.objects.event.MoveCharacterEvent;
import edu.itba.it.poog7.gamelogic.tiles.GameTile;

/**
 * Model for the 'Model-View-Controller' pattern <br>
 * All information about a game and its state of playing
 * 
 */
public class Game extends EventDispatcher {

	protected GameTile[][] tileMatrix;
	protected String levelName;
	protected String levelFileName;
	protected String userName;
	protected int numMoves;
	protected int remainingBoxes;
	protected int boxesNotMatched;

	/**
	 * Instance a empty game
	 * 
	 */
	public Game() {

	}

	/**
	 * Initialize a new Game
	 * 
	 * @param name
	 *            name of the level
	 * @param fileName
	 *            name of the level file
	 * @param userName
	 *            name of the User
	 * @param tiles
	 *            matrix of tiles (board)
	 * @param numMoves
	 *            number of moves already done
	 * @param boxes
	 *            number of boxes
	 * @param targets
	 *            number of targets
	 */
	public void init(String name, String fileName, String userName,
			GameTile[][] tiles, int numMoves, int boxes, int targets) {

		this.levelName = name;
		this.levelFileName = fileName;
		this.userName = userName;
		this.tileMatrix = tiles;
		this.numMoves = numMoves;
		this.remainingBoxes = boxes;
		this.boxesNotMatched = targets;
	}

	/**
	 * Move the {@link Character}
	 * 
	 * @param dir
	 *            direction of movement
	 */
	public void moveCharacter(Direction dir) {

		generateEvent(new MoveCharacterEvent(this, dir));
	}

	/**
	 * Get the {@link GameTile} in a given position
	 * 
	 * @param pos
	 *            the position
	 * @return the {@link GameTile}
	 */
	public GameTile getTile(Position pos) {

		return tileMatrix[pos.getX()][pos.getY()];
	}

	/**
	 * Get an event listener for when character moves.
	 * 
	 * @return the event listener.
	 */
	public EventListener getCharacterMovedListener() {

		return new EventListener() {
			@Override
			public void eventTriggered(Event e) {
				numMoves++;
				generateEvent(new ScoreChangedEvent(Game.this));
			}
		};
	}

	/**
	 * Get an event listener for when a target matches with a box.
	 * 
	 * @return the event listener.
	 */
	public EventListener getTargetMatchedListener() {

		return new EventListener() {
			@Override
			public void eventTriggered(Event e) {
				remainingBoxes--;
				boxesNotMatched--;

				if (remainingBoxes == 0 && boxesNotMatched == 0){
					generateEvent(new GameFinishedEvent(Game.this));
				}
			}
		};
	}

	/**
	 * Get an event listener for when a target lost its matching box.
	 * 
	 * @return the event listener.
	 */
	public EventListener getTargetUnmatchedListener() {

		return new EventListener() {
			@Override
			public void eventTriggered(Event e) {
				remainingBoxes++;
				boxesNotMatched++;
			}
		};
	}

	/**
	 * Get an event listener for when a box is destroyed.
	 * 
	 * @return The event listener.
	 */
	public EventListener getBoxDestroyedListener() {

		return new EventListener() {
			@Override
			public void eventTriggered(Event e) {
				remainingBoxes--;
			}
		};
	}

	/**
	 * Get an event listener for when a character is destroyed.
	 * 
	 * @return The event listener.
	 */
	public EventListener getCharacterDestroyedListener() {

		return new EventListener() {
			@Override
			public void eventTriggered(Event e) {
				generateEvent(new GameOverEvent(Game.this));
			}
		};
	}

	/**
	 * @return the number of moves since beginning
	 */
	public int getNumMoves() {

		return numMoves;
	}

	/**
	 * @return the level name
	 */
	public String getLevelName() {

		return levelName;
	}

	/**
	 * Getter for the UserName
	 * 
	 * @return the name of the player
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Get name of the level file.
	 * 
	 * @return the file name.
	 */
	public String getLevelFileName() {
		return levelFileName;
	}

	/**
	 * Get the tile matrix.
	 * 
	 * @return the tile matrix.
	 */
	public GameTile[][] getTileMatrix() {
		return tileMatrix;
	}

	/**
	 * Getter for the Width of the game
	 * 
	 * @return the amount of columns of the game
	 */
	public int getWidth() {
		return tileMatrix.length;
	}

	/**
	 * Getter for the height of the game
	 * 
	 * @return the amount of rows of the game
	 */
	public int getHeight() {
		return tileMatrix.length == 0 ? 0 : tileMatrix[0].length;
	}
}
