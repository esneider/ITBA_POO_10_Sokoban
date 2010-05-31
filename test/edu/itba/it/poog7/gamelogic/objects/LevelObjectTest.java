package edu.itba.it.poog7.gamelogic.objects;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.LevelState;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.exceptions.GameOverException;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.OneWay;
import edu.itba.it.poog7.gamelogic.tiles.Tile;
import edu.itba.it.poog7.gamelogic.tiles.Wall;
import junit.framework.TestCase;

/**
 * @author eordano
 */
public class LevelObjectTest extends TestCase {
	LevelState state;
	LevelObject obj;
	
	public LevelObjectTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		state = new LevelStateStub();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testObject() throws Exception{
		obj = new LevelObjectStub(new Position (1, 2));
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
	class WallStub extends Wall{
		public WallStub(Position pos) {
			super(pos);
		}

		@Override
		public void draw(JPanel panel) {
			return;
		}
		
	}
	class BlankStub extends Blank{
		public BlankStub(Position pos) {
			super(pos);
		}

		@Override
		public void draw(JPanel panel) {
			return;
		}
		
	}
	class OneWayStub extends OneWay{
		public OneWayStub(Position pos, Direction orientation) {
			super(pos, orientation);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void draw(JPanel panel) {
			return;
		}
		
	}
	class LevelStateStub extends LevelState{
		LevelStateStub(){
			super();
			tileMatrix = new Tile[3][5];
			// Make a simple room
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 5; j++){
					tileMatrix[i][j] = new WallStub(new Position(i, j));
				}
			}
			tileMatrix[1][1] = new BlankStub(new Position(1,1));
			tileMatrix[1][2] = new BlankStub(new Position(1,2));
			tileMatrix[1][3] = new BlankStub(new Position(1,3));
		}
		
		public void setTile(Position pos, Tile newTile){
			tileMatrix[pos.getX()][pos.getY()] = newTile;
		}
		
		@Override
		public void draw(JPanel panel) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class LevelObjectStub extends LevelObject{
		public LevelObjectStub(Position pos) {
			super(pos);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void destructor(LevelState state) throws GameOverException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void draw(JPanel panel) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
