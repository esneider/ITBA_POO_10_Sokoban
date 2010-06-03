package edu.itba.it.poog7.controller_;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import edu.itba.it.poog7.gamelogic_.Direction;
import edu.itba.it.poog7.gamelogic_.Game;
import edu.itba.it.poog7.gamelogic_.GameManager;
import edu.itba.it.poog7.view_.View;

/**
 * Controller for the 'Model-View-Controller' pattern
 * 
 * @author dario
 *
 */
public class Controller implements KeyListener, WindowStateListener {

	private View view;
	private Game game;
	
	/**
	 * Instance a {@link Controller}
	 */
	public Controller() {
		// TODO
		view = new View(10, 10, 30, 30*10+40, 30*10+40);
		game = GameManager.loadNext("");
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO
		if (ke.isControlDown() && ke.getKeyChar() == 'r') {
			game = GameManager.loadCurrent(game.getLevelName());
			return;
		}
		Direction dir = null;
		switch(ke.getKeyCode()){
		case KeyEvent.VK_UP: dir = Direction.UP; break;
		case KeyEvent.VK_RIGHT: dir = Direction.RIGHT; break;
		case KeyEvent.VK_DOWN: dir = Direction.DOWN; break;
		case KeyEvent.VK_LEFT: dir = Direction.LEFT; break;
		}
		if (dir != null) {
			game.moveChaboncitou(dir);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowStateChanged(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
