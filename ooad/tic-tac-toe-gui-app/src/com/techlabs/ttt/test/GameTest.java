package com.techlabs.ttt.test;

import com.techlabs.ttt.*;
import org.junit.jupiter.api.*;

public class GameTest {
    private static final int size = 3;
    
    @Test
    public void testUserTurnPass()
    {
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	User[] users = {new User("a", MarkType.CIRCLE, new Console()),
		new User("b", MarkType.CROSS, new Console())};
	Game game = new Game(b, r, users);
	User u = game.getCurrentUser();
	Assertions.assertTrue(u == users[0]);
	game.play(0);
	u = game.getCurrentUser();
	Assertions.assertTrue(u == users[1]);
	game.play(1);
	u = game.getCurrentUser();
	Assertions.assertTrue(u == users[0]);
    }
    
    @Test
    public void testGameRunning()
    {
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	User[] users = {new User("a", MarkType.CIRCLE, new Console()),
		new User("b", MarkType.CROSS, new Console())};
	Game game = new Game(b, r, users);
	GameResult res = game.play(0);
	Assertions.assertTrue(GameResult.GAME_RUNNING == res);
    }
}
