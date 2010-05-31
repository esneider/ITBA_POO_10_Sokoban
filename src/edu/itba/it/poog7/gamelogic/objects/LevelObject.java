package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.LevelElement;
import edu.itba.it.poog7.gamelogic.LevelState;
import edu.itba.it.poog7.gamelogic.Position;

public abstract class LevelObject extends LevelElement {
	
	public LevelObject(Position pos) {
		super(pos);
	}

	public boolean canMove(Direction dir){
		return false;
	}
	
	public void move(LevelState state, Direction dir){
		return;
	}
	
	public void destructor(){
		return;
	}
}
