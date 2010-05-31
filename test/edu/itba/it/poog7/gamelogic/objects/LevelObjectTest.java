package edu.itba.it.poog7.gamelogic.objects;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.LevelState;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.TileMatrix;
import edu.itba.it.poog7.gamelogic.exceptions.GameOverException;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.Tile;
import edu.itba.it.poog7.gamelogic.tiles.Wall;
import junit.framework.TestCase;

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

	public void test1() throws Exception{
		assert chabon.canMove(state, Direction.LEFT);
		
		state.getMatrix().getTile(new Position(1,2)).setObject(new BoxStub(new Position(1, 2)));
		assert chabon.canMove(state, Direction.LEFT);
		
		state.getMatrix().getTile(new Position(1,3)).setObject(new BoxStub(new Position(1, 3)));
		assert !chabon.canMove(state, Direction.LEFT);
	}
	
	class TileMatrixStub extends TileMatrix{
		TileMatrixStub(){
			this.matrix = new Tile[5][5];
			// Make a simple room
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					matrix[i][j] = new WallStub(new Position(i, j));
				}
				if (i > 0 && i < 4){
					for(int j = 1; j < 4; j++){
						matrix[i][j] = new BlankStub(new Position(i, j));
					}
				}
			}
		}
		public void setTile(Position pos, Tile newTile){
			matrix[pos.getX()][pos.getY()] = newTile;
		}
	}
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
	class LevelStateStub extends LevelState{
		LevelStateStub(){
			matrix = new TileMatrixStub();
		}
		public void setTile(Position pos, Tile newTile){
			((TileMatrixStub) matrix).setTile(pos, newTile);
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
	class BoxStub extends Box{

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
