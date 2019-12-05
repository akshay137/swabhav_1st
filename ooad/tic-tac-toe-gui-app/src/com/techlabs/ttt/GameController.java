package com.techlabs.ttt;

import java.awt.event.*;
import javax.swing.*;

public class GameController implements ActionListener {
    private Game game;
    private int boardSize;
    private TicTacToeFrame frame;
    private static final char[] marks = {' ', 'X', 'O'};
    
    public GameController()
    {
	this(3);
    }
    
    public GameController(int boardSize)
    {
	this.boardSize = boardSize;
	this.setUpGame(this.boardSize);
    }
    
    private void setUpGame(int boardSize)
    {
	Board board = new Board(boardSize);
	ResultAnalyzer rAnalyzer = new ResultAnalyzer(board);
	User[] users = UserFactory.getUsers(2);
	this.game = new Game(board, rAnalyzer, users);
	this.setUI(users);
    }
    
    private void setUI(User[] users)
    {
	HUD hud = new HUD(users[0], users[1], game.getCurrentUser());
	Controls controls = new Controls(this.boardSize, this.boardSize, this);
	this.frame = new TicTacToeFrame(hud, controls);
    }
    
    private void endGame(GameResult res)
    {
	this.frame.endGame();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	JButton button = (JButton)e.getSource();

	String value = button.getText();
	if (value.contains("X") || value.contains("O"))
	    return;
	
	button.removeActionListener(this);
	button.setEnabled(false);
	button.setText(String.valueOf(
		marks[game.getCurrentUser().getMark().ordinal()]));
	
//	System.out.println(value);
	GameResult res = game.play(Integer.parseInt(value));
	if (res == GameResult.GAME_RUNNING)
	    game.changeTurn();
	else
	    endGame(res);
	frame.updateUI(game, res);
    }
}
