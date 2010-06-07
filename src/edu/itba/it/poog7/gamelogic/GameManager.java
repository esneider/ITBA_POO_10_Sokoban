/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import edu.itba.it.poog7.event.EventDispatcher;
import edu.itba.it.poog7.gamelogic.exception.CouldNotLoadFileException;
import edu.itba.it.poog7.gamelogic.exception.NoMoreLevelsException;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.objects.Chaboncitou;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.Blank;
import edu.itba.it.poog7.gamelogic.tiles.Hole;
import edu.itba.it.poog7.gamelogic.tiles.OneWay;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.gamelogic.tiles.Tile;
import edu.itba.it.poog7.gamelogic.tiles.Wall;

/**
 * A kind-of-imperative class in charge of loading levels and interacting with
 * the file system.
 * 
 * Its heavier-load methods returns instances of Game.
 * 
 */
public class GameManager extends EventDispatcher {

	String[] levelList;

	public String[] getLevelList() throws FileNotFoundException {

		File dir = new File("levels/");
		File[] files = null;

		files = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.getName().endsWith(".txt");
			}
		});

		if (files == null) {
			throw new FileNotFoundException("No folder 'levels' found.");
		}

		String[] levels = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			levels[i] = files[i].getName();
		}

		Arrays.sort(levels);

		return levels;
	}

	public String getNextLevel(String current) throws FileNotFoundException, NoMoreLevelsException {

		if (current == "") {
			levelList = getLevelList();
			return levelList[0];
		}

		int index = Arrays.binarySearch(levelList, current);

		if (index + 1 == levelList.length) {
			throw new NoMoreLevelsException();
		}
		return levelList[index + 1];
	}

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

	public void saveGame(Game game, String saveFileName) throws FileNotFoundException {

		PrintStream out = new PrintStream(new FileOutputStream(new File("levels/" + saveFileName)));
		out.println(game.getLevelName());
		out.println(game.getUserName());
		out.println(game.getNumMoves());

		Tile[][] tileMatrix = game.getTileMatrix();

		Position maxPos = new Position(tileMatrix.length, tileMatrix.length == 0 ? 0 : tileMatrix[0].length);

		out.println(maxPos);

		for (int y = 0; y < maxPos.getY(); y++) {
			for (int x = 0; x < maxPos.getX(); x++) {
				Tile tile = game.getTile(new Position(x, y));
				out.println(tile);
				if (tile.getObject() != null)
					out.println(tile.getObject());
			}
		}
	}

	public Game loadLevel(String fileName, String userName) throws CouldNotLoadFileException {

		BufferedReader file;

		try {
			file = new BufferedReader(new FileReader("levels/" + fileName));
		} catch (FileNotFoundException e) {
			throw new CouldNotLoadFileException("Could not load the file '" + fileName);
		}

		return loadState(file, fileName, userName, 0);
	}

	protected Game loadState(BufferedReader file, String fileName, String userName, int score)
			throws CouldNotLoadFileException {

		Counter boxCount = new Counter();
		Counter targetCount = new Counter();
		Counter chaboncitouCount = new Counter();
		String newLine;
		String levelName;
		Position maxPos;
		Game game;
		Tile[][] tileMatrix;

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

		tileMatrix = new Tile[maxPos.getX()][maxPos.getY()];

		Queue<Tile> tileQueue = new LinkedList<Tile>();
		Queue<GameObject> objectQueue = new LinkedList<GameObject>();

		// Get info and put it into the queue
		while ((newLine = readLine(file)) != null) {
			computeLine(game, newLine, tileQueue, objectQueue, boxCount, targetCount, chaboncitouCount);
		}

		if (chaboncitouCount.getCount() != 1) {
			throw new CouldNotLoadFileException("Level corrupted: wrong number of chaboncitous.");
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

		return game;
	}

	private void setObject(GameObject poll, Tile[][] tileMatrix) throws CouldNotLoadFileException {

		Position pos = poll.getPosition();
		Tile theTile = tileMatrix[pos.getX()][pos.getY()];

		if (poll instanceof Chaboncitou) {
			if (theTile instanceof Hole) {
				throw new CouldNotLoadFileException("Level corrupted: Infinite Black Hole Bug.");
			}
			if (theTile instanceof Wall) {
				throw new CouldNotLoadFileException("Level corrupted: Chaboncitou over a Wall.");
			}
		}

		if (theTile.getObject() != null) {
			throw new CouldNotLoadFileException("Level corrupted: Two objects in the same position.");
		}
		theTile.setObject(poll);
	}

	private void setTile(Tile poll, Tile[][] tileMatrix) throws CouldNotLoadFileException {

		Position pos = poll.getPosition();
		if (tileMatrix[pos.getX()][pos.getY()] != null) {
			throw new CouldNotLoadFileException("Level corrupted: Two tiles in the same position");
		}
		tileMatrix[pos.getX()][pos.getY()] = poll;
	}

	/**
	 * Read a line from the file, deletes preceding white spaces, and erases
	 * comments starting with #. Returns a String "EOF" if the end of the file
	 * was reached.
	 * 
	 * @param file
	 *            the file from where to read
	 * @return the string read from the file without front whitespace and
	 *         leading comments
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
	 * Given a line containing something with the regex "(\d,){6}\d", evaluate
	 * what kind of GameElement it is, and add it to a queue of elements. This
	 * method doesn't read data from the queues, only inserts data.
	 * 
	 * @param newLine
	 *            the string from where to read data
	 * @param tileQueue
	 *            a queue of Tile
	 * @param objectQueue
	 *            a queue of GameObject
	 * @throws Exception
	 */
	private void computeLine(Game game, String newLine, Queue<Tile> tileQueue, Queue<GameObject> objectQueue,
			Counter boxCount, Counter targetCount, Counter chaboncitouCount) throws CouldNotLoadFileException {

		IOHelper data = new IOHelper(newLine);

		// The only two things that are objects are Chaboncitous or Boxes (1-2)
		switch (ElementType.getType(data.getData(2))) {
		case CHABONCITOU:
		case BOX:
			objectQueue.add(readObject(game, data, boxCount, chaboncitouCount));
			break;
		default:
			tileQueue.add(readTile(game, data, targetCount));
			break;
		}
	}

	/**
	 * Returns a tile, bask on what kind of tile the string gotten says it is.
	 * Not a very beautiful implementation, should look for something nicer.
	 * 
	 * @param data
	 *            a IOHelper with the information gotten.
	 * @return a processed Tile from the data
	 * @throws CouldNotLoadFileException
	 */
	private Tile readTile(Game game, IOHelper data, Counter targetCount) throws CouldNotLoadFileException {

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
	 * @param data
	 *            a IOHelper with information about the object
	 * @param chaboncitouCount
	 * @return a new GameObject of the correct type
	 */
	private GameObject readObject(Game game, IOHelper data, Counter boxCount, Counter chaboncitouCount)
			throws CouldNotLoadFileException {
		switch (ElementType.getType(data.getData(2))) {
		case CHABONCITOU:
			chaboncitouCount.increment();
			return newChaboncitou(game, data);
		case BOX:
			boxCount.increment();
			return newBox(game, data);
		default:
			throw new CouldNotLoadFileException("The file is corrupted.");
		}
	}

	protected OneWay newOneWay(Game game, IOHelper data) throws CouldNotLoadFileException {
		return new OneWay(data.getPosition(), Direction.getTurn(data.getData(4)));
	}

	protected Tile newHole(Game game, IOHelper data) throws CouldNotLoadFileException {
		return new Hole(data.getPosition());
	}

	protected Tile newWall(Game game, IOHelper data) throws CouldNotLoadFileException {
		return new Wall(data.getPosition());
	}

	protected Tile newTarget(Game game, IOHelper data) throws CouldNotLoadFileException {
		return new Target(data.getPosition(), data.getColor());
	}

	protected GameObject newChaboncitou(Game game, IOHelper data) throws CouldNotLoadFileException {
		return new Chaboncitou(data.getPosition());
	}

	protected GameObject newBox(Game game, IOHelper data) throws CouldNotLoadFileException {
		return new Box(data.getPosition(), data.getColor());
	}

	protected Tile newBlank(Game game, Position pos) throws CouldNotLoadFileException {
		return new Blank(pos);
	}

	/**
	 * A useful class to get information from and into a file.
	 * 
	 * 
	 * @author eordano
	 * 
	 */
	protected class IOHelper {
		private static final int lineItems = 7;
		int data[];

		IOHelper(Position pos, int type, int rotations, RGBColor col) {
			data = new int[] { pos.getX(), pos.getY(), type, rotations, col.getR(), col.getG(), col.getB() };
		}

		/**
		 * A constructor from a string of the regex form "(\d,){6}\d"
		 * 
		 * @param S
		 *            incoming data to be processed
		 * @throws Exception
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
		 * @param pos
		 *            position in the vector
		 * @return the value of data[pos]
		 */
		public int getData(int pos) {
			return data[pos];
		}

		/**
		 * Cast into a position of data stored in the first two elements of the
		 * data array.
		 * 
		 * @return a Position from the data obtained
		 */
		public Position getPosition() {
			return new Position(data[1], data[0]);
		}

		/**
		 * Get the color element encoded in the string, in the last three
		 * elements
		 * 
		 * @return a Color from the data obtained
		 */
		public RGBColor getColor() {
			return new RGBColor(data[4], data[5], data[6]);
		}

		/**
		 * Get a Representation of the data. A new IOHelper could be gotten with
		 * this string as constructor.
		 */
		public String toString() {
			String res = "" + data[0];
			for (int i = 1; i < data.length; i++) {
				res += "," + data[i];
			}
			return res;
		}
	}

	private class Counter {
		private int count;

		Counter() {
			count = 0;
		}

		public void increment() {
			count++;
		}

		public int getCount() {
			return count;
		}
	}
}
