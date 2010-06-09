/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import edu.itba.it.poog7.gamelogic.event.StateUpdateEvent;
import edu.itba.it.poog7.gamelogic.exception.CouldNotLoadFileException;
import edu.itba.it.poog7.gamelogic.exception.NoMoreLevelsException;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.objects.Character;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.objects.event.DestroyedEvent;
import edu.itba.it.poog7.gamelogic.objects.event.MoveCharacterEvent;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.GameTile;
import edu.itba.it.poog7.gamelogic.tiles.Hole;
import edu.itba.it.poog7.gamelogic.tiles.OneWay;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.gamelogic.tiles.Wall;
import edu.itba.it.poog7.gamelogic.tiles.event.TargetMatchedEvent;
import edu.itba.it.poog7.gamelogic.tiles.event.TargetUnmatchedEvent;

/**
 * A kind-of-imperative class in charge of loading levels and interacting with the file system.
 * 
 * Its heavier-load methods returns instances of Game.
 * 
 */
public class GameManager {

	TreeMap<String, String> fileNames;
	Map<String, String> levelNames;

	/**
	 * Instance a new game manager.
	 * 
	 * @throws CouldNotLoadFileException if theres no levels, or repeated level titles.
	 */
	public GameManager() throws CouldNotLoadFileException {

		loadLevelList();
	}

	/**
	 * Load the level list
	 * 
	 * @throws CouldNotLoadFileException
	 */
	protected void loadLevelList() throws CouldNotLoadFileException {

		File dir = new File("levels/");
		File[] files = null;

		files = dir.listFiles(new FileFilter() {

			public boolean accept(File file) {

				return file.getName().endsWith(".txt");
			}
		});

		if (files == null) {
			throw new CouldNotLoadFileException("Could not load folder 'levels'.");
		}

		fileNames = new TreeMap<String, String>();
		levelNames = new HashMap<String, String>();

		for (int i = 0; i < files.length; i++) {
			BufferedReader file;
			try {
				file = new BufferedReader(new FileReader(files[i]));
			} catch (FileNotFoundException e) {
				throw new CouldNotLoadFileException("Could not load folder 'levels'.");
			}
			String name;
			if ((name = readLine(file)) == null) {
				throw new CouldNotLoadFileException("The file is corrupted.");
			}
			if (levelNames.containsKey(name)) {
				throw new CouldNotLoadFileException("Two levels with the same name.");
			}

			String fileName = "levels/" + files[i].getName();
			fileNames.put(fileName, name);
			levelNames.put(name, fileName);
		}
	}

	/**
	 * Given a level, return the next in the list of levels
	 * 
	 * If current is empty, the first level is returned.
	 * 
	 * @param current the title of the current level
	 * 
	 * @return the title of the next level
	 * 
	 * @throws NoMoreLevelsException if the level is the last
	 * @throws CouldNotLoadFileException
	 */
	public String getNextLevel(String current) throws NoMoreLevelsException, CouldNotLoadFileException {

		if (current == "") {
			return fileNames.firstEntry().getValue();
		}
		if (!levelNames.containsKey(current)) {
			throw new CouldNotLoadFileException("There is no such level");
		}

		Map.Entry<String, String> nextLevelFileName = fileNames.higherEntry(levelNames.get(current));

		if (nextLevelFileName == null) {
			throw new NoMoreLevelsException();
		}

		return nextLevelFileName.getValue();
	}

	/**
	 * Get the title of the previous level given a level title.
	 * 
	 * If current is an empty string, the last level is returned.
	 * 
	 * @param current The title of the current level.
	 * @return The title of the previous level.
	 * @throws CouldNotLoadFileException If the level title passed is non a level.
	 * @throws NoMoreLevelsException If the level is the first.
	 */
	public String getPreviousLevel(String current) throws CouldNotLoadFileException, NoMoreLevelsException {

		if (current == "") {
			return fileNames.lastEntry().getValue();
		}
		if (!levelNames.containsKey(current)) {
			throw new CouldNotLoadFileException("There is no such level");
		}

		Map.Entry<String, String> nextLevelFileName = fileNames.lowerEntry(levelNames.get(current));

		if (nextLevelFileName == null) {
			throw new NoMoreLevelsException();
		}

		return nextLevelFileName.getValue();
	}

	/**
	 * Create a new game from a saved file
	 * 
	 * @param fileName the name of the saved file
	 * @return a new instance of Game
	 * @throws CouldNotLoadFileException if the file is an invalid game data file
	 */
	public Game loadGame(String fileName) throws CouldNotLoadFileException {

		BufferedReader file;

		try {
			file = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			throw new CouldNotLoadFileException("Could not load folder 'levels'.");
		}

		int score;
		String userName;

		if ((userName = readLine(file)) == null) {
			throw new CouldNotLoadFileException("The file is corrupted");
		}

		try {
			score = Integer.parseInt(readLine(file));
		} catch (NumberFormatException e) {
			throw new CouldNotLoadFileException("The file is corrupted");
		}

		return loadState(file, fileName, userName, score);
	}

	/**
	 * Save a game played
	 * 
	 * @param game the game being played
	 * @param saveFileName where to save the state of the game
	 * @throws IOException if it can't write into the file
	 */
	public void saveGame(Game game, String saveFileName) throws IOException {

		BufferedWriter out = new BufferedWriter(new FileWriter(saveFileName));
		out.write(game.getUserName());
		out.newLine();

		out.write("" + game.getNumMoves());
		out.newLine();

		out.write(game.getLevelName());
		out.newLine();

		GameTile[][] tileMatrix = game.getTileMatrix();

		Position maxPos = new Position(tileMatrix.length, tileMatrix.length == 0 ? 0 : tileMatrix[0].length);

		out.write(maxPos.toString());
		out.newLine();

		for (int y = 0; y < maxPos.getY(); y++) {
			for (int x = 0; x < maxPos.getX(); x++) {
				GameTile gameTile = game.getTile(new Position(x, y));

				if (!(gameTile instanceof Blank)) {

					out.write(gameTile.toString());
					out.newLine();
				}
				if (gameTile.getObject() != null) {

					out.write(gameTile.getObject().toString());
					out.newLine();
				}
			}
		}

		out.close();
	}

	/**
	 * Loads a level from its stored file
	 * 
	 * @param levelName The name of the level to load
	 * @param userName the name of the new user
	 * @return a new instance of a game
	 * @throws CouldNotLoadFileException
	 */
	public Game loadLevel(String levelName, String userName) throws CouldNotLoadFileException {

		BufferedReader file;

		String fileName = levelNames.get(levelName);

		try {
			file = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new CouldNotLoadFileException("Could not load the file '" + fileName);
		}

		return loadState(file, fileName, userName, 0);
	}

	/**
	 * This is used by both loadGame and loadLevel. As they have to do the same work, we do it in here.
	 * 
	 * @param file a open file from where to read data
	 * @param fileName the name of that file
	 * @param userName the name of the user
	 * @param score the initial score of the game
	 * @return a new instance of game
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	protected Game loadState(BufferedReader file, String fileName, String userName, int score) throws CouldNotLoadFileException {

		Counter boxCount = new Counter();
		Counter targetCount = new Counter();
		Counter characterCount = new Counter();
		String newLine;
		String levelName;
		Position maxPos;
		Game game;
		GameTile[][] tileMatrix;

		game = new Game();

		if ((levelName = readLine(file)) == null) {
			throw new CouldNotLoadFileException("The file is corrupted");
		}

		try {
			String[] s = readLine(file).split(",");
			if (s.length != 2) {
				throw new CouldNotLoadFileException("The file is corrupted");
			}
			maxPos = new Position(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		} catch (NumberFormatException e) {
			throw new CouldNotLoadFileException("The file is corrupted");
		} catch (NullPointerException e) {
			throw new CouldNotLoadFileException("The file is corrupted");
		}

		tileMatrix = new GameTile[maxPos.getY()][maxPos.getX()];

		Queue<GameTile> tileQueue = new LinkedList<GameTile>();
		Queue<GameObject> objectQueue = new LinkedList<GameObject>();

		// Get info and put it into the queue
		while ((newLine = readLine(file)) != null) {
			computeLine(game, newLine, tileQueue, objectQueue, boxCount, targetCount, characterCount);
		}

		if (characterCount.getCount() != 1) {
			throw new CouldNotLoadFileException("Level corrupted: wrong number of characters.");
		}
		// Spawn Tiles and Objects
		while (!tileQueue.isEmpty()) {
			setTile(tileQueue.poll(), tileMatrix);
		}

		for (int x = 0; x < tileMatrix.length; x++) {
			for (int y = 0; y < tileMatrix[0].length; y++) {
				if (tileMatrix[x][y] == null) {
					setTile(newBlank(game, new Position(x, y)), tileMatrix);
				}
			}
		}

		while (!objectQueue.isEmpty()) {
			setObject(objectQueue.poll(), tileMatrix);
		}

		game.init(levelName, fileName, userName, tileMatrix, score, boxCount.getCount(), targetCount.getCount());

		for (GameTile[] tiles : game.getTileMatrix()) {

			for (GameTile tile : tiles) {

				GameObject object = tile.getObject();
				if (null != object && object instanceof Box) {

					((Box) object).checkMatched(game);
				}
			}
		}

		return game;
	}

	/**
	 * Set an object in a tile of the tile matrix.
	 * 
	 * @param poll The object to set.
	 * @param tileMatrix The tile matrix to get the tile from
	 * @throws CouldNotLoadFileException Bubble validation errores upwards.
	 */
	private void setObject(GameObject poll, GameTile[][] tileMatrix) throws CouldNotLoadFileException {

		Position pos = poll.getPosition();
		GameTile theTile = tileMatrix[pos.getX()][pos.getY()];

		if (poll instanceof Character) {
			if (theTile instanceof Hole) {
				throw new CouldNotLoadFileException("Level corrupted: Infinite Black Hole Bug.");
			}
			if (theTile instanceof Wall) {
				throw new CouldNotLoadFileException("Level corrupted: Character over a Wall.");
			}
		}

		if (theTile.getObject() != null) {
			throw new CouldNotLoadFileException("Level corrupted: Two objects in the same position.");
		}
		theTile.setObject(poll);
	}

	/**
	 * Set a tile in a tile matrix.
	 * 
	 * @param poll The tile to set.
	 * @param tileMatrix The tile matrix to use.
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	private void setTile(GameTile poll, GameTile[][] tileMatrix) throws CouldNotLoadFileException {

		Position pos = poll.getPosition();
		if (tileMatrix[pos.getX()][pos.getY()] != null) {
			throw new CouldNotLoadFileException("Level corrupted: Two tiles in the same position");
		}
		tileMatrix[pos.getX()][pos.getY()] = poll;
	}

	/**
	 * Read a line from the file, deletes preceding white spaces, and erases comments starting with #. Returns a String "EOF" if the end of
	 * the file was reached.
	 * 
	 * @param file the file from where to read
	 * @return the string read from the file without front whitespace and leading comments
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	private String readLine(BufferedReader file) throws CouldNotLoadFileException {

		String str;

		do {
			try {
				str = file.readLine();
			} catch (Exception e) {
				throw new CouldNotLoadFileException("Could not read from file.");
			}

			if (str != null) {
				int numeral = str.indexOf('#');
				if (numeral != -1) {
					str = str.substring(0, numeral);
				}
				str = str.trim();
			}
		} while (str != null && str.length() == 0);

		return str;
	}

	/**
	 * Given a line containing something with the regex "(\d,){6}\d", evaluate what kind of GameElement it is, and add it to a queue of
	 * elements. This method doesn't read data from the queues, only inserts data.
	 * 
	 * @param game The game object to set the state in.
	 * @param newLine the string from where to read data
	 * @param tileQueue a queue of GameTile
	 * @param objectQueue a queue of GameObject
	 * @param boxCount The current box count.
	 * @param targetCount The current target count.
	 * @param characterCount The current character count. For validation only.
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	private void computeLine(Game game, String newLine, Queue<GameTile> tileQueue, Queue<GameObject> objectQueue, Counter boxCount,
			Counter targetCount, Counter characterCount) throws CouldNotLoadFileException {

		IOHelper data = new IOHelper(newLine);

		// The only two things that are objects are Characters or Boxes (1-2)
		switch (ElementType.getType(data.getData(2))) {
		case CHARACTER:
		case BOX:
			objectQueue.add(readObject(game, data, boxCount, characterCount));
			break;
		default:
			tileQueue.add(readTile(game, data, targetCount));
			break;
		}
	}

	/**
	 * Returns a tile, bask on what kind of tile the string gotten says it is. Not a very beautiful implementation, should look for
	 * something nicer.
	 * 
	 * @param game The game to set the new state in.
	 * @param data a IOHelper with the information gotten.
	 * @param targetCount The current target count.
	 * @return a processed GameTile from the data
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	private GameTile readTile(Game game, IOHelper data, Counter targetCount) throws CouldNotLoadFileException {

		switch (ElementType.getType(data.getData(2))) {
		case TARGET:
			targetCount.increment();
			return newTarget(game, data);
		case WALL:
			return newWall(game, data);
		case HOLE:
			return newHole(game, data);
		case ONEWAY:
			return newOneWay(game, data);
		default:
			throw new CouldNotLoadFileException("El archivo esta corrupto");
		}
	}

	/**
	 * Returns an GameObject from the incoming data
	 * 
	 * @param game The game to set the state in.
	 * @param data a IOHelper with information about the object
	 * @param boxCount The current number of boxes.
	 * @param characterCount The current number of characters. For validation only.
	 * @return a new GameObject of the correct type
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	private GameObject readObject(Game game, IOHelper data, Counter boxCount, Counter characterCount) throws CouldNotLoadFileException {

		switch (ElementType.getType(data.getData(2))) {
		case CHARACTER:
			characterCount.increment();
			return newCharacter(game, data);
		case BOX:
			boxCount.increment();
			return newBox(game, data);
		default:
			throw new CouldNotLoadFileException("The file is corrupted.");
		}
	}

	/**
	 * Instance a new "one way only" tile
	 * 
	 * @param game the game where to instance the tile
	 * @param data the information about the tile
	 * @return the instance created
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	protected OneWay newOneWay(Game game, IOHelper data) throws CouldNotLoadFileException {

		return new OneWay(data.getPosition(), data.getDirection());
	}

	/**
	 * Instance a new Hole tile
	 * 
	 * @param game the game where to instance the tile
	 * @param data the information about the tile
	 * @return the instance created
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	protected GameTile newHole(Game game, IOHelper data) throws CouldNotLoadFileException {

		return new Hole(data.getPosition());
	}

	/**
	 * Instance a new Wall tile
	 * 
	 * @param game the game where to instance the tile
	 * @param data the information about the tile
	 * @return the instance created
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	protected GameTile newWall(Game game, IOHelper data) throws CouldNotLoadFileException {

		return new Wall(data.getPosition());
	}

	/**
	 * Instance a new Target tile
	 * 
	 * @param game the game where to instance the tile
	 * @param data the information about the tile
	 * @return the instance created
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	protected GameTile newTarget(Game game, IOHelper data) throws CouldNotLoadFileException {

		return new Target(data.getPosition(), data.getColor());
	}

	/**
	 * Instance a new Character object
	 * 
	 * @param game the game where to instance the character
	 * @param data the information about the character (position)
	 * @return the instance created
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	protected GameObject newCharacter(Game game, IOHelper data) throws CouldNotLoadFileException {

		Character newCharacter = new Character(data.getPosition());

		game.subscribeListener(MoveCharacterEvent.class, newCharacter.getMoveListener());

		newCharacter.subscribeListener(DestroyedEvent.class, game.getCharacterDestroyedListener());
		newCharacter.subscribeListener(StateUpdateEvent.class, game.getCharacterMovedListener());

		return newCharacter;
	}

	/**
	 * Instance a new Box
	 * 
	 * @param game the game where to instance the box
	 * @param data the information about the box
	 * @return the instance created
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	protected GameObject newBox(Game game, IOHelper data) throws CouldNotLoadFileException {

		Box newBox = new Box(data.getPosition(), data.getColor());

		newBox.subscribeListener(DestroyedEvent.class, game.getBoxDestroyedListener());

		newBox.subscribeListener(TargetMatchedEvent.class, game.getTargetMatchedListener());
		newBox.subscribeListener(TargetUnmatchedEvent.class, game.getTargetUnmatchedListener());

		return newBox;
	}

	/**
	 * Instance a new blank tile
	 * 
	 * @param game the game where to instance the blank tile
	 * @param pos the information about the tile, where is it
	 * @return the instance created
	 * @throws CouldNotLoadFileException Bubble validation errors upwards.
	 */
	protected GameTile newBlank(Game game, Position pos) throws CouldNotLoadFileException {

		return new Blank(pos);
	}

	/**
	 * A useful class to get information from and into a file. <br/>
	 * A IOHelper translates information from raw data into something more accessible for the methods of this class. <br/>
	 * It receives a string that has to be of the regex form (\d,){6}\d => that means 7 numbers separated by comas. The first two are
	 * translated into {row, column}; the third one represents the ElementType of the element being read, the fourth is how is it rotated
	 * and the last three numbers represent the RGB color of the element.
	 * 
	 * @author eordano
	 * 
	 */
	protected class IOHelper {
		private static final int lineItems = 7;
		int data[];

		/**
		 * A constructor from a string of the regex form "(\d,){6}\d"
		 * 
		 * @param S incoming data to be processed
		 * @throws CouldNotLoadFileException Bubble validation errors upwards.
		 */
		IOHelper(String S) throws CouldNotLoadFileException {

			String[] split = S.split(",");
			if (split.length != lineItems) {
				throw new CouldNotLoadFileException("The file is corrupted.");
			}
			data = new int[split.length];
			try {
				for (int i = 0; i < split.length; i++) {
					data[i] = Integer.parseInt(split[i]);
				}
			} catch (NumberFormatException e) {
				throw new CouldNotLoadFileException("The file is corrupted.");
			}
		}

		/**
		 * Access data of the vector
		 * 
		 * @param pos position in the vector
		 * @return the value of data[pos]
		 */
		public int getData(int pos) {

			return data[pos];
		}

		/**
		 * Access direction of the element. This is stored in the fourth position
		 * 
		 * @return The direction the object is oriented in.
		 */
		public Direction getDirection() {

			return Direction.getTurn(data[3]);
		}

		/**
		 * Cast into a position of data stored in the first two elements of the data array. The position of a element is stored in the first
		 * two numbers obtained from a line.
		 * 
		 * @return a Position from the data obtained
		 */
		public Position getPosition() {

			return new Position(data[1], data[0]);
		}

		/**
		 * Get the color element encoded in the string, in the last three elements.
		 * 
		 * @return a Color from the data obtained
		 */
		public RGBColor getColor() {

			return new RGBColor(data[4], data[5], data[6]);
		}

		/**
		 * Get a Representation of the data. A new IOHelper could be gotten with this string as constructor.
		 */
		public String toString() {

			String res = "" + data[0];
			for (int i = 1; i < data.length; i++) {
				res += "," + data[i];
			}
			return res;
		}
	}

	/**
	 * This is a small class made to pass a integer from function to function. We are interested in it's final value and we are only
	 * incrementing it.
	 * 
	 */
	private class Counter {
		private int count;

		/**
		 * Instance a new counter.
		 */
		Counter() {

			count = 0;
		}

		/**
		 * Increment the counter in one.
		 */
		public void increment() {

			count++;
		}

		/**
		 * Get the current count.
		 * 
		 * @return The count.
		 */
		public int getCount() {

			return count;
		}
	}
}
