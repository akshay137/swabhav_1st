package com.techlabs.ttt;

public class Board implements IBoard {
    private Cell[] grid;
    private int size;
    
    public Board(int size)
    {
	this.size = size;
	this.generateGrid(size);
    }
    
    private void generateGrid(int size)
    {
	this.grid = new Cell[size * size];
	for (int i = 0; i < grid.length; i++)
	    grid[i] = new Cell();
    }
    
    @Override
    public int getSize()
    {
	return this.size;
    }
    
    @Override
    public Cell[] getGrid()
    {
	return this.grid;
    }
    
    @Override
    public void markCell(int cellId, MarkType type)
    {
	grid[cellId].setMark(type);
    }
}
