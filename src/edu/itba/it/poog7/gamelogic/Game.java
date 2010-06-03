package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.gamelogic.objects.Chaboncitou;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * All information about a level and its state of playing
 * 
 * @author dario
 *
 */
public class Game {
	
	protected Tile [][] tileMatrix;
	Position chaboncitouPos;
	String levelName;
	String levelFileName;
	String userName;
	int numMoves;
	int remainingBoxes;
	int boxesNotMatched;

	/**
	 * Instance a new Game
	 * 
	 * @param name            name of the level
	 * @param userName        name of the User
	 * @param tiles           matrix of tiles (board)
	 * @param chaboncitouPos  position of the chaboncitou
	 * @param numMoves        number of moves already done
	 * @param boxes           number of boxes
	 * @param targets         number of targets
	 */
	public Game(String name, String userName, Tile[][] tiles, Position chaboncitouPos,
						int numMoves, int boxes, int targets){
		this.levelName = name;
		this.userName = userName;
		this.tileMatrix = tiles;
		this.chaboncitouPos = chaboncitouPos;
		this.numMoves = numMoves;
		this.remainingBoxes = boxes;
		this.boxesNotMatched = targets;
	}
	
	/**
	 * Instance a empty game
	 */
	public Game(){
		
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

	/**
	 * Getter for the UserName
	 * 
	 * @return    the name of the player
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Setter for the UserName
	 * 
	 * @return
	 */
	public void getUserName(String userName) {
		this.userName = userName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public String getLevelFileName() {
		return levelFileName;
	}

	public void setLevelFileName(String levelFileName) {
		this.levelFileName = levelFileName;
	}

	public Tile[][] getTileMatrix() {
		return tileMatrix;
	}

	public Position getChaboncitouPos() {
		return chaboncitouPos;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Resize the board. WARNING: This will erase all previous information that the board had.
	 * 
	 * @param width    the new width of the board
	 * @param height   the new height of the board
	 */
	public void setSize(int width, int height) {
		tileMatrix = new Tile[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				tileMatrix[i][j] = new Blank(new Position(i,j));
			}
		}
	}

	public void setTile(Tile got) {
		tileMatrix[got.getPos().getX()][got.getPos().getY()] = got;
	}

	public void setObject(GameObject got) {
		getTile(got.getPos()).setObject(got);
	}

}
