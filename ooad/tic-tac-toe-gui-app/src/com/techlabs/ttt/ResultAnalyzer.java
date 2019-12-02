package com.techlabs.ttt;

public class ResultAnalyzer implements IResultAnalyzer{
    
    private Board board;
    
    public ResultAnalyzer(Board board)
    {
	this.board = board;
    }
    
    @Override
    public GameResult checkBoard(MarkType type, int cellId)
    {
	if (checkRows(type, cellId))
	    return GameResult.WIN;
	if (checkColumns(type, cellId))
	    return GameResult.WIN;
	if (checkDiagonal(type, cellId))
	    return GameResult.WIN;
	if (checkReverseDiagonal(type, cellId))
	    return GameResult.WIN;
	if (isBoardFilled())
	    return GameResult.DRAW;
	return GameResult.GAME_RUNNING;
    }
    
    private boolean checkRows(MarkType mark, int cellId)
    {
	if (mark == MarkType.EMPTY)
	    return false;
	int size = board.getSize();
	Cell[] cells = board.getGrid();
	int offset = (cellId / size) * size;
	for (int i = offset; i < offset + size; i++)
	    if (cells[i].getMark() != mark)
		return false;
	return true;
    }
    
    private boolean checkColumns(MarkType mark, int cellId)
    {
	if (mark == MarkType.EMPTY)
	    return false;
	int size = board.getSize();
	Cell[] cells = board.getGrid();
	int offset = (cellId % size);
	for (int i = 0; i < size; i++)
	    if (cells[(i * size) + offset].getMark() != mark)
		return false;
	return true;
    }
    
    private boolean checkDiagonal(MarkType mark, int cellId)
    {
	if (mark == MarkType.EMPTY)
	    return false;
	int size = board.getSize();
	if (cellId / size != cellId % size)
	    return false;
	Cell[] cells = board.getGrid();
	for (int i = 0; i < size; i++)
	    if (cells[i * size + i].getMark() != mark)
		return false;
	return true;
    }
    
    private boolean checkReverseDiagonal(MarkType mark, int cellId)
    {
	if (mark == MarkType.EMPTY)
	    return false;
	int size = board.getSize();
	int row = cellId / size;
	int column = cellId % size;
	if (row != (size - 1 - column))
	    return false;
	Cell[] cells = board.getGrid();
	for (int i = 0; i < size; i++)
	{
	    int index = (i * size) + (size - 1 - i);
	    if (cells[index].getMark() != mark)
		return false;
	}
	return true;
    }
    
    private boolean isBoardFilled()
    {
	Cell[] cells = board.getGrid();
	for (int i = 0; i < cells.length; i++)
	    if (cells[i].isEmpty())
		return false;
	return true;
    }
}
