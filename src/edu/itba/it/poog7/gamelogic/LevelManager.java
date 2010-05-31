/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

import edu.itba.it.poog7.gamelogic.objects.Box; 
import edu.itba.it.poog7.gamelogic.objects.Chaboncitou;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.OneWay;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.gamelogic.tiles.Wall;

public abstract class LevelManager {

	private LevelState current;
	
	public LevelState getCurrent() {
		return current; // clone()
	}

	public LevelState getNext() {
		// TODO: stub
		return null;
	}

	public LevelState load(String name) {
		// TODO: stub
		return null;
	}
	
	public void save(LevelState level) {
		
	}
	
	public abstract Chaboncitou newChaboncitou();
	
	public abstract Box newBox();
	
	public abstract Wall newWall();
	
	public abstract Target newTarget();
	
	public abstract OneWay newOneWay();
	
	public abstract Blank newBlank();
}
