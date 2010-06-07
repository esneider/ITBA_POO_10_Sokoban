package edu.itba.it.poog7.gamelogic;

public enum ElementType {
	CHABONCITOU(1), BOX(2), TARGET(3), WALL(4), HOLE(5), ONEWAY(6), BLANK(7);
	
	private final int dir;

	private ElementType(int dir) {
		this.dir = dir;
	}
	
	public int getInt() {
		return this.dir;
	}

	public static ElementType getType(int type) {
		
		switch (type) {
			case 1:	return CHABONCITOU;
			case 2: return BOX;
			case 3: return TARGET;
			case 4: return WALL;
			case 5: return HOLE;
			case 6: return ONEWAY;
			default: return BLANK;
		}
	}
}
