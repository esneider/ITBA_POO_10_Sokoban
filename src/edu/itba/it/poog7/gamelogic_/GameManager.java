package edu.itba.it.poog7.gamelogic_;

public abstract class GameManager {

	protected static Game loadGame(String name, boolean saved){
		// TODO
		return null;
	}

	public static Game loadCurrent(String name) {
		return loadGame(name, false);
	}

	public static Game loadNext(String name) {
		// TODO
		name = null /* find next name */;
		return loadGame(name, false);
	}

	public static Game load(String name) {
		return loadGame(name, true);
	}

	public static void save(Game game) {
		// TODO
	}
}
