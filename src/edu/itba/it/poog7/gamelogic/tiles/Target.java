package edu.itba.it.poog7.gamelogic.tiles;

import edu.itba.it.poog7.gamelogic.ElementType;
import edu.itba.it.poog7.gamelogic.Position;
import edu.itba.it.poog7.gamelogic.RGBColor;
import edu.itba.it.poog7.gamelogic.objects.Box;
import edu.itba.it.poog7.gamelogic.objects.GameObject;
import edu.itba.it.poog7.gamelogic.tiles.event.TargetMatchedEvent;
import edu.itba.it.poog7.gamelogic.tiles.event.TargetUnmatchedEvent;

/**
 * Target tile for boxes.
 * 
 * @author champo
 */
public class Target extends GameTile {

	RGBColor color;

	/**
	 * Instance a new target tile.
	 * 
	 * @param pos
	 *            The position the tile is in.
	 * @param color
	 *            The color that the tile has.
	 */
	public Target(Position pos, RGBColor color) {
		super(pos);
		this.color = color;
	}

	/**
	 * Get the tiles color.
	 * 
	 * @return The tiles color.
	 */
	public RGBColor getColor() {
		return color;
	}

	@Override
	public void setObject(GameObject object) {

		if (this.object instanceof Box) {
			if (((Box) this.object).getColor().equals(this.getColor())) {
				System.out.println("Dispare un evento de target unmatched!");
				generateEvent(new TargetUnmatchedEvent(this));
			}
		}

		if (object instanceof Box) {
			if (((Box) this.object).getColor().equals(this.getColor())) {
				System.out.println("Dispare un evento de target matched!");
				generateEvent(new TargetMatchedEvent(this));
			}
		}

		super.setObject(object);
	}

	@Override
	public String toString() {
		return pos + "," + ElementType.TARGET.getInt() + ",0," + color;
	}
}
