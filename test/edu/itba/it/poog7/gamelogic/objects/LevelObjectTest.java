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
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @author eordano
 */
public class LevelObjectTest extends TestCase {
	LevelStateStub state;
	
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
		
	}
	
	/**
	 * Stub classes
	 * 
	 * @author eordano
	 */
	public class TileMatrixStub extends TileMatrix{
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
			matrix = new TileMatrixStub();
		}
	}
}
