package com.techlabs.ttt;

import java.awt.*;

import javax.swing.*;

public class HUD extends JPanel {
    
    private JLabel p1Name;
    private JLabel p2Name;
    
    private JLabel currentUser;
    private JLabel gameStatus;
    
    public HUD(User p1, User p2, User current)
    {
	super();
	p1Name = new JLabel(getPlayerData(p1));
	p2Name = new JLabel(getPlayerData(p2));
	currentUser = new JLabel(getPlayerData(current));
	gameStatus = new JLabel(GameResult.GAME_RUNNING.toString());
	setView();
    }
    
    private String getPlayerData(User user)
    {
	return String.format("%s [%s]",
		user.getName(), user.getMark().toString());
    }
    
    private void setView()
    {
	setLayout(new GridLayout(2, 0));
	JLabel player1 = new JLabel("Player 1:");
	this.add(player1);
	this.add(p1Name);
	
	JLabel player2 = new JLabel("Player 2:");
	this.add(player2);
	this.add(p2Name);
	
	JLabel cu = new JLabel("Current user:");
	this.add(cu);
	this.add(currentUser);
	
	JLabel status = new JLabel("Status:");
	this.add(status);
	this.add(gameStatus);
    }
    
    public void updateUI(Game game, GameResult res)
    {
	if (res == GameResult.WIN)
	{
	    gameStatus.setText(String.format("%s %s",
		    getPlayerData(game.getCurrentUser()), res.toString()));
	}
	else
	{
	    gameStatus.setText(res.toString());
	}
	currentUser.setText(getPlayerData(game.getCurrentUser()));
    }
}
