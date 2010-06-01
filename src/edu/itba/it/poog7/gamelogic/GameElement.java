package edu.itba.it.poog7.gamelogic;

public abstract class GameElement {

	protected Position pos;
	
	public GameElement(Position pos) {
		this.pos = pos;
	}

	public Position getPos() {
		return pos;
	}
}
