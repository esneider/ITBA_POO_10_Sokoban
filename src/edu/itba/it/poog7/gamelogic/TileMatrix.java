package edu.itba.it.poog7.gamelogic;

import javax.swing.JPanel;

import edu.itba.it.poog7.Drawable;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

public abstract class TileMatrix implements Drawable {
	private Tile matrix[][];
	
	@Override
	public void draw(JPanel panel) {
		// TODO Auto-generated method stub	
	}
	
	public Tile getTile(Position pos){
		return getMatrix()[pos.getX()][pos.getY()];
	}

	public void setMatrix(Tile matrix[][]) {
		this.matrix = matrix;
	}

	public Tile[][] getMatrix() {
		return matrix;
	}

	
}
