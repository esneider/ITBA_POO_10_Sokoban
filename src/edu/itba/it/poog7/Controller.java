package edu.itba.it.poog7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.itba.it.poog7.event.Event;
import edu.itba.it.poog7.event.EventListener;
import edu.itba.it.poog7.gamelogic.Direction;
import edu.itba.it.poog7.gamelogic.Game;
import edu.itba.it.poog7.gamelogic.event.GameOverEvent;
import edu.itba.it.poog7.gamelogic.exception.CouldNotLoadFileException;
import edu.itba.it.poog7.view.GameManager;
import edu.itba.it.poog7.view.gui.ErrorBox;
import edu.itba.it.poog7.view.gui.MenuBar;
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
				final PromptBox name = new PromptBox("Ingrese nombre de usuario");
				name.setCallback(new getFunction() {
					
					@Override
					public void call() {

						newGame(getLevels()[0], name.getName());
						name.dispose();
					}
				});
				name.setVisible(true);
			}

		});
		map.put("Load Game", new getFunction() {

			@Override
			public void call() {
				JFileChooser fileChoose = new JFileChooser();
				fileChoose.showOpenDialog(null);
				loadGame(fileChoose.getSelectedFile().getName());
			}

		});
		map.put("Save Game", new getFunction() {

			@Override
			public void call() {
				JFileChooser fileChoose = new JFileChooser();
				fileChoose.showSaveDialog(null);
				saveGame(fileChoose.getSelectedFile().getName());
			}

		});
		map.put("High Scores", new getFunction() {

			@Override
			public void call() {
				highScores();
			}

		});
		map.put("Reset", new getFunction() {

			@Override
			public void call() {
				newGame(game.getLevelName(), game.getUserName());
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
	 * Get the list of levels.
	 * 
	 * @return Array of level names.
	 */
	public String[] getLevels(){
		try{
			return manager.getLevelList();
		} catch (FileNotFoundException e){
			new ErrorBox("No se encontraron niveles.\n"+e);
			gamePanel.setVisible(false);
			return null;
		}
	}

	/**
	 * Create a new game for a given level.
	 * 
	 * @param levelName The name of the level name.
	 * @param userName The user name that is playing.
	 */
	public void newGame(String levelName, String userName) {
		try {
			game = manager.loadLevel(levelName, userName);
			game.subscribeListener(GameOverEvent.class, getGameOverListener());
			gamePanel.setVisible(true);
		} catch (CouldNotLoadFileException e) {
			e.printStackTrace();
			new ErrorBox("No levels were found.\n"+e);
			gamePanel.setVisible(false);
		}
	}

	/**
	 * Reset the current game.
	 */
	public void resetGame() {
		try{
			game = manager.loadLevel(game.getLevelFileName(), game.getUserName());
			gamePanel.setVisible(true);
		} catch (CouldNotLoadFileException e){
			new ErrorBox("Could restart level.\n"+e);
		}
	}

	/**
	 * Load a saved game.
	 * 
	 * @param savedLevelName The name of the saved game file.
	 */
	public void loadGame(String savedLevelName) {
		try{
			game = manager.loadGame(savedLevelName);
			game.subscribeListener(GameOverEvent.class, getGameOverListener());
			gamePanel.setVisible(true);
		} catch (CouldNotLoadFileException e){
			e.printStackTrace();
			gamePanel.setVisible(false);
			new ErrorBox("Could not load level.\n"+e);
		}	
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
			new ErrorBox("Could not load the saved game.\n"+e);
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
	 * - Arrow keys for the Chaboncitou
	 * - Ctrl+R to restart the game
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			game.moveChaboncitou(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			game.moveChaboncitou(Direction.RIGHT);
			break;
		case KeyEvent.VK_UP:
			game.moveChaboncitou(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			game.moveChaboncitou(Direction.DOWN);
			break;
		case KeyEvent.VK_R:
			if ((e.getModifiers() & KeyEvent.SHIFT_DOWN_MASK) != 0){
				resetGame();
			}
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
