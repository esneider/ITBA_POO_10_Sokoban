package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.event.EventDispatcher;

public abstract class GameElement extends EventDispatcher{

	protected Position pos;

	public GameElement(Position pos) {
		this.pos = pos;
	}

	public Position getPos() {
		return pos;
	}

	public void generateEvent() {
		generateEvent(pos);
	}
}
