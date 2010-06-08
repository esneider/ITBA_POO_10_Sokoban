package edu.itba.it.poog7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

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
	static MainFrame frame;
	GameManager manager;
	Game game;

	/**
	 * Constructor.
	 */
	Controller() {
		frame = new MainFrame();
		frame.addKeyListener(this);
		frame.setOutsiderListener(this);
		
		manager = new GameManager();

		map = new HashMap<String, getFunction>();
		map.put("New Game", new getFunction() {

			@Override
			public void call() {
				String name = JOptionPane.showInputDialog("What's your name?");
				newGame(getLevels()[0], name);
			}

		});
		map.put("Load Game", new getFunction() {

			@Override
			public void call() {
				// TODO: Ask for a file
			}

		});
		map.put("Save Game", new getFunction() {

			@Override
			public void call() {
				// TODO: Ask for a file
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

				Controller.frame.dispose();
			}
		});
	}

	/**
	 * Get the list of levels.
	 * 
	 * @return Array of level names.
	 */
	public String[] getLevels() {
		try {
			return manager.getLevelList();
		} catch (FileNotFoundException e) {
			new MessageBox("Error", "No se encontraron niveles.\n" + e, true);
			return null;
		}
	}

	/**
	 * Create a new game for a given level.
	 * 
	 * @param levelName
	 *            The name of the level name.
	 * @param userName
	 *            The user name that is playing.
	 */
	public void newGame(String levelName, String userName) {
		try {
			game = manager.loadLevel(levelName, userName);
			frame.setGame(manager.getView());
			System.out.println("Se inicializó el juego");

			game.subscribeListener(GameOverEvent.class, getGameOverListener());
			game.subscribeListener(GameFinishedEvent.class,
					getGameFinishedListener());
		} catch (CouldNotLoadFileException e) {

			frame.setNoGame();
			new MessageBox("Error", "No levels were found.\n" + e, true);
		}
	}

	/**
	 * Reset the current game.
	 */
	public void resetGame() {
		String levelFileName = game.getLevelFileName();
		String userName = game.getUserName();

		newGame(levelFileName, userName);
	}

	/**
	 * Load a saved game.
	 * 
	 * @param savedLevelName
	 *            The name of the saved game file.
	 */
	public void loadGame(String savedLevelName) {

		if (game != null) {
			frame.setNoGame();
		}
		try {
			game = manager.loadGame(savedLevelName);
			frame.setGame(manager.getView());

			game.subscribeListener(GameOverEvent.class, getGameOverListener());
			game.subscribeListener(GameFinishedEvent.class,
					getGameFinishedListener());

		} catch (CouldNotLoadFileException e) {

			frame.setNoGame();
			new MessageBox("Error", "Could not load level.\n" + e, true);
		}
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

				try {

					String nextLevel = manager.getNextLevel(game
							.getLevelFileName());
					newGame(nextLevel, game.getUserName());
				} catch (FileNotFoundException ex) {

					new MessageBox("Error", "Could not load level.", true);
				} catch (NoMoreLevelsException ex) {

					new MessageBox("Campeon!!",
							"¡Sos un titan man! ¡¡Ganaste el juego!!", false);
					frame.setNoGame();
					game = null;
				}
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
