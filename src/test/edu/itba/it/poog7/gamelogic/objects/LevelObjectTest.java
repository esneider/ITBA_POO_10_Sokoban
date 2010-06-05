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
	
	public LevelObjectTest(String arg0) {
		super(arg0);
	}

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
		state = new GameStub("file", tileMatrix, null, 0, 0, 0);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testObject() throws Exception{
		obj = new GameObjectStub(new Position (1, 2));
		state.getTile(new Position(1, 2)).setObject(obj);
		assertTrue(obj.canMove(state, Direction.LEFT));
		assertTrue(obj.canMove(state, Direction.RIGHT));

		assertFalse(obj.canMove(state, Direction.UP));
		assertFalse(obj.canMove(state, Direction.DOWN));
	}
	
	/**
	 * Stub classes
	 * 
	 * @author eordano
	 */
	class GameStub extends Game{		
		public GameStub(String string, Tile[][] tileMatrix, Position pos,
				int i, int j, int k) {
			super(string, "nobody", tileMatrix, pos, i, j, k);
		}

		public void setTile(Position pos, Tile newTile){
			tileMatrix[pos.getX()][pos.getY()] = newTile;
		}
		
	}
	class GameObjectStub extends GameObject{
		public GameObjectStub(Position position) {
			super(position);
		}
		
		@Override
		public void destructor(Game state){
			// TODO Auto-generated method stub
		}
		
	}
}
