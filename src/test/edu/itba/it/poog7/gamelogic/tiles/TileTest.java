/**
 * 
 */
package test.edu.itba.it.poog7.gamelogic.tiles;

import test.edu.itba.it.poog7.gamelogic.LevelElementTest;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.GameTile;

/**
 * @author champo
 *
 */
public class TileTest extends LevelElementTest {

	private GameTile gameTile;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception {
		
		gameTile = new GameTile(new Position(0, 0)) {
			
			@Override
			public boolean canMoveTo(Direction dir) {
				return false;
			}
			
			@Override
			public boolean canMoveFrom(Direction dir) {
				return false;
			}
		};
		
		super.setUp();
	}
	
	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.GameTile#getObject()}.
	 */
	public void testGetObject() {
		
		assertNull(gameTile.getObject());
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.GameTile#setObject(edu.itba.it.poog7.gamelogic.objects.GameObject)}.
	 */
	public void testSetObject() {
		
		assertNull(gameTile.getObject());
		
		GameObject obj = new GameObject(new Position(0, 0)) {
			
		};
		
		gameTile.setObject(obj);
		
		assertNotNull(gameTile.getObject());
		assertEquals(obj, gameTile.getObject());
	}

}
