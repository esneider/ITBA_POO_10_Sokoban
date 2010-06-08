package edu.itba.it.poog7.view.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import edu.itba.it.gui.ImageUtils;
import edu.itba.it.poog7.view.View;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 10098415897L;
	private static final int SCREEN_SIDE = 640;
	MyMenu menu;
	View view;
	Container pane;
	Container background;

	public MainFrame() {
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

		background = new Background();
		setVisible(true);
		setSize(new Dimension(SCREEN_SIDE, SCREEN_SIDE));
		setNoGame();
	}

	public void setNoGame() {
		pane.removeAll();
		pane.add(background, 0);
		
		if (view != null){
			view.clearImage();
		}
		
		menu.savegame.setEnabled(false);
		menu.restart.setEnabled(false);
	}

	public void setGame(View aView) {

		view = aView;

		int margenX = (pane.getWidth() - view.getWidth()) / 2;
		int margenY = (pane.getHeight() - view.getHeight()) / 2;
		
		pane.removeAll();
		pane.add(view, 0);
		pane.add(background, 1);
		
		view.setBounds(margenX, margenY, view.getWidth(), view.getHeight());

		menu.savegame.setEnabled(true);
		menu.restart.setEnabled(true);
	}

	public void addOutsiderListener(ActionListener outsider) {
		if (menu != null) {
			menu.newgame.addActionListener(outsider);
			menu.loadgame.addActionListener(outsider);
			menu.savegame.addActionListener(outsider);
			menu.restart.addActionListener(outsider);
			menu.highscores.addActionListener(outsider);
			menu.exit.addActionListener(outsider);
		}
	}

	private class MyMenu extends JMenuBar {
		private static final long serialVersionUID = 969388L;
		JMenu game;
		JMenuItem newgame;
		JMenuItem loadgame;
		JMenuItem savegame;
		JMenuItem restart;
		JMenuItem highscores;
		JMenuItem exit;

		MyMenu() {
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

	private class Background extends JPanel {
		private static final long serialVersionUID = 21L;
		private Image background;

		/**
		 * Constructor.
		 */
		Background() {
			try {
				background = ImageUtils.loadImage("resources/background.png");
			} catch (IOException e) {
				new MessageBox("Error", "Could not load background image.\n" + e, true);
			}
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(background, 0, 0, null);
		}
	}

}
