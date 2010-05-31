/**
 * 
 */
package edu.itba.it.poog7.gamelogic.tiles;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * @author champo
 *
 */
public class WallTest extends TileTest {

	private Wall wall;

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		
		wall = new Wall(new Position(0, 0)) {
			
			@Override
			public void draw(JPanel panel) {
				// TODO Auto-generated method stub
				
			}
		};
		
		super.setUp();
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Wall#canMoveTo(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveTo() {
		
		assertFalse(wall.canMoveTo(Direction.DOWN));
		assertFalse(wall.canMoveTo(Direction.LEFT));
		assertFalse(wall.canMoveTo(Direction.RIGHT));
		assertFalse(wall.canMoveTo(Direction.UP));
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Wall#canMoveFrom(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveFrom() {
		
		assertFalse(wall.canMoveFrom(Direction.DOWN));
		assertFalse(wall.canMoveFrom(Direction.LEFT));
		assertFalse(wall.canMoveFrom(Direction.RIGHT));
		assertFalse(wall.canMoveFrom(Direction.UP));
	}

}
