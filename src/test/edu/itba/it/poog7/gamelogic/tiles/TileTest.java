/**
 * 
 */
package test.edu.itba.it.poog7.gamelogic.tiles;

import test.edu.itba.it.poog7.gamelogic.LevelElementTest;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

/**
 * @author champo
 *
 */
public class TileTest extends LevelElementTest {

	private Tile tile;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception {
		
		tile = new Tile(new Position(0, 0)) {
			
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
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Tile#getObject()}.
	 */
	public void testGetObject() {
		
		assertNull(tile.getObject());
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.tiles.Tile#setObject(edu.itba.it.poog7.gamelogic.objects.GameObject)}.
	 */
	public void testSetObject() {
		
		assertNull(tile.getObject());
		
		GameObject obj = new GameObject(new Position(0, 0)) {
			
			@Override
			public void destructor(Game state) {
			}
		};
		
		tile.setObject(obj);
		
		assertNotNull(tile.getObject());
		assertEquals(obj, tile.getObject());
	}

}
