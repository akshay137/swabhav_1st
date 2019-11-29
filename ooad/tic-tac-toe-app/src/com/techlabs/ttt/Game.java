package com.techlabs.ttt;

public class Game {
    private IBoard board;
    private IResultAnalyzer resultAnalyzer;
    
    private User[] users;
    private int turn;
    
    public Game(IBoard board, IResultAnalyzer resultAnalyzer,
	    User[] users)
    {
	this.board = board;
	this.resultAnalyzer = resultAnalyzer;
	this.users = users;
	this.turn = 0;
    }
    
    public GameResult play(int cellId)
    {
	board.markCell(cellId, users[turn].getMark());
	return resultAnalyzer.checkBoard(
		users[turn].getMark(), cellId);
    }
    
    public void changeTurn()
    {
	this.turn = (this.turn + 1) % this.users.length;
    }
    
    public User getCurrentUser()
    {
	return this.users[this.turn];
    }
    
    public IBoard getBoard()
    {
	return this.board;
    }
}
