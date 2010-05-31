package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.Drawable;

public abstract class LevelElement implements Drawable {

	protected Position pos;
	
	public LevelElement(Position pos) {
		this.pos = pos;
	}

	public Position getPos() {
		return pos;
	}
}
