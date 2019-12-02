package com.techlabs.swing.subscriber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowdyListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("howdy");
	}

}
