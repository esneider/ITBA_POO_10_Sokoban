package edu.itba.it.poog7.view.gui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import edu.itba.it.poog7.Controller.getFunction;

/**
 * Ask the user for a string
 */
public class PromptBox extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JButton but = null;
	JTextField tex = null;

	protected getFunction callback;
	/**
	 * Shows the user a message and ask for a string
	 * 
	 * TODO: Trigger a event when this is done 
	 * 
	 * @param frontText A message to show the user
	 */
	public PromptBox(String frontText){
		super(frontText);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (arg0.getActionCommand().toString() == "OK") {
					callback.call();
				}
			}
		});
		setLayout(new GridLayout(2, 1));
		add(new JTextField(""));
		add(button);
		
		setBounds(10, 10, 300, 100);
	}
	
	/**
	 * Get the value inputed by the user
	 * @return the string the user wrote 
	 */
	public String getValue(){
		setVisible(true);
		return tex.getText();
	}

	public void setCallback(getFunction callback) {
		this.callback = callback;
	}
}
