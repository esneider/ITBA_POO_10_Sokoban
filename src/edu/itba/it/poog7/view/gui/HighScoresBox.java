package edu.itba.it.poog7.view.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import edu.itba.it.poog7.gamelogic.Highscores;

/**
 * show the user a highscores box for the current level
 * 
 */
public class HighScoresBox extends JFrame {

	private static final long serialVersionUID = 1L;

	private Highscores highScores;

	/**
	 * Show the user a table within a box, containing the top scores and who has achieved them
	 * 
	 * @param highscores
	 */
	public HighScoresBox(Highscores highscores) {

		super("Highscores");
		this.highScores = highscores;

		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (arg0.getActionCommand().toString() == "OK") {
					dispose();
				}
			}
		});

		TableModel dataModel = new AbstractTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int row, int col) {

				switch (col) {
				case 0:
					return highScores.getScores().get(row).getPlayerName();
				case 1:
					return highScores.getScores().get(row).getScore();
				default:
					return null;
				}
			}

			@Override
			public int getRowCount() {

				return highScores.getScores().size();
			}

			@Override
			public int getColumnCount() {

				return 2;
			}
		};

		JTable table = new JTable(dataModel);

		Box box = Box.createVerticalBox();

		JPanel theTable = new JPanel();
		theTable.add(table);
		box.add(theTable);

		JPanel button = new JPanel();
		button.add(ok);
		box.add(button);

		table.setBackground(null);
		table.setGridColor(getBackground());
		table.setEnabled(false);
		table.setFont(new Font(table.getFont().getName(), table.getFont().getStyle() | Font.BOLD, 15));
		table.setRowHeight(2 * table.getRowHeight());

		add(box);

		setBounds(0, 0, 250, table.getRowHeight() * table.getRowCount() + 80);
		setResizable(false);
		setVisible(true);
	}
}
