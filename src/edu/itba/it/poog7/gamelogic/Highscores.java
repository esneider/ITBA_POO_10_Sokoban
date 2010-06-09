package edu.itba.it.poog7.gamelogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.ListIterator;

import edu.itba.it.poog7.gamelogic.exception.CouldNotLoadFileException;
import edu.itba.it.poog7.gamelogic.exception.CouldNotSaveFileException;

/**
 * Class used to maintain the highscores for a given level
 * 
 */
public class Highscores {

	private static final int MAX_SCORES = 10;
	private LinkedList<Score> scores;
	private String fileName;

	/**
	 * Instance a {@link Highscores}
	 * 
	 * @param levelFileName
	 *            the level filename
	 * @throws CouldNotLoadFileException
	 */
	public Highscores(String levelFileName) throws CouldNotLoadFileException {

		this.fileName = levelFileName.substring(0, levelFileName.length()-4) + ".score";
		loadScores();
	}

	/**
	 * Load the scores from a file
	 * 
	 * @throws CouldNotLoadFileException
	 */
	protected void loadScores() throws CouldNotLoadFileException {

		BufferedReader file;
		scores = new LinkedList<Score>();
		
		try {
			file = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			return;
		}

		String line;
		try {
			while ((line = file.readLine()) != null) {
				scores.add(parseLine(line));
			}
		} catch (IOException e) {

			throw new CouldNotLoadFileException("Cound not load highscores file.");
		}
	}

	/**
	 * Helper method to parse lines
	 * 
	 * @param line
	 *            the line
	 * @return a {@link Score}
	 * @throws CouldNotLoadFileException
	 */
	protected Score parseLine(String line) throws CouldNotLoadFileException {

		String s[] = line.split(",");
		if (s.length < 2) {
			throw new CouldNotLoadFileException("The highscores file is corrupted.");
		}

		int score;
		try {
			score = Integer.parseInt(s[0]);
		} catch (NumberFormatException e) {
			throw new CouldNotLoadFileException("The highscores file is corrupted.");
		}

		return new Score(score, line.substring(line.indexOf(',')+1));
	}

	/**
	 * Add a new pair score<->user.
	 * 
	 * @param playerName
	 *            the user name
	 * @param scored
	 *            the score
	 * @return True if the score entered the high scores, false otherwise
	 * @throws CouldNotSaveFileException
	 */
	public boolean addScore(String playerName, int scored) throws CouldNotSaveFileException {

		Score score = new Score(scored, playerName);

		if (scores.size() < MAX_SCORES || score.compareTo(scores.getLast()) < 0) {

			ListIterator<Score> it = scores.listIterator();
			while (it.hasNext() && it.next().compareTo(score) <= 0)
				;

			if (it.hasNext() || (it.hasPrevious() && score.compareTo(scores.getLast()) < 0)) {
				it.previous();
			}

			it.add(score);

			if (scores.size() > MAX_SCORES) {
				scores.removeLast();
			}

			saveScores();
			return true;
		}
		return false;
	}

	/**
	 * Save the scores to a file
	 * 
	 * @throws CouldNotSaveFileException
	 */
	protected void saveScores() throws CouldNotSaveFileException {

		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			throw new CouldNotSaveFileException("Could not save the highscores");
		}

		for (Score s : scores) {
			out.println(s);
		}
	}

	/**
	 * Helper class to maintain pairs of score<->user name
	 * 
	 */
	public class Score implements Comparable<Score> {

		private int score;
		private String playerName;

		/**
		 * Instanciate a {@link Score}
		 * 
		 * @param score
		 *            the score
		 * @param playerName
		 *            the user name
		 */
		public Score(int score, String playerName) {

			this.score = score;
			this.playerName = playerName;
		}

		/**
		 * @return the score
		 */
		public int getScore() {

			return score;
		}

		/**
		 * @return the user name
		 */
		public String getPlayerName() {

			return playerName;
		}

		@Override
		public int compareTo(Score arg0) {

			return score - arg0.getScore();
		}

		@Override
		public String toString() {

			return score + "," + playerName;
		}
	}

	/**
	 * @return the List of scores
	 */
	public LinkedList<Score> getScores() {

		return scores;
	}

	public void erase() throws CouldNotLoadFileException {
		// TODO Auto-generated method stub
		File file = new File(fileName);
		file.delete();
		loadScores();
	}
}
