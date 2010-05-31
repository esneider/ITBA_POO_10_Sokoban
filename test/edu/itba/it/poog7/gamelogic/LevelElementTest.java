/**
 * 
 */
package edu.itba.it.poog7.gamelogic;

import javax.swing.JPanel;

import junit.framework.TestCase;

/**
 * @author champo
 *
 */
public class LevelElementTest extends TestCase {

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.LevelElement#LevelElement(edu.itba.it.poog7.gamelogic.Position)}.
	 */
	public void testLevelElement() {
		
		new LevelElement(new Position(0, 0)) {
			
			@Override
			public void draw(JPanel panel) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	/**
	 * Test method for {@link edu.itba.it.poog7.gamelogic.LevelElement#getPosition()}.
	 */
	public void testGetPosition() {
		
		Position pos = new Position(2, 3);
		
		LevelElement element = new LevelElement(pos) {
			
			@Override
			public void draw(JPanel panel) {
				// TODO Auto-generated method stub
				
			}
		};
		
		assertEquals(pos, element.getPos());
	}

}
