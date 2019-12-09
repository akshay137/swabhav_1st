package com.techlabs.synchronous;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class WelcomeFrame extends JFrame{
	private JButton btnPrint;
	private JButton btnHello;
	
	public WelcomeFrame() {
		super("synchronus operations test");
		this.setView();
		this.addListeners();
	}
	
	private void setView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 360);
		this.setLayout(new FlowLayout());
		btnPrint = new JButton("Print");
		btnHello = new JButton("Hello");
		
		this.add(btnPrint);
		this.add(btnHello);
		this.setVisible(true);
	}
	
	private void addListeners() {
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				while (true) {
					String time = new SimpleDateFormat("hh:mm:ss").format(new Date());
					System.out.printf("%s\r", time.replace('\n', '\0'));
				}
			}
		});
		btnHello.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(WelcomeFrame.this,
						"You clicked on Hello");;
			}
		});
	}
}
