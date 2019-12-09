package com.techlabs.asynchronous;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class WelcomeFrame extends JFrame{
	private JButton btnPrint;
	private JButton btnStop;
	private JButton btnHello;
	private Thread thread;
	
	private volatile boolean running;
	
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
		btnStop = new JButton("Stop printing");
		btnHello = new JButton("Hello");
		
		this.add(btnPrint);
		this.add(btnStop);
		this.add(btnHello);
		this.setVisible(true);
	}
	
	private class PrintMsg implements Runnable {
		
		@Override
		public void run() {
			while (WelcomeFrame.this.running) {
				String time = new SimpleDateFormat("hh:mm:ss").format(new Date());
				System.out.printf("%s\r", time.replace('\n', '\0'));
			}
		}
		
	}
	
	private void addListeners() {
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WelcomeFrame.this.running = true;
				thread = new Thread(new PrintMsg());
				thread.start();
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WelcomeFrame.this.running = false;
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
