package edu.itba.it.poog7.gamelogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

import edu.itba.it.poog7.gamelogic.exception.CouldNotSaveFileException;
import edu.itba.it.poog7.gamelogic.exception.InvalidFileException;

public class Highscores {

	private static final int MAX_SCORES = 10;
	private TreeSet<Score> scores;
	private String levelName;

	public Highscores(String levelName) throws InvalidFileException {
		
		this.levelName = levelName;
		loadScores();
	}
	
	private void loadScores() throws InvalidFileException {
		
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(levelName));
		} catch(FileNotFoundException e) {
			scores = new TreeSet<Score>();
			return;
		}
		
		String line;
		try {
			while((line = file.readLine()) != null) {
				
				scores.add(parseLine(line));
			}
		} catch (IOException e) {
			
			throw new InvalidFileException();
		}
	}
	
	private Score parseLine(String line) throws InvalidFileException {
		
		int separator = line.indexOf(',');
		if (separator == -1 || line.length() == separator - 1) {
			throw new InvalidFileException();
		}
		
		int score = Integer.parseInt(line.substring(0, separator));
		String playerName = line.substring(separator + 1);
		
		return new Score(score, playerName);
	}

	/**
	 * Add a new score.
	 * 
	 * @param playerName
	 * @param scored
	 * @return True if the score entered the high scores, false otherwise
	 * @throws CouldNotSaveFileException 
	 */
	public boolean addScore(String playerName, int scored) throws CouldNotSaveFileException {
		
		Score score = new Score(scored, playerName);
		Score last = scores.last();
		
		if (scores.size() < MAX_SCORES || score.compareTo(last) > 0 ) {
			
			scores.add(score);
			if (scores.size() >= MAX_SCORES) {
				scores.remove(last);
			}
			
			saveScores();
			return true;
		} else {
			return false;
		}
	}
	
	private void saveScores() throws CouldNotSaveFileException {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(levelName));

			for (Score s : scores) {
				writer.write(s.getScore() + "," + s.getPlayerName());
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			
			throw new CouldNotSaveFileException("Could not save the highscores");
		}
	}

	class Score implements Comparable<Score> {
		
		int score;
		String playerName;
		
		public Score(int score, String playerName) {
			super();
			this.score = score;
			this.playerName = playerName;
		}

		public int getScore() {
			return score;
		}

		public String getPlayerName() {
			return playerName;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int compareTo(Score arg0) {
			
			return new Integer(score).compareTo(new Integer(arg0.score));
		}
	}
}
