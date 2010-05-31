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
public class BlankTest extends TileTest {

	private Blank blank;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception {

		blank = new Blank(new Position(0, 0)) {
			
			@Override
			public void draw(JPanel panel) {
			}
		};
		super.setUp();
	}
	
	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Blank#canMoveTo(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveTo() {
		
		assertTrue(blank.canMoveTo(Direction.DOWN));
		assertTrue(blank.canMoveTo(Direction.UP));
		assertTrue(blank.canMoveTo(Direction.LEFT));
		assertTrue(blank.canMoveTo(Direction.RIGHT));
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Blank#canMoveFrom(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveFrom() {
		
		assertTrue(blank.canMoveFrom(Direction.DOWN));
		assertTrue(blank.canMoveFrom(Direction.UP));
		assertTrue(blank.canMoveFrom(Direction.LEFT));
		assertTrue(blank.canMoveFrom(Direction.RIGHT));
	}
}
