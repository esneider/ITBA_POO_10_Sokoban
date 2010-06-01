package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.Drawable;
import edu.itba.it.poog7.gamelogic.objects.Chaboncitou;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * All information about a level and its state of playing
 * 
 * @author dario
 *
 */
public abstract class LevelState implements Drawable, Cloneable {
	
	protected Tile [][] tileMatrix;
	Position chaboncitouPos;
	String levelName;
	int numMoves;
	int remainingBoxes;
	int boxesNotMatched;

	/**
	 * Instance a new LevelState
	 * 
	 * @param name            name of the level
	 * @param tiles           matrix of tiles (board)
	 * @param chaboncitouPos  position of the chaboncitou
	 * @param numMoves        number of moves already done
	 * @param boxes           number of boxes
	 * @param targets         number of targets
	 */
	public LevelState(String name, Tile[][] tiles, Position chaboncitouPos,
						int numMoves, int boxes, int targets){
		this.levelName = name;
		this.tileMatrix = tiles;
		this.chaboncitouPos = chaboncitouPos;
		this.numMoves = numMoves;
		this.remainingBoxes = boxes;
		this.boxesNotMatched = targets;
	}

	/**
	 * Get the state of the game
	 * 
	 * @see GameState
	 * 
	 * @return the state of the game
	 */
	public GameState getState() {
		if (chaboncitouPos == null) {
			return GameState.GAMEOVER;
		}
		if (remainingBoxes == 0 && boxesNotMatched == 0) {
			return GameState.FINISHED;
		}
		return GameState.PLAYING;
	}

	/**
	 * Move the {@link Chaboncitou}
	 * 
	 * @param dir  direction of movement
	 */
	public void moveChaboncitou( Direction dir ) {
		Chaboncitou chaboncitou = (Chaboncitou)getTile(chaboncitouPos).getObject();
		
		if (chaboncitou.canMove(this,dir)) {
			chaboncitou.move(this, dir);
		}
	}

	/**
	 * Get the {@link Tile} in a given position
	 * 
	 * @param pos  the position
	 * @return the {@link Tile}
	 */
	public Tile getTile( Position pos ) {
		return tileMatrix[pos.getX()][pos.getY()];
	}

	/**
	 * @return the number of moves since beginning
	 */
	public int getNumMoves() {
		return numMoves;
	}

	/**
	 * Increment by one the number of moves since beginning
	 */
	public void incNumMoves() {
		this.numMoves++;
	}

	/**
	 * Decrement by one the number of remaining boxes
	 */
	public void decRemainingBoxes() {
		this.remainingBoxes--;
	}

	/**
	 * Increment by one the number of remaining boxes
	 */
	public void incRemainingBoxes() {
		this.remainingBoxes++;
	}

	/**
	 * Increment by one the number of matched boxes
	 */
	public void incBoxesMatched() {
		this.boxesNotMatched--;
	}

	/**
	 * Decrement by one the number of matched boxes
	 */
	public void decBoxesMatched() {
		this.boxesNotMatched++;
	}

	/**
	 * @return the level name
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * Set the reference to the {@link Chaboncitou} position
	 * 
	 * @param pos the position
	 */
	public void setChaboncitouPos(Position pos) {
		this.chaboncitouPos = pos;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		LevelState o = (LevelState)super.clone();
		o.tileMatrix = (Tile[][])o.tileMatrix.clone();
		// ISSUE: chaboncitou is gonna be cloned twice if cloned here but, if not, reference is gonna be wrong :S
		return o;
	}
}
