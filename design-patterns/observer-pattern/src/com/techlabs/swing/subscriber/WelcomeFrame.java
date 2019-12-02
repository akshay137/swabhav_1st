package com.techlabs.swing.subscriber;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class WelcomeFrame extends JFrame {
	
	public WelcomeFrame()
	{
		super("akshay");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setView();
		setVisible(true);
	}
	
	private void setView()
	{
		this.setLayout(null);//new FlowLayout(FlowLayout.LEFT));
		JButton wish = new JButton();
		wish.setText("Wish");
		wish.setBounds(0, 0, 200, 100);
		this.add(wish);
		
		wish.addActionListener(new HowdyListener());
		wish.addActionListener(new HelloListener());
	}
}
