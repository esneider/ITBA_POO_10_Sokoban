package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.Drawable;
import edu.itba.it.poog7.gamelogic.objects.Chaboncitou;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

public abstract class LevelState implements Drawable {

	TileMatrix tiles;
//	Tiles [][] tiles;
	Chaboncitou chaboncitou;
	String levelName;
	int numMoves;
	int remainingBoxes;
	int boxesNotMatched;

	public GameState getState() {
		if (chaboncitou == null) {
			return GameState.GAMEOVER;
		}
		if (remainingBoxes == 0 && boxesNotMatched == 0) {
			return GameState.FINISHED;
		}
		return GameState.PLAYING;
	}

	public void moveChaboncitou( Direction dir ) {
		if (chaboncitou.canMove(this,dir)) {
			chaboncitou.move(this, dir);
		}
	}
	
	public Tile getTile( Position pos ) {
		return tiles.getTile(pos);
//		return tiles[pos.getX()][pos.getY()];
	}

	public int getNumMoves() {
		return numMoves;
	}

	public void incNumMoves(int numMoves) {
		this.numMoves++;
	}

	public void decRemainingBoxes(int remainingBoxes) {
		this.remainingBoxes--;
	}

	public void incRemainingBoxes(int remainingBoxes) {
		this.remainingBoxes++;
	}

	public void incBoxesMatched(int boxesNotMatched) {
		this.boxesNotMatched--;
	}

	public void decBoxesMatched(int boxesNotMatched) {
		this.boxesNotMatched++;
	}

}
