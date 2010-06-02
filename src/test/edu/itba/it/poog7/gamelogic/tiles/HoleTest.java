/**
 * 
 */
package test.edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Hole;

/**
 * @author champo
 *
 */
public class HoleTest extends TileTest {
	
	private Hole hole;

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		
		hole = new Hole(new Position(0, 0));
		super.setUp();
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Hole#canMoveTo(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveTo() {
		
		assertTrue(hole.canMoveTo(Direction.DOWN));
		assertTrue(hole.canMoveTo(Direction.UP));
		assertTrue(hole.canMoveTo(Direction.LEFT));
		assertTrue(hole.canMoveTo(Direction.RIGHT));
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Hole#canMoveFrom(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveFrom() {
		
		assertTrue(hole.canMoveFrom(Direction.DOWN));
		assertTrue(hole.canMoveFrom(Direction.UP));
		assertTrue(hole.canMoveFrom(Direction.LEFT));
		assertTrue(hole.canMoveFrom(Direction.RIGHT));
	}

}
