package com.techlabs.ttt;

import java.awt.*;
import javax.swing.*;

public class MenuFrame extends JFrame {
    
    private JTextField p1Name;
    private JTextField p2Name;
    private JButton btnStart;
    
    public MenuFrame()
    {
	super("Enter player details");
	this.setView();
	this.setVisible(true);
    }
    
    private void setView()
    {
	this.setSize(400, 400);
	this.setLayout(new GridLayout(3, 0));
	p1Name = new JTextField();
	this.add(p1Name);
	p2Name = new JTextField();
	this.add(p2Name);
	btnStart = new JButton("Start");
	this.add(btnStart);
    }
}
