package com.techlabs.ttt;

import java.awt.*;
import javax.swing.*;

public class TicTacToeFrame extends JFrame {

    private HUD hud;
    private Controls controls;
    
    public TicTacToeFrame(HUD hud, Controls controls)
    {
	super("Tic Tac Toe");
	this.hud = hud;
	this.controls = controls;
	this.setView();
    }

    private void setView() {
	setSize(800, 480);
	setLayout(new GridLayout(2, 1));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.add(this.hud);
	this.add(this.controls);
	setVisible(true);
    }
    
    public void updateUI(Game game, GameResult res)
    {
	this.hud.updateUI(game, res);
    }
    
    public void endGame()
    {
	this.controls.disableAllButtons();
    }
}
