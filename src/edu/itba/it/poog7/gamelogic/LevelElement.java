package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.Drawable;

public abstract class LevelElement implements Drawable, Cloneable {

	protected Position pos;
	
	public LevelElement(Position pos) {
		this.pos = pos;
	}

	public Position getPos() {
		return pos;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		LevelElement o = (LevelElement)super.clone();
		o.pos = (Position)o.pos.clone();
		return o;
	}
}
