package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.LevelElement;

public class LevelObject extends LevelElement {
	
	public boolean canMove(Direction dir){
		return false;
	}
	
	public void move(Direction dir){
		return;
	}
	
	public void destructor(){
		return;
	}
}
