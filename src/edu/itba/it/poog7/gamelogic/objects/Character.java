package edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;
import edu.itba.it.poog7.gamelogic.objects.event.MoveCharacterEvent;
import edu.itba.it.poog7.gamelogic.tiles.GameTile;

/**
 * A Character that moves through the game and can move other objects.
 *
 */
public class Character extends GameObject {

	/**
	 * Simple constructor that delegates everything to his father.
	 * 
	 * @param pos The initial position.
	 */
	public Character(Position pos) {
		super(pos);
	}
	
	/**
	 * Check for the ability to move into another square. If the square is occupied by a box, there
	 * is still a chance that he can move it.
	 * 
	 * @param state    The current instance of Game where the Character is in.
	 * @param dir      The direction in which to move.
	 */
	public boolean canMove(Game state, Direction dir){
		if (!super.canMove(state, dir)){
			return false;
		}
		GameTile nextTile = state.getTile(pos.getNeighbourPosition(dir));
		if (nextTile.getObject() != null){
			if (nextTile.getObject() instanceof Box){
				return nextTile.getObject().canMove(state, dir);
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return Get the event listener for move.
	 */
	public EventListener getMoveListener() {
		
		return new EventListener() {
			
			@Override
			public void eventTriggered(Event e) {
				
				MoveCharacterEvent event = (MoveCharacterEvent) e;
				Game game = (Game) e.getDispatcher();
				Direction dir = event.getDirection();
				if (canMove(game, dir)) {
					move(game, dir);
				}
			}
		};
	}

	@Override
	public String toString() {
		return pos+","+ElementType.CHARACTER.getInt()+",0,"+RGBColor.black;
	}
}