package edu.itba.it.poog7.gamelogic.objects;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.LevelState;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.TileMatrix;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.Tile;
import edu.itba.it.poog7.gamelogic.tiles.Wall;
import junit.framework.TestCase;

public class ChaboncitouTest extends TestCase {
	public ChaboncitouTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCanMove() {
		fail("Not yet implemented"); // TODO
	}

	public void testMove() {
		fail("Not yet implemented"); // TODO
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
	}
}
