/**
 * 
 */
package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;

/**
 * @author champo
 *
 */
public class OneWay extends Tile {
	
	Direction dir;

	public OneWay(Direction dir) {
		super();
		this.dir = dir;
	}
	
	public Direction getDir() {
		return dir;
	}
}
