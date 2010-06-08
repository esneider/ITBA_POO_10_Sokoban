package edu.itba.it.poog7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

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
import edu.itba.it.poog7.view.gui.MainFrame;
import edu.itba.it.poog7.view.gui.MessageBox;

/**
 * Controller for the 'Model-View-Controller' pattern <br>
 * Big class to control the application flow. <br>
 * In charge of communicating things in between big objects.
 * 
 * @author eordano
 * 
 */
public class Controller implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	Map<String, getFunction> map;
	MainFrame frame;
	GameManager manager;
	Game game;

	/**
	 * Constructor.
	 */
	Controller() {
		frame = new MainFrame();
		frame.addKeyListener(this);
		frame.addOutsiderListener(this);
		
		manager = new GameManager();

		map = new HashMap<String, getFunction>();
		map.put("New Game", new getFunction() {

			@Override
			public void call() {
				String name = JOptionPane.showInputDialog("What's your name?");
				newGame(name);
			}

		});
		final FileFilter sokoFilter = new FileFilter(){
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".sok") || f.isDirectory();
			}

			@Override
			public String getDescription() {
				return "Sokoban Saved File Game (.sok)";
			}			    	
	    };
		map.put("Load Game", new getFunction() {

			@Override
			public void call() {
				JFileChooser chooser = new JFileChooser();
			    chooser.setFileFilter(sokoFilter);
			    int returnVal = chooser.showOpenDialog(frame);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	loadSavedGame(chooser.getSelectedFile().getPath());
			    }
			}

		});
		map.put("Save Game", new getFunction() {

			@Override
			public void call() {
				JFileChooser chooser = new JFileChooser();
			    chooser.setFileFilter(sokoFilter);
			    int returnVal = chooser.showSaveDialog(frame);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String path = chooser.getSelectedFile().getPath();
			    	
			    	if (!path.endsWith(".sok")) {
			    		
			    		path += ".sok";
			    	}
			    	saveGame(path);
			    }
			}

		});
		map.put("High Scores", new getFunction() {

			@Override
			public void call() {
				highScores();
			}

		});
		map.put("Restart Game", new getFunction() {

			@Override
			public void call() {

				resetGame();
			}

		});
		map.put("Exit", new getFunction() {

			@Override
			public void call() {

				frame.dispose();
			}
		});
	}

	/**
	 * Create a new game for a given level.
	 * 
	 * @param levelName The name of the level name.
	 * @param userName The user name that is playing.
	 */
	public void newGame(String userName) {
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
			
			frame.setNoGame();
			new MessageBox("Error", "The levels folder doesn't exist.", true);
		} catch (NoMoreLevelsException e) {
			
			frame.setNoGame();
			new MessageBox("Campeon!!", "¡Sos un titan man! ¡¡Ganaste el juego!!", false);
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
			
			frame.setGame(manager.getView());
		} catch (CouldNotLoadFileException e) {
			
			frame.setNoGame();
			new MessageBox("Error", "The level could not be loaded", true);
		} 
	}
	
	/**
	 * Load a saved game.
	 * 
	 * @param savedLevelName The name of the saved game file.
	 */
	public void loadSavedGame(String savedLevelName) {

		try{
			game = manager.loadGame(savedLevelName);
			
			game.subscribeListener(GameOverEvent.class, getGameOverListener());
			game.subscribeListener(GameFinishedEvent.class, getGameFinishedListener());

			frame.setGame(manager.getView());
		} catch (CouldNotLoadFileException e){

			frame.setNoGame();
			new MessageBox("Error", "Could not load level.", true);
		}	
	}

	/**
	 * Reset the current game.
	 */
	public void resetGame() {
		String levelFileName = game.getLevelFileName();
		String userName = game.getUserName();

		loadGame(levelFileName, userName);
	}

	/**
	 * @return
	 */
	private EventListener getGameFinishedListener() {

		return new EventListener() {

			@Override
			public void eventTriggered(Event e) {

				try {
					Highscores highscores = new Highscores(game
							.getLevelFileName());

					// FIXME: This throws an exception when no highscore exists
					// yet
					// highscores.addScore(game.getUserName(),
					// game.getNumMoves());
				} catch (InvalidFileException ex) {

					new MessageBox("Error",
							"Could not save your highscore. Sorry dude!", true);
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
	 * @param savedLevelName
	 *            The name of the file to save to.
	 */
	public void saveGame(String savedLevelName) {
		try {
			manager.saveGame(game, savedLevelName);
		} catch (Exception e) {
			new MessageBox("Error", "Could not save the game.\n" + e, true);
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
		if (map.containsKey(e.getActionCommand().toString())) {
			map.get(e.getActionCommand().toString()).call();
		}
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Controller();
	}

	/**
	 * Watch for keys being pressed: <br />
	 * 
	 * - Arrow keys for the Character - Ctrl+R to restart the game
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
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
			if ((e.getModifiers() & KeyEvent.CTRL_MASK) == KeyEvent.CTRL_MASK) {
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
