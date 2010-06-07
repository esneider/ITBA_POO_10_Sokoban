package test.edu.itba.it.poog7.gamelogic.objects;

import junit.framework.TestCase;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.Tile;
import edu.itba.it.poog7.gamelogic.tiles.Wall;

/**
 * @author eordano
 */
public class LevelObjectTest extends TestCase {
	GameStub state;
	GameObject obj;
	
	protected void setUp() throws Exception {
		super.setUp();
		Tile tileMatrix[][] = new Tile[3][5];
		// Make a simple room
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 5; j++){
				tileMatrix[i][j] = new Wall(new Position(i, j));
			}
		}
		tileMatrix[1][1] = new Blank(new Position(1,1));
		tileMatrix[1][2] = new Blank(new Position(1,2));
		tileMatrix[1][3] = new Blank(new Position(1,3));	
		state = new GameStub("file", tileMatrix, 0, 0, 0);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Test that the game object can move correctly.
	 * @throws Exception
	 */
	public void testObject() throws Exception{
		obj = new GameObjectStub(new Position (1, 2));
		state.getTile(new Position(1, 2)).setObject(obj);
		assertTrue(obj.canMove(state, Direction.UP));
		assertTrue(obj.canMove(state, Direction.DOWN));

		assertFalse(obj.canMove(state, Direction.RIGHT));
		assertFalse(obj.canMove(state, Direction.LEFT));
	}
	
	/**
	 * Stub classes
	 * 
	 * @author eordano
	 */
	class GameStub extends Game {		
		/**
		 * Constructor.
		 * 
		 * @param string A string.
		 * @param tileMatrix The tile matrix that represents the game board.
		 * @param i Number of movements made.
		 * @param j Number of boxes.
		 * @param k Number of targets.
		 */
		public GameStub(String string, Tile[][] tileMatrix, 
				int i, int j, int k) {
			init(string, string, "nobody", tileMatrix, i, j, k);
		}
	}
	/**
	 * @author dario
	 *
	 */
	class GameObjectStub extends GameObject {
		/**
		 * Constructor.
		 * 
		 * @param position The objects initial position.
		 */
		public GameObjectStub(Position position) {
			super(position);
		}
		
	}
}
