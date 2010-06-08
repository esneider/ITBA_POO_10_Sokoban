package edu.itba.it.poog7.view.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	public MessageBox(String title, String message){
		super(title);
		
		JPanel myPanel = new JPanel();
		new BoxLayout(myPanel, BoxLayout.Y_AXIS);
		
		JButton ok = new JButton("OK");
		ok.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {

				dispose();
			}
		});

		myPanel.add(new JLabel(message));
		myPanel.add(ok);
		add(myPanel);
		setBounds(0,0,300,60);
		
		setVisible(true);
	}
}
