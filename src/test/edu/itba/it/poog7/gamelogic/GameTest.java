package test.edu.itba.it.poog7.gamelogic;

import junit.framework.TestCase;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;
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
		aGame.init("1", "2", "3", tiles, 0, 1, 4);
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

}
