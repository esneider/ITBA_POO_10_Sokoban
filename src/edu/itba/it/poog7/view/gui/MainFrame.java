package edu.itba.it.poog7.view.gui;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.OverlayLayout;

import edu.itba.it.poog7.view.View;


public class MainFrame extends JFrame{
	private static final long serialVersionUID = 10098415897L;
	private static final int SCREEN_SIDE = 600;
	MyMenu menu;
	View view;
	Container pane;
	Container background;
	
	public MainFrame(){
		super("Sokoban");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
		
		// This is the container of the Frame
		pane = getContentPane();
		pane.setBackground(Color.black);
		
		// Set up the menu
		menu = new MyMenu();
		setJMenuBar(menu);
		
		// Set up the layout
	    LayoutManager overlay = new OverlayLayout(pane);
	    pane.setLayout(overlay);

		setSize(new Dimension(SCREEN_SIDE,SCREEN_SIDE));
		setVisible(true);
		setNoGame();
	}
	
	public void setNoGame(){
		
		if (view != null){
			view.setVisible(false);
			view = null;
		}
		menu.savegame.setEnabled(false);
		menu.restart.setEnabled(false);
	}
	
	public void setGame(View aView){
		
		view = aView;

		int margenX = (pane.getWidth()-view.getWidth())/2;
		int margenY = (pane.getHeight()-view.getHeight())/2;
		
		pane.add(view);
		view.setBounds(margenX, margenY, view.getWidth(), view.getHeight());
		
		menu.savegame.setEnabled(true);
		menu.restart.setEnabled(true);
	}
	
	public void setOutsiderListener(ActionListener outsider){
		if (menu != null){
			menu.newgame.addActionListener(outsider);
			menu.loadgame.addActionListener(outsider);
			menu.savegame.addActionListener(outsider);
			menu.restart.addActionListener(outsider);
			menu.highscores.addActionListener(outsider);
			menu.exit.addActionListener(outsider);
		}
	}
	
	private class MyMenu extends JMenuBar{
		private static final long serialVersionUID = 969388L;
		JMenu game;
		JMenuItem newgame;
		JMenuItem loadgame;
		JMenuItem savegame;
		JMenuItem restart;
		JMenuItem highscores;
		JMenuItem exit;
		
		MyMenu(){
			super();
			game = new JMenu("Game");
			
			newgame = new JMenuItem("New Game");
			game.add(newgame);
			
			loadgame = new JMenuItem("Load Game");
			game.add(loadgame);

	        game.addSeparator();
			
	        savegame = new JMenuItem("Save Game");
			game.add(savegame);
			
			restart = new JMenuItem("Restart Game");
			game.add(restart);

	        game.addSeparator();
	        
			highscores = new JMenuItem("High Scores");
			game.add(highscores);
			
			exit = new JMenuItem("Exit");
			game.add(exit);
			
			add(game);
		}
	}
}
