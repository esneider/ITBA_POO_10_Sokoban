package edu.itba.it.poog7.view.gui;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The menu of the main screen
 *
 */
public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 2L;
	ActionListener parent;

	/**
	 * Displays a menu with some commons options in the menubar of the main screen 
	 */
	public MenuBar(ActionListener frame) {
		super();
		parent = frame;

		JMenu file = new Menu("Game", new String[] { "New Game", "Load Game",
				"Save Game", "Restart", "Quit"});
		JMenu high = new Menu("High Scores", new String[] {"Show"});
		
		this.add(file);
		this.add(high);
		
	}

	/**
	 * Batch adition of menu elements
	 *
	 */
	private class Menu extends JMenu {
		private static final long serialVersionUID = 3L;
		private List<MyMenuItem> elem = null;

		/**
		 * Automatic constructor of a Menu 
		 * @param name the name of the menu
		 * @param sons the names of 'hanging' options from this menu
		 */
		public Menu(String name, String[] sons) {
			super(name);
			elem = new  LinkedList<MyMenuItem>();
			for (String actionName : sons) {
				MyMenuItem nuevo = new MyMenuItem(actionName);
				this.add(nuevo);
			}
		}
		/**
		 * Getter for the elements created in this menu
		 * @return a list of items in the menu
		 */
		@SuppressWarnings("unused")
		public List<MyMenuItem> getElem() {
			return elem;
		}
	}
	
	/**
	 * A Menu Item
	 */
	private class MyMenuItem extends JMenuItem {
		private static final long serialVersionUID = 1L;

		/**
		 * Constructor
		 * @param name the name of the menu item
		 */
		public MyMenuItem(String name) {
			super(name);
			this.addActionListener(parent);
		}
	}
}
