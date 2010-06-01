/**
 * 
 */
package test.edu.itba.it.poog7.gamelogic;

import javax.swing.JPanel;

import edu.itba.it.poog7.gamelogic.GameElement;
import edu.itba.it.poog7.gamelogic.Position;

import junit.framework.TestCase;

/**
 * @author champo
 *
 */
public class LevelElementTest extends TestCase {

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.GameElement#LevelElement(edu.itba.it.poog7.gamelogic.Position)}.
	 */
	public void testLevelElement() {
		
		new GameElement(new Position(0, 0)) {
			
			@Override
			public void draw(JPanel panel) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.GameElement#getPosition()}.
	 */
	public void testGetPosition() {
		
		Position pos = new Position(2, 3);
		
		GameElement element = new GameElement(pos) {
			
			@Override
			public void draw(JPanel panel) {
				// TODO Auto-generated method stub
				
			}
		};
		
		assertEquals(pos, element.getPos());
	}

}
