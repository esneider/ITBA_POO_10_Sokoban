package edu.itba.it.poog7.view.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Show a Error box with a simple message
 *
 */
public class MessageBox extends JFrame {
	private static final long serialVersionUID = 20L;

	/**
	 * Show the user a simple box with some text.
	 * @param message the message for the user to read.
	 */
	public MessageBox(String title, String message, boolean error){
		super(title);

	    JOptionPane.showMessageDialog(new JFrame(), message, title,
	        (error?JOptionPane.ERROR_MESSAGE:JOptionPane.INFORMATION_MESSAGE));
	}
}
