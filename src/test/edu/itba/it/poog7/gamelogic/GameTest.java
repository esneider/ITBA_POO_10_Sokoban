package test.edu.itba.it.poog7.gamelogic;

import junit.framework.TestCase;
import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;
import edu.itba.it.poog7.gamelogic.event.GameFinishedEvent;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.objects.Character;
import edu.itba.it.poog7.gamelogic.objects.event.MoveCharacterEvent;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.GameTile;
import edu.itba.it.poog7.gamelogic.tiles.Target;

public class GameTest extends TestCase {
	Game aGame;
	Character character;

	public void setUp() throws Exception {
		aGame = new Game();

		GameTile[][] tiles = new GameTile[1][3];
		character = new Character(new Position(0, 0));

		tiles[0][0] = new Blank(new Position(0, 0));
		tiles[0][1] = new Blank(new Position(0, 1));
		tiles[0][2] = new Target(new Position(0, 2), new RGBColor(0, 0, 0));
		aGame.init("1", "2", "3", tiles, 0, 0, 1); // 0 score, 1 boxes
		// (different testcases
		// use one box, 1 target
	}

	public final void testInit() {
		assertTrue(aGame.getLevelName() == "1");
		assertTrue(aGame.getLevelFileName() == "2");
		assertTrue(aGame.getUserName() == "3");
		assertTrue(aGame.getNumMoves() == 0);
	}

	public final void testMoveCharacter() {

		character.getMoveListener().eventTriggered(
				new MoveCharacterEvent(aGame, Direction.DOWN));
		assertTrue(aGame.getTile(new Position(0, 1)).getObject().equals(
				character));
		character.getMoveListener().eventTriggered(
				new MoveCharacterEvent(aGame, Direction.DOWN));
		assertTrue(aGame.getTile(new Position(0, 2)).getObject().equals(
				character));
		character.getMoveListener().eventTriggered(
				new MoveCharacterEvent(aGame, Direction.UP));
		assertTrue(aGame.getTile(new Position(0, 1)).getObject().equals(
				character));

		assertTrue(aGame.getNumMoves() == 3);
	}

	public final void testMoveCharacterAndBox() {

		// Set up a Box under the Character
		Box blackBox = new Box(new Position(0, 1), new RGBColor(0, 0, 0));
		aGame.getTile(new Position(0, 1)).setObject(blackBox);

		character.getMoveListener().eventTriggered(
				new MoveCharacterEvent(aGame, Direction.DOWN));
		assertTrue(aGame.getTile(new Position(0, 1)).getObject().equals(
				character));
		assertTrue(aGame.getTile(new Position(0, 2)).getObject().equals(
				blackBox));

	}

	public final void testWonIfSameColorBox() {

		// Set up a Box under the Character
		Box blackBox = new Box(new Position(0, 1), new RGBColor(0, 0, 0));
		aGame.getTile(new Position(0, 1)).setObject(blackBox);

		int who[];
		who = new int[] { 0 };
		StubCheck e = new StubCheck(who);
		aGame.subscribeListener(GameFinishedEvent.class, e);

		character.getMoveListener().eventTriggered(
				new MoveCharacterEvent(aGame, Direction.DOWN));
		assertTrue(aGame.getTile(new Position(0, 1)).getObject().equals(
				character));
		assertTrue(aGame.getTile(new Position(0, 2)).getObject().equals(
				blackBox));

		assertTrue(who[0] == 1);
	}

	/**
	 * 
	 */
	public final void testNotWonIfNotTheSameColorBox() {

		// Set up a Box under the Character
		Box notSoBlackBox = new Box(new Position(0, 1), new RGBColor(0, 255, 0));
		aGame.getTile(new Position(0, 1)).setObject(notSoBlackBox);

		int who[];
		who = new int[] { 0 };
		StubCheck e = new StubCheck(who);
		aGame.subscribeListener(GameFinishedEvent.class, e);

		character.getMoveListener().eventTriggered(
				new MoveCharacterEvent(aGame, Direction.DOWN));
		assertTrue(aGame.getTile(new Position(0, 1)).getObject().equals(
				character));
		assertTrue(aGame.getTile(new Position(0, 2)).getObject().equals(
				notSoBlackBox));

		assertTrue(who[0] == 0);
	}

	/**
	 * "En la guerra y en los unit tests, todo se vale" - Julius Caesar et al
	 * 
	 * This class uses a dirty trick to pass a C-like "output parameter"
	 */
	class StubCheck implements EventListener {
		int[] who;

		public void eventTriggered(Event e) {
			who[0] = 1;
		}

		public StubCheck(int[] who) {
			this.who = who;
		}
	}
}
