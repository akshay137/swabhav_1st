package com.techlabs.ttt.test;

import com.techlabs.ttt.*;

import java.lang.reflect.*;
import org.junit.jupiter.api.*;

public class ResultAnalyzerTest {
    
    private final int size = 7890;
    
    @Test
    public void testGameRunning()
    {
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	b.markCell(0, MarkType.CIRCLE);
	b.markCell(1, MarkType.CIRCLE);
	Assertions.assertTrue(GameResult.GAME_RUNNING
		== r.checkBoard(MarkType.CIRCLE, 0));
    }
    
    @Test
    public void testGameWinVerticalPass()
    {
	MarkType type = MarkType.CIRCLE;
	for (int i = 0; i < size; i++)
	{
	    Board b = new Board(size);
	    ResultAnalyzer r = new ResultAnalyzer(b);
	    BoardDummyIO.fillColumn(i, b, type);
	    for (int j = 0; j < size; j++)
	    {
		Assertions.assertTrue(r.checkBoard(type, j * size + i)
		    == GameResult.WIN);
	    }
	}
    }
    
    @Test
    public void testGameWinVerticalFail()
    {
	MarkType type = MarkType.CIRCLE;
	for (int i = 0; i < size; i++)
	{
	    Board b = new Board(size);
	    ResultAnalyzer r = new ResultAnalyzer(b);
	    BoardDummyIO.fillColumnAlt(i, b);
	    for (int j = 0; j < size; j++)
	    {
		Assertions.assertFalse(r.checkBoard(type, j * size + i)
		    == GameResult.WIN);
	    }
	}
    }
    
    @Test
    public void testGameWinHorizontalPass()
    {
	MarkType type = MarkType.CIRCLE;
	for (int i = 0; i < size; i++)
	{
	    Board b = new Board(size);
	    ResultAnalyzer r = new ResultAnalyzer(b);
	    BoardDummyIO.fillRow(i, b, type);
	    for (int j = 0; j < size; j++)
	    {
		Assertions.assertTrue(r.checkBoard(type, i * size + j)
		    == GameResult.WIN);
	    }
	}
    }
    
    @Test
    public void testGameWinHorizontalFail()
    {
	MarkType type = MarkType.CIRCLE;
	for (int i = 0; i < size; i++)
	{
	    Board b = new Board(size);
	    ResultAnalyzer r = new ResultAnalyzer(b);
	    BoardDummyIO.fillRowAlt(i, b);
	    for (int j = 0; j < size; j++)
	    {
		Assertions.assertFalse(r.checkBoard(type, i * size + j)
		    == GameResult.WIN);
	    }
	}
    }
    
    @Test
    public void testHorizontalPass() throws IllegalAccessException,
    	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(
		ResultAnalyzer.class, "checkRows", 2);
	if (method == null)
	    throw new RuntimeException("method not found");
	
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	MarkType type = MarkType.CIRCLE;
	
	for (int i = 0; i < size; i++)
	{
	    int offset = i * size;
	    BoardDummyIO.fillRow(i, b, type);
	    for (int j = 0; j < size; j++)
	    {
		boolean result = (boolean)method.invoke(r, type, j + offset);
		Assertions.assertTrue(result);
	    }
	}
    }
    
    @Test
    public void testHorizontalFail() throws IllegalAccessException,
	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(
		ResultAnalyzer.class, "checkRows", 2);
	if (method == null)
	    throw new RuntimeException("method not found");
	
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	MarkType type = MarkType.CIRCLE;
	for (int i = 0; i < size; i++)
	{
	    int offset = i * size;
	    BoardDummyIO.fillRowAlt(i, b);
	    for (int j = 0; j < size; j++)
	    {
		boolean result = (boolean)method.invoke(r, type, j + offset);
		Assertions.assertFalse(result);
	    }
	}
    }
    
    @Test
    public void testVerticalPass() throws IllegalAccessException,
	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(
		ResultAnalyzer.class, "checkColumns", 2);
	if (method == null)
	    throw new RuntimeException("method not found");
	
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	MarkType type = MarkType.CIRCLE;
	for (int i = 0; i < size; i++)
	{
	    BoardDummyIO.fillColumn(i, b, type);
	    for (int j = 0; j < size; j++)
	    {
		int index = (j * size) + i;
		boolean result = (boolean)method.invoke(r, type, index);
		Assertions.assertTrue(result);
	    }
	}
    }
    
    @Test
    public void testVerticalFail() throws IllegalAccessException,
	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(
		ResultAnalyzer.class, "checkColumns", 2);
	if (method == null)
	    throw new RuntimeException("method not found");
	
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	MarkType type = MarkType.CIRCLE;
	for (int i = 0; i < size; i++)
	{
	    BoardDummyIO.fillColumnAlt(i, b);
	    for (int j = 0; j < size; j++)
	    {
		int index = (j * size) + i;
		boolean result = (boolean)method.invoke(r, type, index);
		Assertions.assertFalse(result);
	    }
	}
    }
    
    @Test
    public void testDiagonalPass() throws IllegalAccessException,
	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(ResultAnalyzer.class,
		"checkDiagonal", 2);
	if (method == null)
	    throw new RuntimeException("Method not found");

	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	MarkType type = MarkType.CIRCLE;
	BoardDummyIO.fillDiagonal(b, type);
	
	for (int i = 0; i < size; i++)
	{
	    boolean result = (boolean)method.invoke(r, type, i * size + i);
	    Assertions.assertTrue(result);
	}
    }
    
    @Test
    public void testDiagonalFail() throws IllegalAccessException,
	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(ResultAnalyzer.class,
		"checkDiagonal", 2);
	if (method == null)
	    throw new RuntimeException("Method not found");

	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	MarkType type = MarkType.CIRCLE;
	BoardDummyIO.fillDiagonalAlt(b);
	
	for (int i = 0; i < size * size; i++)
	{
	    boolean result = (boolean)method.invoke(r, type, i);
	    Assertions.assertFalse(result);
	}
    }
    
    @Test
    public void testReverseDiagonalPass() throws IllegalAccessException,
	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(ResultAnalyzer.class,
		"checkReverseDiagonal", 2);
	if (method == null)
	    throw new RuntimeException("Method not found");
	
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	MarkType type = MarkType.CIRCLE;
	BoardDummyIO.fillRDiagonal(b, type);
	
	for (int i = 0; i < size; i++)
	{
    	    int index = (i * size) + (size - 1 - i);
    	    boolean result = (boolean)method.invoke(r, type, index);
    	    Assertions.assertTrue(result);
	}
    }
    
    @Test
    public void testReverseDiagonalFail() throws IllegalAccessException,
	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(ResultAnalyzer.class,
		"checkReverseDiagonal", 2);
	if (method == null)
	    throw new RuntimeException("Method not found");
	
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	MarkType type = MarkType.CIRCLE;
	for (int i = 0; i < size * size; i++)
	{
	    boolean result = (boolean)method.invoke(r, type, i);
	    Assertions.assertFalse(result);
	}
    }
    
    @Test
    public void testFilledBoardPass() throws IllegalAccessException,
    	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(ResultAnalyzer.class,
		"isBoardFilled", 0);
	if (method == null)
	    throw new RuntimeException("Method not found");
	
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	for (int i = 0; i < size * size; i++)
	    b.markCell(i, MarkType.CIRCLE);
	Assertions.assertTrue((boolean)method.invoke(r));
    }
    
    @Test
    public void testFilledBoardFail() throws IllegalAccessException,
    	IllegalArgumentException, InvocationTargetException
    {
	Method method = Reflector.getMethod(ResultAnalyzer.class,
		"isBoardFilled", 0);
	if (method == null)
	    throw new RuntimeException("Method not found");
	
	Board b = new Board(size);
	ResultAnalyzer r = new ResultAnalyzer(b);
	Assertions.assertFalse((boolean)method.invoke(r));
	b.markCell(0, MarkType.CIRCLE);
	Assertions.assertFalse((boolean)method.invoke(r));
    }
}
