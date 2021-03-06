package edu.itba.it.poog7.view;

import java.io.BufferedReader;
import java.io.IOException;

import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.exception.CouldNotLoadFileException;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.objects.Character;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.GameTile;
import edu.itba.it.poog7.gamelogic.tiles.Hole;
import edu.itba.it.poog7.gamelogic.tiles.OneWay;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.gamelogic.tiles.Wall;
import edu.itba.it.poog7.view.objects.DBox;
import edu.itba.it.poog7.view.objects.DCharacter;
import edu.itba.it.poog7.view.tiles.DBlank;
import edu.itba.it.poog7.view.tiles.DHole;
import edu.itba.it.poog7.view.tiles.DOneWay;
import edu.itba.it.poog7.view.tiles.DTarget;
import edu.itba.it.poog7.view.tiles.DWall;

public class GameManager extends edu.itba.it.poog7.gamelogic.GameManager {

	/**
	 * @throws CouldNotLoadFileException
	 */
	public GameManager() throws CouldNotLoadFileException {

		super();
	}

	private static final int CELL_SIZE = 30;
	private View view;

	/**
	 * Getter for the last view created
	 * 
	 * @return the view created
	 */
	public View getView() {

		return view;
	}

	@Override
	protected Game loadState(BufferedReader file, String fileName, String userName, int score) throws CouldNotLoadFileException {

		view = new View(0, 0);
		Game game = super.loadState(file, fileName, userName, score);

		view.setBoardSize(game.getHeight(), game.getWidth(), CELL_SIZE);

		return game;
	}

	@Override
	protected GameObject newBox(Game game, IOHelper data) throws CouldNotLoadFileException {

		Box newBox = (Box) super.newBox(game, data);

		try {
			view.addElement(new DBox(view, newBox));
		} catch (IOException e) {
			throw new CouldNotLoadFileException("Could not load resource file");
		}

		return newBox;
	}

	@Override
	protected GameObject newCharacter(Game game, IOHelper data) throws CouldNotLoadFileException {

		Character newCharacter = (Character) super.newCharacter(game, data);

		try {
			view.addElement(new DCharacter(view, newCharacter));
		} catch (IOException e) {
			throw new CouldNotLoadFileException("Could not load resource file");
		}

		return newCharacter;
	}

	@Override
	protected GameTile newHole(Game game, IOHelper data) throws CouldNotLoadFileException {

		Hole newHole = (Hole) super.newHole(game, data);

		try {
			view.addElement(new DHole(view, newHole));
		} catch (IOException e) {
			throw new CouldNotLoadFileException("Could not load resource file");
		}

		return newHole;
	}

	@Override
	protected OneWay newOneWay(Game game, IOHelper data) throws CouldNotLoadFileException {

		OneWay newOneWay = (OneWay) super.newOneWay(game, data);

		try {
			view.addElement(new DOneWay(view, newOneWay));
		} catch (IOException e) {
			throw new CouldNotLoadFileException("Could not load resource file");
		}

		return newOneWay;
	}

	@Override
	protected GameTile newTarget(Game game, IOHelper data) throws CouldNotLoadFileException {

		Target newTarget = (Target) super.newTarget(game, data);

		try {
			view.addElement(new DTarget(view, newTarget));
		} catch (IOException e) {
			throw new CouldNotLoadFileException("Could not load resource file");
		}

		return newTarget;
	}

	@Override
	protected GameTile newWall(Game game, IOHelper data) throws CouldNotLoadFileException {

		Wall newWall = (Wall) super.newWall(game, data);

		try {
			view.addElement(new DWall(view, newWall));
		} catch (IOException e) {
			throw new CouldNotLoadFileException("Could not load resource file");
		}

		return newWall;
	}

	@Override
	protected GameTile newBlank(Game game, Position pos) throws CouldNotLoadFileException {

		Blank newBlank = (Blank) super.newBlank(game, pos);

		try {
			view.addElement(new DBlank(view, newBlank));
		} catch (IOException e) {
			throw new CouldNotLoadFileException("Could not load resource file");
		}

		return newBlank;
	}
}
