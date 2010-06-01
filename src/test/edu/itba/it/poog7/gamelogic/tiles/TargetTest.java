/**
 * 
 */
package test.edu.itba.it.poog7.gamelogic.tiles;

import java.awt.Color;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.tiles.Target;

/**
 * @author champo
 *
 */
public class TargetTest extends TileTest {

	private Target target;
	private Color black;

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		black = new Color(0, 0, 0);
		target = new Target(new Position(0, 0), black) {
			
			@Override
			public void draw(JPanel panel) {
				// TODO Auto-generated method stub
				
			}
		};
		super.setUp();
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Target#canMoveTo(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveTo() {
		
		assertTrue(target.canMoveFrom(Direction.DOWN));
		assertTrue(target.canMoveFrom(Direction.UP));
		assertTrue(target.canMoveFrom(Direction.LEFT));
		assertTrue(target.canMoveFrom(Direction.RIGHT));
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Target#canMoveFrom(edu.itba.it.poog7.gamelogic.Direction)}.
	 */
	public void testCanMoveFrom() {
		
		assertTrue(target.canMoveFrom(Direction.DOWN));
		assertTrue(target.canMoveFrom(Direction.UP));
		assertTrue(target.canMoveFrom(Direction.LEFT));
		assertTrue(target.canMoveFrom(Direction.RIGHT));
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Target#getColor()}.
	 */
	public void testGetColor() {
		
		Color white = new Color(255, 255, 255);
		
		assertNotNull(target.getColor());
		assertTrue(target.getColor().equals(black));
		assertFalse(target.getColor().equals(white));
		
		Target whiteTarget = new Target(new Position(0, 0), white) {
			
			@Override
			public void draw(JPanel panel) {
				// TODO Auto-generated method stub
				
			}
		};
		
		assertNotNull(whiteTarget.getColor());
		assertTrue(whiteTarget.getColor().equals(white));
		assertFalse(whiteTarget.getColor().equals(black));
	}

}
