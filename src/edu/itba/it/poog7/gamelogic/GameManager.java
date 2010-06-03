/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

import edu.itba.it.poog7.gamelogic.Color;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.objects.Chaboncitou;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.Hole;
import edu.itba.it.poog7.gamelogic.tiles.OneWay;
import edu.itba.it.poog7.gamelogic.tiles.Target;
import edu.itba.it.poog7.gamelogic.tiles.Tile;
import edu.itba.it.poog7.gamelogic.tiles.Wall;

//import java.util.logging.Level;

/**
 * A kind-of-imperative class in charge of loading levels and interacting with the filesystem.
 * 
 * Its heavier-load methods returns instances of Game.
 * 
 */
public class GameManager {
	Game newGame;
	
	public String[] getLevelList() {
		File dir = new File("/levels/");
		File[] files = dir.listFiles(new FileFilter(){
			public boolean accept(File file){
				return file.getName().endsWith(".txt");
			}
		});
		String[] levels = new String[files.length];
		for (int i = 0; i < files.length; i++){
			levels[i] = files[i].getName();
		}
		return levels;
	}

	public String getNextLevel(String current) {
		String[] levelList = getLevelList();
		if (current == ""){
			return levelList[0];
		}
		int indexOfCurrent = indexOf(levelList, "current");
		return levelList[indexOfCurrent+1];
	}

	private int indexOf(String[] levelList, String string) {
		for(int i = 0; i < levelList.length; i++)
			if (levelList[i] == string)
				return i;
		return levelList.length;
	}

	public Game loadGame(String name){
		FileInputStream file;
		try{
			file = new FileInputStream(new File("/levels/"+name));
		} catch (FileNotFoundException FnF){
			System.out.print("Error reading file:\nFile not found.\n");
			return null;
		}
		String newLine;
		newGame = new Game();
		while((newLine = readLine(file)) == "");
		newGame.setLevelName(newLine);
		while((newLine = readLine(file)) == "");
		newGame.setUserName(newLine);
		while((newLine = readLine(file)) == "");
		newGame.setScore(Integer.parseInt(newLine));
		while((newLine = readLine(file)) == "");
		int width, height;
		width = Integer.parseInt(newLine.split(",")[0]);
		height = Integer.parseInt(newLine.split(",")[1]);
		newGame.setSize(width, height);
		
		loadState(file);
		return newGame;
	}

	public void saveGame(Game game, String saveFileName){
		FileOutputStream file = null;
		try{
			file = new FileOutputStream(new File("/levels/"+saveFileName));
			PrintStream out = new PrintStream(file);
			out.println(game.getLevelName());
			out.println(game.getUserName());
			out.println(game.getNumMoves());
			out.println(game.getWidth() + " " + game.getHeight());
			for(int i = 0; i < game.getHeight(); i++){
				for(int j = 0; j < game.getWidth(); j++){
					outputTile(out, game.getTile(new Position(i,j)));
				}
			}
		} catch (IOException aIOEx){
			System.out.print("Error writing into file:\n"+saveFileName+"\n");
		}
	}

	public Game loadLevel(String levelName, String userName){
		FileInputStream file;
		try{
			file = new FileInputStream(new File("/levels/"+levelName));
		} catch (FileNotFoundException FnF){
			System.out.print("Error reading file:\nFile not found.\n");
			return null;
		}
		String newLine;
		newGame = new Game();
		newGame.setUserName(userName);
		while((newLine = readLine(file)) == "");
		newGame.setLevelName(newLine);
		while((newLine = readLine(file)) == "");
		int width, height;
		width = Integer.parseInt(newLine.split(",")[0]);
		height = Integer.parseInt(newLine.split(",")[1]);
		newGame.setSize(width, height);
		newGame.setScore(0);
		
		loadState(file);
		return newGame;
	}
	
	private void loadState(FileInputStream file){
		String newLine; 
		Queue<Tile> tileQueue = new LinkedList<Tile>();
		Queue<GameObject> objectQueue = new LinkedList<GameObject>();
		while((newLine = readLine(file)) != "EOF"){
			computeLine(newLine, tileQueue, objectQueue);
		}
		while(!tileQueue.isEmpty()){
			newGame.setTile(tileQueue.poll());
		}
		while(!objectQueue.isEmpty()){
			newGame.setObject(objectQueue.poll());
		}
	}

	private String readLine(FileInputStream file) {
		StringBuffer line = new StringBuffer();
		int got;
		try{
			while((got = file.read()) != -1){
				if (got == ((int) '\n')){
					break;
				}
				line.append(got);
			}
			if (got == -1){
				return "EOF";
			}
		} catch (IOException ioerror){
			return "EOF";
		}
		while (line.charAt(0) == ' ' || line.charAt(0) == '\t') line.deleteCharAt(0);
		if (line.length() == 0 || line.charAt(0) == '#'){
			return "";
		}
		return line.toString();
	}
	
	private void computeLine(String newLine, Queue<Tile> tileQueue, Queue<GameObject> objectQueue) {
		IOHelper data = new IOHelper(newLine);
		if (data.getData(2) < 3){ // An object
			objectQueue.add(readObject(data));
		}
		else{
			tileQueue.add(readTile(data));
		}
	}

	private Tile readTile(IOHelper data) {
		if (data.getData(2) == 3)
			return newTarget(data);
		if (data.getData(2) == 4)
			return newWall(data);
		if (data.getData(2) == 5)
			return newHole(data);
		if (data.getData(2) == 6)
			return newOneWay(data);
		return null;
	}
	
	private GameObject readObject(IOHelper data) {
		if (data.getData(2) == 1)
			return newChaboncitou(data);
		if (data.getData(2) == 2)
			return newBox(data);
		return null;
	}

	private OneWay newOneWay(IOHelper data) {
		return new OneWay(data.getPosition(), Direction.getTurn(data.getData(4)));
	}

	private Tile newHole(IOHelper data) {
		return new Hole(data.getPosition());
	}

	private Tile newWall(IOHelper data) {
		return new Wall(data.getPosition());
	}

	private Tile newTarget(IOHelper data) {
		return new Target(data.getPosition(), data.getColor());
	}

	private GameObject newChaboncitou(IOHelper data) {
		// TODO CHECK FOR ANOTHER CHABONCITOU
		return new Chaboncitou(data.getPosition());
	}

	private GameObject newBox(IOHelper data) {
		// TODO INCREMENT NUMBER OF BOXES?
		return new Box(data.getPosition(), data.getColor());
	}
	
	private void outputTile(PrintStream out, Tile tile) {
		if (tile instanceof Wall){
			printTile(out, (Wall)tile);
		}
		if (tile instanceof Hole){
			printTile(out, (Hole)tile);
		}
		if (tile instanceof Target){
			printTile(out, (Target)tile);
		}
		if (tile instanceof OneWay){
			printTile(out, (OneWay)tile);
		}
		if (tile.getObject() != null){
			printObject(out, tile.getObject());
		}
	}

	private void printObject(PrintStream out, GameObject object) {
		if (object instanceof Chaboncitou){
			printChaboncitou(out, (Chaboncitou)object);
		}
		if (object instanceof Box){
			printBox(out, (Box)object);
		}
	}

	private void printChaboncitou(PrintStream out, Chaboncitou object) {
		out.println(new IOHelper(object.getPos(), 1, 0, new Color(0,0,0)));
	}
	private void printBox(PrintStream out, Box object) {
		out.println(new IOHelper(object.getPos(), 2, 0, object.getColor()));
	}
	private void printTile(PrintStream out, Target tile) {
		out.println(new IOHelper(tile.getPos(), 3, 0, tile.getColor()));
	}
	private void printTile(PrintStream out, Wall tile) {
		out.println(new IOHelper(tile.getPos(), 4, 0, new Color(0,0,0)));
	}
	private void printTile(PrintStream out, Hole tile) {
		out.println(new IOHelper(tile.getPos(), 5, 0, new Color(0,0,0)));
	}
	private void printTile(PrintStream out, OneWay tile) {
		out.println(new IOHelper(tile.getPos(), 6, tile.getDirection().getInt(), new Color(0,0,0)));
	}
	
	private class IOHelper{
		int data[];
		IOHelper(Position pos, int a, int b, Color col){
			data = new int[]{
					pos.getX(),
					pos.getY(),
					a,
					b,
					col.getR(),
					col.getG(),
					col.getB()
			};
		}
		IOHelper(String S){
			String[] split = S.split(",");
			data = new int[split.length];
			for(int i = 0; i < split.length; i++){
				data[i] = Integer.parseInt(split[i]);
			}
		}
		public int getData(int pos){
			return data[pos];
		}
		public Position getPosition(){
			return new Position(data[0], data[1]);
		}
		public Color getColor(){
			return new Color(data[4], data[5], data[6]);
		}
		public String toString(){
			String res = "";
			for(int i = 0; i+1 < data.length; i++){
				res += data[i] + ",";
			}
			res += data[data.length-1];
			return res; 
		}
	}
}
