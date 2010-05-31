package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.Drawable;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

public abstract class TileMatrix implements Drawable {

	Tile [][] tiles;

	public Tile getTile(Position pos) {
		return tiles[pos.getX()][pos.getY()];
	}

}
