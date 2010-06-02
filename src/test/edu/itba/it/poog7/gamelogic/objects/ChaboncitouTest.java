package test.edu.itba.it.poog7.gamelogic.objects;

import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.objects.Chaboncitou;

public class ChaboncitouTest extends LevelObjectTest {
	public ChaboncitouTest(String arg0) {
		super(arg0);
	}

	ChaboncitouStub chabon;
	
	public void setUp() throws Exception {
		super.setUp();
		chabon = new ChaboncitouStub(new Position(1, 1));
		state.getTile(new Position(1,1)).setObject(chabon);
	}

	/**
	 * First movement case. The simplest: [P] -> [ ] (a player moving into a empty blank tile
	 * 
	 * @throws Exception
	 */
	public void testCanMove1() throws Exception{
		assertTrue(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * Second movement case. A player moves a box.
	 * 
	 * @throws Exception
	 */
	public void testCanMove2() throws Exception{
		state.getTile(new Position(1,2)).setObject(new BoxStub(new Position(1, 2)));
		assertTrue(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * A player shouldn't be able to move a box that has a box behind it.
	 * 
	 * @throws Exception
	 */
	public void testCanMove3() throws Exception{
		state.getTile(new Position(1,2)).setObject(new Box(new Position(1, 2)));
		state.getTile(new Position(1,3)).setObject(new Box(new Position(1, 3)));
		assertFalse(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * Moving a box that has a OneWayTile under it
	 * 
	 * @throws Exception
	 */
	public void testCanMove4() throws Exception{
		state.getTile(new Position(1,2)).setObject(new BoxStub(new Position(1, 2)));
		((LevelStateStub) state).setTile(new Position(1, 2), new OneWayStub(new Position(1, 2),	Direction.RIGHT));
		assertTrue(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * Moving a box to the right; the box has a OneWayTile under it in the wrong direction (up)
	 * so it can't be moved.
	 * 
	 * @throws Exception
	 */
	public void testCanMove5() throws Exception{
		state.getTile(new Position(1,2)).setObject(new BoxStub(new Position(1, 2)));
		((LevelStateStub) state).setTile(new Position(1, 2), new OneWayStub(new Position(1, 2),	Direction.UP));
		assertFalse(chabon.canMove(state, Direction.RIGHT));
	}
	
	/**
	 * Checks that the user is unable to move across walls in all directions
	 * 
	 * @throws Exception
	 */
	public void testCanMove6() throws Exception{
		((LevelStateStub) state).setTile(new Position(1,2), new WallStub(new Position(1, 2)));
		assertFalse(chabon.canMove(state, Direction.LEFT));
		assertFalse(chabon.canMove(state, Direction.RIGHT));
		assertFalse(chabon.canMove(state, Direction.UP));
		assertFalse(chabon.canMove(state, Direction.DOWN));
	}

	/**
	 * Moving a to the right: I have a onewaytile under me in the right direction
	 * 
	 * @throws Exception
	 */
	public void testCanMove7() throws Exception{
		((LevelStateStub) state).setTile(new Position(1, 1), new OneWayStub(new Position(1, 1), Direction.RIGHT));
		assertTrue(chabon.canMove(state, Direction.RIGHT));
	}

	/**
	 * Moving to the right: but there is a onewaytile there and it's not in the right direction
	 * 
	 * @throws Exception
	 */
	public void testCanMove8() throws Exception{
		((LevelStateStub) state).setTile(new Position(1, 2), new OneWayStub(new Position(1, 2), Direction.UP));
		assertFalse(chabon.canMove(state, Direction.RIGHT));
	}

	public class ChaboncitouStub extends Chaboncitou{
		public ChaboncitouStub(Position pos) {
			super(pos);
			// TODO Auto-generated constructor stub
		}
	}
}
