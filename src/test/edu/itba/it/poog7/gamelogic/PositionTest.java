/**
 * 
 */
package test.edu.itba.it.poog7.gamelogic;

import junit.framework.TestCase;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;

/**
 * @author champo
 *
 */
public class PositionTest extends TestCase {

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.Position#Position(int, int)}.
	 */
	public void testPosition() {
		
		new Position(0, 0);
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.Position#getX()}.
	 */
	public void testGetX() {
		
		int x = 4;
		Position position = new Position(x, 0);
		
		assertEquals(x, position.getX());
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.Position#getY()}.
	 */
	public void testGetY() {
		
		int y = -34;
		Position position = new Position(0, y);
		
		assertEquals(y, position.getY());
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.Position#getNeighbourPosition(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testGetNeighbourPosition() {
		
		Position pos = new Position(0, 0);
		
		assertEquals(pos, pos.getNeighbourPosition(Direction.DOWN).getNeighbourPosition(Direction.UP));
		
		Position result;
		
		//TODO: This just doesnt feel right
		result = pos.getNeighbourPosition(Direction.UP);
		assertEquals(pos.getX(), result.getX());
		assertFalse(pos.getY() == result.getY());
		
		result = pos.getNeighbourPosition(Direction.DOWN);
		assertEquals(pos.getX(), result.getX());
		assertFalse(pos.getY() == result.getY());
		
		result = pos.getNeighbourPosition(Direction.LEFT);
		assertFalse(pos.getX() == result.getX());
		assertEquals(pos.getY(), result.getY());
		
		result = pos.getNeighbourPosition(Direction.RIGHT);
		assertFalse(pos.getX() == result.getX());
		assertEquals(pos.getY(), result.getY());
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.Position#equals(Object)}.
	 */
	public void testEquals() {
		
		Position first = new Position(0, 0);
		assertTrue(first.equals(new Position(0, 0)));
		assertFalse(first.equals(new Position(1, 1)));
		assertFalse(first.equals(new Position(0, 1)));
	}

}
