/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.Tile;

//import java.util.logging.Level;

/**
 * A kind-of-imperative class in charge of loading levels and interacting with the filesystem.
 * 
 * Its heavier-load methods returns instances of Game.
 * 
 */
public class GameManager {
	
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
		Game newGame = new Game();
		while((newLine = readLine(file)) == "");
		newGame.setLevelName(newLine);
		while((newLine = readLine(file)) == "");
		int width, height;
		width = Integer.parseInt(newLine.split(",")[0]);
		height = Integer.parseInt(newLine.split(",")[1]);
		newGame.setSize(width, height);
		
		loadState(newGame, file);
		return newGame;
	}
	
	private void loadState(Game newGame, FileInputStream file){
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
	
	private void computeLine(String newLine, Queue<Tile> tileQueue, Queue<GameObject> objectQueue) {
		
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
		// TODO: ESTO TODAVIA ES UN STUB!!!
		return null;
	}

	public Game saveGame(Game game) {
		// TODO
		return null;
	}
	
	public Game loadLevel(String levelName, String userName){
		// TODO
		return null;
	}
}
