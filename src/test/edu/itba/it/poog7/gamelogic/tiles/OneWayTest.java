/**
 * 
 */
package test.edu.itba.it.poog7.gamelogic.tiles;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.OneWay;

/**
 * @author jcivile
 *
 */
public class OneWayTest extends TileTest {

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.OneWay#canMoveTo(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveTo() {

		OneWayStub down = new OneWayStub(new Position(0, 0), Direction.DOWN);
		assertTrue(down.canMoveTo(Direction.DOWN));
		assertFalse(down.canMoveTo(Direction.UP));
		assertFalse(down.canMoveTo(Direction.LEFT));
		assertFalse(down.canMoveTo(Direction.RIGHT));
		
		OneWayStub up = new OneWayStub(new Position(0, 0), Direction.UP);
		assertTrue(up.canMoveTo(Direction.UP));
		assertFalse(up.canMoveTo(Direction.DOWN));
		assertFalse(up.canMoveTo(Direction.LEFT));
		assertFalse(up.canMoveTo(Direction.RIGHT));
		
		OneWayStub left = new OneWayStub(new Position(0, 0), Direction.LEFT);
		assertTrue(left.canMoveTo(Direction.LEFT));
		assertFalse(left.canMoveTo(Direction.UP));
		assertFalse(left.canMoveTo(Direction.DOWN));
		assertFalse(left.canMoveTo(Direction.RIGHT));
		
		OneWayStub right = new OneWayStub(new Position(0, 0), Direction.RIGHT);
		assertTrue(right.canMoveTo(Direction.RIGHT));
		assertFalse(right.canMoveTo(Direction.UP));
		assertFalse(right.canMoveTo(Direction.LEFT));
		assertFalse(right.canMoveTo(Direction.DOWN));
		
	}
	
	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.OneWay#canMoveFrom(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveFrom() {
		
		OneWayStub down = new OneWayStub(new Position(0, 0), Direction.DOWN);
		assertTrue(down.canMoveFrom(Direction.DOWN));
		assertFalse(down.canMoveFrom(Direction.UP));
		assertFalse(down.canMoveFrom(Direction.LEFT));
		assertFalse(down.canMoveFrom(Direction.RIGHT));
		
		OneWayStub up = new OneWayStub(new Position(0, 0), Direction.UP);
		assertTrue(up.canMoveFrom(Direction.UP));
		assertFalse(up.canMoveFrom(Direction.DOWN));
		assertFalse(up.canMoveFrom(Direction.LEFT));
		assertFalse(up.canMoveFrom(Direction.RIGHT));
		
		OneWayStub left = new OneWayStub(new Position(0, 0), Direction.LEFT);
		assertTrue(left.canMoveFrom(Direction.LEFT));
		assertFalse(left.canMoveFrom(Direction.UP));
		assertFalse(left.canMoveFrom(Direction.DOWN));
		assertFalse(left.canMoveFrom(Direction.RIGHT));
		
		OneWayStub right = new OneWayStub(new Position(0, 0), Direction.RIGHT);
		assertTrue(right.canMoveFrom(Direction.RIGHT));
		assertFalse(right.canMoveFrom(Direction.UP));
		assertFalse(right.canMoveFrom(Direction.LEFT));
		assertFalse(right.canMoveFrom(Direction.DOWN));
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.OneWay#getDirection()}.
	 */
	public void testGetDirection() {
		
		Direction orientation = Direction.DOWN;
		OneWayStub oneway = new OneWayStub(new Position(0, 0), orientation);
		
		assertTrue(oneway.getDirection() == orientation);
	}

	class OneWayStub extends OneWay {

		public OneWayStub(Position pos, Direction orientation) {
			super(pos, orientation);
		}

		@Override
		public void draw(JPanel panel) {
		}
	}
}