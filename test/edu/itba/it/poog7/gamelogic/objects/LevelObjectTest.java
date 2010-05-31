package edu.itba.it.poog7.gamelogic.objects;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.LevelState;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.TileMatrix;
import edu.itba.it.poog7.gamelogic.exceptions.GameOverException;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.OneWay;
import edu.itba.it.poog7.gamelogic.tiles.Tile;
import edu.itba.it.poog7.gamelogic.tiles.Wall;
import junit.framework.TestCase;

/**
 * @author eordano
 *
 */
public class LevelObjectTest extends TestCase {
	LevelStateStub state;
	ChaboncitouStub chabon;
	
	public LevelObjectTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		state = new LevelStateStub();
		chabon = new ChaboncitouStub(new Position(1, 1));
		state.getMatrix().getTile(new Position(1,1)).setObject(chabon);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * First movement case. The simplest: [P] -> [ ] (a player moving into a empty blank tile
	 * 
	 * @throws Exception
	 */
	public void testMove1() throws Exception{
		assertTrue(chabon.canMove(state, Direction.RIGHT));
	}
	
	
	/**
	 * Second movement case. A player moves a box.
	 * 
	 * @throws Exception
	 */
	public void testMove2() throws Exception{
		state.getMatrix().getTile(new Position(1,2)).setObject(new BoxStub(new Position(1, 2)));
		assertTrue(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * A player shouldn't be able to move a box that has a box behind it.
	 * 
	 * @throws Exception
	 */
	public void testMove3() throws Exception{
		state.getMatrix().getTile(new Position(1,2)).setObject(new BoxStub(new Position(1, 2)));
		state.getMatrix().getTile(new Position(1,3)).setObject(new BoxStub(new Position(1, 3)));
		assertFalse(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * Moving a box that has a OneWayTile under it
	 * 
	 * @throws Exception
	 */
	public void testMove4() throws Exception{
		state.getMatrix().getTile(new Position(1,2)).setObject(new BoxStub(new Position(1, 2)));
		((TileMatrixStub) state.getMatrix()).setTile(new Position(1, 2), new OneWayStub(new Position(1, 2), 
				Direction.RIGHT));
		assertTrue(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * Moving a box to the right; the box has a OneWayTile under it in the wrong direction (up)
	 * so it can't be moved.
	 * 
	 * @throws Exception
	 */
	public void testMove5() throws Exception{
		state.getMatrix().getTile(new Position(1,2)).setObject(new BoxStub(new Position(1, 2)));
		((TileMatrixStub) state.getMatrix()).setTile(new Position(1, 2), new OneWayStub(new Position(1, 2), 
				Direction.UP));
		assertFalse(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * Checks that the user is unable to move across walls in all directions
	 * 
	 * @throws Exception
	 */
	public void testMove6() throws Exception{
		((TileMatrixStub) state.getMatrix()).setTile(new Position(1,3), new WallStub(new Position(1, 3)));
		assertTrue(chabon.canMove(state, Direction.LEFT));
		assertTrue(chabon.canMove(state, Direction.RIGHT));
		assertTrue(chabon.canMove(state, Direction.UP));
		assertTrue(chabon.canMove(state, Direction.DOWN));
	}

	/**
	 * Moving a to the right: I have a onewaytile under me in the right direction
	 * 
	 * @throws Exception
	 */
	public void testMove7() throws Exception{
		((TileMatrixStub) state.getMatrix()).setTile(new Position(1, 1), new OneWayStub(new Position(1, 1), 
				Direction.RIGHT));
		assertTrue(chabon.canMove(state, Direction.RIGHT));
	}

	/**
	 * Moving to the right: but there is a onewaytile there and it's not in the right direction
	 * 
	 * @throws Exception
	 */
	public void testMove8() throws Exception{
		((TileMatrixStub) state.getMatrix()).setTile(new Position(1, 2), new OneWayStub(new Position(1, 2), 
				Direction.UP));
		assertFalse(chabon.canMove(state, Direction.RIGHT));
	}
	
	
	/**
	 * Stub classes
	 * 
	 * @author eordano
	 */
	private class TileMatrixStub extends TileMatrix{
		TileMatrixStub(){
			this.matrix = new Tile[3][5];
			// Make a simple room
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 5; j++){
					matrix[i][j] = new WallStub(new Position(i, j));
				}
			}
			matrix[1][1] = new BlankStub(new Position(1,1));
			matrix[1][2] = new BlankStub(new Position(1,2));
			matrix[1][3] = new BlankStub(new Position(1,3));
		}
		public void setTile(Position pos, Tile newTile){
			matrix[pos.getX()][pos.getY()] = newTile;
		}
	}
	private class WallStub extends Wall{
		public WallStub(Position pos) {
			super(pos);
		}

		@Override
		public void draw(JPanel panel) {
			return;
		}
		
	}
	private class BlankStub extends Blank{
		public BlankStub(Position pos) {
			super(pos);
		}

		@Override
		public void draw(JPanel panel) {
			return;
		}
		
	}
	private class OneWayStub extends OneWay{
		public OneWayStub(Position pos, Direction orientation) {
			super(pos, orientation);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void draw(JPanel panel) {
			return;
		}
		
	}
	private class LevelStateStub extends LevelState{
		LevelStateStub(){
			matrix = new TileMatrixStub();
		}
	}
	class ChaboncitouStub extends Chaboncitou{

		public ChaboncitouStub(Position pos) {
			super(pos);
		}

		@Override
		public void draw(JPanel panel) {
			return;
		}
	}
	private class BoxStub extends Box{

		public BoxStub(Position pos) {
			super(pos);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void destructor(LevelState state) throws GameOverException {
			// TODO Auto-generated method stub
			state.destroyedBox();
		}

		@Override
		public void draw(JPanel panel) {
			// TODO Auto-generated method stub
			return;
		}
		
	}
}
