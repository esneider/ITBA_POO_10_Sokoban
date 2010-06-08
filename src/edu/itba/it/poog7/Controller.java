package edu.itba.it.poog7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.Highscores;
import edu.itba.it.poog7.gamelogic.event.GameFinishedEvent;
import edu.itba.it.poog7.gamelogic.event.GameOverEvent;
import edu.itba.it.poog7.gamelogic.exception.CouldNotLoadFileException;
import edu.itba.it.poog7.gamelogic.exception.InvalidFileException;
import edu.itba.it.poog7.gamelogic.exception.NoMoreLevelsException;
import edu.itba.it.poog7.view.GameManager;
import edu.itba.it.poog7.view.gui.MenuBar;
import edu.itba.it.poog7.view.gui.MessageBox;
import edu.itba.it.poog7.view.gui.PromptBox;

/**
 * Controller for the 'Model-View-Controller' pattern <br>
 * Big class to control the application flow. <br>
 * In charge of communicating things in between big objects.
 * 
 * @author eordano
 * 
 */
public class Controller extends JFrame implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private static final int SCREEN_SIDE = 640;
	
	Map<String, getFunction> map;
	JPanel gamePanel;
	GameManager manager;
	Game game;
	MenuBar menu;


	/**
	 * Constructor.
	 */
	Controller() {
		super();
		setTitle("Sokoban - POO Group 7");
		setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(0, 0, SCREEN_SIDE, SCREEN_SIDE);
		
        gamePanel = new JPanel();
        this.add(gamePanel);
        
        manager = new GameManager();
        manager.setPanel(gamePanel);

        MenuBar menu = new MenuBar(this);
        this.setJMenuBar(menu);
        menu.setVisible(true);
        
		map = new HashMap<String, getFunction>();
		map.put("New Game", new getFunction() {

			@Override
			public void call(){
				final PromptBox name = new PromptBox("Input the player name: ");
				name.setCallback(new getFunction() {
					
					@Override
					public void call() {

						newGame(name.getValue());
						name.dispose();
					}
				});
				name.setVisible(true);
			}

		});
		map.put("Load Game", new getFunction() {

			@Override
			public void call() {
				final PromptBox fileName = new PromptBox("Input the file name: ");
				fileName.setCallback(new getFunction() {
					
					@Override
					public void call() {

						loadSavedGame(fileName.getValue());
						fileName.dispose();
					}
				});
				fileName.setVisible(true);
			}

		});
		map.put("Save Game", new getFunction() {

			@Override
			public void call() {
				if (null == game) {
					new MessageBox("Error", "You cant save a file if youre not playing.");
					return;
				}
				
				final PromptBox fileName = new PromptBox("Input the file name: ");
				fileName.setCallback(new getFunction() {
					
					@Override
					public void call() {

						saveGame(fileName.getValue());
						fileName.dispose();
					}
				});
				fileName.setVisible(true);
			}

		});
		map.put("High Scores", new getFunction() {

			@Override
			public void call() {
				highScores();
			}

		});
		map.put("Restart", new getFunction() {

			@Override
			public void call() {
				
				resetGame();
			}

		});
		map.put("Quit", new getFunction() {

			@Override
			public void call() {
				
				dispose();
			}
		});
		
		addKeyListener(this);
	}

	/**
	 * Create a new game for a given level.
	 * 
	 * @param levelName The name of the level name.
	 * @param userName The user name that is playing.
	 */
	public void newGame(String userName) {
		if (null != game) {
			
			resetPanel();
			game = null;
		}

		loadNextLevel("", userName);
	}

	/**
	 * Load the level after a given one.
	 * @param currentLevel The name of the level thats previous to the level to load.
	 * @param userName The name of the user playing.
	 */
	private void loadNextLevel(String currentLevel, String userName) {

		try {
			loadGame(manager.getNextLevel(currentLevel), userName);
		} catch (FileNotFoundException e) {
			
			new MessageBox("Error", "The levels folder doesn't exist.");
		} catch (NoMoreLevelsException e) {
			
			new MessageBox("Campeon!!", "¡Sos un titan man! ¡¡Ganaste el juego!!");
			resetPanel();
			game = null;
		}
	}

	/**
	 * Load a game level.
	 * 
	 * @param levelName The name of the level to load.
	 * @param userName The name of the user playing the level.
	 */
	private void loadGame(String levelName, String userName) {
		try {
			game = manager.loadLevel(levelName, userName);
			
			game.subscribeListener(GameOverEvent.class, getGameOverListener());
			game.subscribeListener(GameFinishedEvent.class, getGameFinishedListener());
			
			gamePanel.repaint();
		} catch (CouldNotLoadFileException e) {
			
			resetPanel();
			new MessageBox("Error", "The level could not be loaded");
		} 
	}

	/**
	 * Reset the current game.
	 */
	public void resetGame() {
		String levelFileName = game.getLevelFileName();
		String userName = game.getUserName();
		
		resetPanel();
		loadGame(levelFileName, userName);
		
		gamePanel.repaint();
	}

	/**
	 * Load a saved game.
	 * 
	 * @param savedLevelName The name of the saved game file.
	 */
	public void loadSavedGame(String savedLevelName) {
		
		if (null != game) {
			
			resetPanel();
			game = null;
		}
		try{
			game = manager.loadGame(savedLevelName);
			
			game.subscribeListener(GameOverEvent.class, getGameOverListener());
			game.subscribeListener(GameFinishedEvent.class, getGameFinishedListener());
			
		} catch (CouldNotLoadFileException e){

			e.printStackTrace();
			resetPanel();
			
			new MessageBox("Error", "Could not load level.\n"+e);
		}	
	}

	/**
	 * Reset the panel that holds the game.
	 */
	private void resetPanel() {
		gamePanel.removeAll();
		gamePanel.repaint();
	}

	/**
	 * @return
	 */
	private EventListener getGameFinishedListener() {
		
		return new EventListener() {
			
			@Override
			public void eventTriggered(Event e) {

				try {
					Highscores highscores = new Highscores(game.getLevelFileName());
					
					//FIXME: This throws an exception when no highscore exists yet
//					highscores.addScore(game.getUserName(), game.getNumMoves());
				} catch (InvalidFileException ex) {
					
					new MessageBox("Error", "Could not save your highscore. Sorry dude!");
				}

				loadNextLevel(game.getLevelFileName(), game.getUserName());
			}
		};
	}

	private EventListener getGameOverListener() {
		
		return new EventListener() {
			
			@Override
			public void eventTriggered(Event e) {

				resetGame();
			}
		};
	}

	/**
	 * Save the current game to a file.
	 * 
	 * @param savedLevelName The name of the file to save to.
	 */
	public void saveGame(String savedLevelName){
		try{
			manager.saveGame(game, savedLevelName);
		} catch (IOException e){
			new MessageBox("Error", "Could not save the game.\n"+e);
		}
	}

	/**
	 * Show the highscores dialog.
	 */
	public void highScores() {
		// TODO: HighScores Dialog
	}

	/**
	 * @author dario
	 *
	 */
	public abstract class getFunction {
		/**
		 * Call the function. 
		 */
		public abstract void call();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(map.containsKey(e.getActionCommand().toString())){
			map.get(e.getActionCommand().toString()).call();
		}
	}
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		new Controller();
	}

	/**
	 * Watch for keys being pressed: <br />
	 * 
	 * - Arrow keys for the Character
	 * - Ctrl+R to restart the game
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			game.moveCharacter(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			game.moveCharacter(Direction.RIGHT);
			break;
		case KeyEvent.VK_UP:
			game.moveCharacter(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			game.moveCharacter(Direction.DOWN);
			break;
		case KeyEvent.VK_R:
			if ((e.getModifiers() & KeyEvent.CTRL_MASK) == KeyEvent.CTRL_MASK){
				resetGame();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Method not used from the interface KeyListener
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// Pass
		
	}

	/**
	 * Method not used from the interface KeyListener
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// Pass
	}
}
