package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.Drawable;

public abstract class LevelElement implements Drawable {

	Position pos;
	
	public LevelElement(Position pos) {
		
		this.pos = pos;
	}
}
