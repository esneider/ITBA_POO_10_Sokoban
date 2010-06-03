package edu.itba.it.poog7;

import edu.itba.it.poog7.gamelogic.GameManager;
import edu.itba.it.poog7.gamelogic.Game;

/**
 * Controller for the 'Model-View-Controller' pattern
 * <br>
 * Big class to control the application flow.
 * <br>
 * In charge of comunicating things in between big objects.
 * 
 * @author eordano
 *
 */
public class Controller{
	GameManager manager;
	Game game;
	
	Controller(){
		//MainMenu aMenu;
		//aMenu.show();
		// TODO: add more things to the MainMenu
		// TODO: add listeners to trigger new games on clicks on the menu
	}
	
	public String[] getLevels(){
		return manager.getLevelList();
	}
	
	public void NewGame(String levelName, String userName){
		//TODO: Kill active game, maybe ask to save game? update highscores?
		game = manager.loadLevel("levelName", userName);
		//TODO: Display GameFrame? GameBoard?
	}
	
	public void ResetGame(){
		game = manager.loadLevel(game.getLevelName(), game.getUserName());
	}
	
	public void LoadGame(String savedLevelName){
		game = manager.loadGame(savedLevelName);
	}

	public void SaveGame(String savedLevelName){
		manager.saveGame(game);
	}
	
	public void HighScores(){
		//TODO: HighScores Dialog
	}
}
