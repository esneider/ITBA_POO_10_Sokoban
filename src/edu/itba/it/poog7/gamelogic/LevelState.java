package edu.itba.it.poog7.gamelogic;

import javax.swing.JPanel;

import edu.itba.it.poog7.Drawable;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

public class LevelState implements Drawable {
	private TileMatrix matrix;
	
	@Override
	public void draw(JPanel panel) {
		// TODO Auto-generated method stub
		
	}
	
	public Tile getTile(Position pos){
		return matrix.getTile(pos);
	}

	public void setMatrix(TileMatrix matrix) {
		this.matrix = matrix;
	}

	public TileMatrix getMatrix() {
		return matrix;
	}
	
}
