package com.techlabs.ttt.testDrive;

import com.techlabs.ttt.*;

public class TicTacToeGame {
    public static void main(String[] args)
    {
	GameController gameController = new GameController(3);
	gameController.playGame();
    }
}
