package com.techlabs.swing.subscriber;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class ColorListener implements ActionListener {
	
	private JFrame frame;
	
	public ColorListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		Color color = getColorFromName(button.getText());
		frame.getContentPane().setBackground(color);
	}
	
	private Color getColorFromName(String name)
	{
		try
		{
			return (Color)Color.class.getField(name.toUpperCase()).get(null);
		}
		catch (Exception e)
		{
			return Color.WHITE;
		}
	}

}
