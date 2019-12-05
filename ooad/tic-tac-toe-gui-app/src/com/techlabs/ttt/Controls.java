package com.techlabs.ttt;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Controls extends JPanel {

    private ActionListener listener;
    private List<JButton> buttons;

    public Controls(int w, int h, ActionListener listener) {
	super();
	this.listener = listener;
	this.buttons = new ArrayList<JButton>();
	this.setView(w, h);
    }

    private void setView(int w, int h) {
	int buttonCount = w * h;
	this.setLayout(new GridLayout(w, h));
	for (int i = 0; i < buttonCount; i++) {
	    JButton button = new JButton(Integer.toString(i));
	    button.addActionListener(this.listener);
	    this.add(button);
	    this.buttons.add(button);
	}
    }

    public void disableAllButtons() {
	for (JButton button : this.buttons) {
	    button.removeActionListener(this.listener);
	    button.setEnabled(false);
	}
    }
}
