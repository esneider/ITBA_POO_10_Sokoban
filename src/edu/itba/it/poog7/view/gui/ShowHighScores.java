package edu.itba.it.poog7.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.GameManager;
import edu.itba.it.poog7.gamelogic.Highscores;
import edu.itba.it.poog7.gamelogic.Highscores.Score;
import edu.itba.it.poog7.gamelogic.exception.CouldNotLoadFileException;
import edu.itba.it.poog7.gamelogic.exception.NoMoreLevelsException;

/**
 * Show highscores
 * 
 */
public class ShowHighScores extends JFrame implements ActionListener {
	private static final long serialVersionUID = 197239L;
	GameManager manager;

	public ShowHighScores(String levelName, GameManager manager) {
		super("Highscores");
		this.setSize(600, 600);

		setLayout(new BorderLayout());
		this.manager = manager;
		showScores(levelName);

		this.setVisible(true);
	}

	private void showScores(final String levelName) {
		Header header = new Header(levelName);
		ScoreBoard board = new ScoreBoard(levelName);
		JButton reset = new JButton("Reset scores for level " + levelName);

		getContentPane().add(header, BorderLayout.PAGE_START);
		getContentPane().add(board, BorderLayout.CENTER);
		getContentPane().add(reset, BorderLayout.PAGE_END);

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int confirmation = JOptionPane
						.showConfirmDialog(ShowHighScores.this,
								"Are you sure?", "Delete scores for "
										+ levelName, JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (confirmation == JOptionPane.YES_OPTION) {
					try {
						new Highscores(manager.getLevelNamesMap()
								.get(levelName)).erase();
						new ShowHighScores(levelName, manager);
						ShowHighScores.this.dispose();
					} catch (CouldNotLoadFileException except) {
						new MessageBox("Error", "Couldn't erase highscores",
								true);
					}
				}
			}
		});
	}

	private class Header extends JPanel {
		private static final long serialVersionUID = 163143L;

		Header(String levelName) {
			LayoutManager borderLayout = new BorderLayout();
			setLayout(borderLayout);

			JButton a = new JButton(levelName);
			add(a, BorderLayout.PAGE_END);
			a.setEnabled(false);
			a.setBorderPainted(false);
			a.setFont(new Font("Serif", 0, 40));

			String before = null;
			String after = null;
			try {
				before = manager.getPreviousLevel(levelName);
			} catch (NoMoreLevelsException e) {
				;
			} catch (CouldNotLoadFileException e) {
				;
			}
			if (before != null) {
				JButton backButton = new JButton(before);
				backButton.setMaximumSize(new Dimension(50, 50));
				add(backButton, BorderLayout.LINE_START);
				backButton.addActionListener(ShowHighScores.this);
			}

			try {
				after = manager.getNextLevel(levelName);
			} catch (NoMoreLevelsException e) {
				;
			} catch (CouldNotLoadFileException e) {
				;
			}

			if (after != null) {
				JButton forwardButton = new JButton(after);
				forwardButton.setMaximumSize(new Dimension(50, 50));
				add(forwardButton, BorderLayout.LINE_END);
				forwardButton.addActionListener(ShowHighScores.this);
			}
		}
	}

	private class ScoreBoard extends JPanel {
		private static final long serialVersionUID = 2404379151L;

		ScoreBoard(String levelName) {
			LinkedList<Score> scores = null;
			try {
				scores = new Highscores(manager.getLevelNamesMap().get(
						levelName)).getScores();
			} catch (CouldNotLoadFileException e) {
				;
			}

			LayoutManager gridLayout = new GridLayout(10, 2);
			this.setLayout(gridLayout);

			if (scores != null) {
				int i = 10;
				for (Score s : scores) {
					JButton name = new JButton(s.getPlayerName() + " - "
							+ new Integer(s.getScore()).toString());
					name.setFont(new Font("Serif", Font.PLAIN, 20+(i--)));
					name.setEnabled(false);
					name.setBorderPainted(false);
					this.add(name);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new ShowHighScores(e.getActionCommand(), manager);
		dispose();
	}
}
