package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.LevelElement;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.LevelObject;

public abstract class Tile extends LevelElement {
	
	LevelObject object;
	
	public Tile(Position pos) {
		super(pos);
	}

	public LevelObject getObject() {
		return object;
	}
	
	public void setObject(LevelObject object) {
		this.object = object;
	}
	
	public abstract boolean canMoveTo(Direction dir);
	
	public abstract boolean canMoveFrom(Direction dir);
}