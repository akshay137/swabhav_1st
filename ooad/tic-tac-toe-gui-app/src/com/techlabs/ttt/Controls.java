package com.techlabs.ttt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Controls extends JPanel {
    
    private ActionListener listener;
    
    public Controls(int w, int h, ActionListener listener)
    {
	super();
	this.listener = listener;
	this.setView(w, h);
    }
    
    private void setView(int w, int h)
    {
	int buttonCount = w * h;
	this.setLayout(new GridLayout(w, h));
	for (int i = 0; i < buttonCount; i++)
	{
	    JButton button = new JButton(Integer.toString(i));
	    button.addActionListener(this.listener);
	    this.add(button);
	}
    }
    
    public void disableAllButtons()
    {
	Component[] componets = this.getComponents();
	for (Component c : componets)
	{
	    if (c instanceof JButton)
	    {
		JButton button = (JButton)c;
		button.removeActionListener(this.listener);
		button.setEnabled(false);
	    }
	}
    }
}
