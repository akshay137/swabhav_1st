package com.techlabs.ttt.test;

import com.techlabs.ttt.*;

public class BoardDummyIO {
    public static void fillRow(int row, Board b, MarkType type)
    {
	int offset = row * b.getSize();
	for (int i = 0; i < b.getSize(); i++)
	    b.markCell(offset + i, type);
    }
    
    public static void fillRowAlt(int row, Board b)
    {
	int offset = row * b.getSize();
	for (int i = 0; i < b.getSize(); i++)
	{
	    if (i % 2 == 0)
		b.markCell(i + offset, MarkType.CIRCLE);
	    else
		b.markCell(i + offset, MarkType.CROSS);
	}
    }
    
    public static void fillColumn(int column, Board b, MarkType type)
    {
	for (int i = 0; i < b.getSize(); i++)
	{
	    int index = (i * b.getSize()) + column;
	    b.markCell(index, type);
	}
    }
    
    public static void fillColumnAlt(int column, Board b)
    {
	for (int i = 0; i < b.getSize(); i++)
	{
	    int index = (i * b.getSize()) + column;
	    if (i % 2 == 0)
		b.markCell(index, MarkType.CIRCLE);
	    else
		b.markCell(index, MarkType.CROSS);
	}
    }
    
    public static void fillDiagonal(Board b, MarkType type)
    {
	for (int i = 0; i < b.getSize(); i++)
	{
	    int index = i * b.getSize() + i;
	    b.markCell(index, type);
	}
    }
    
    public static void fillDiagonalAlt(Board b)
    {
	for (int i = 0; i < b.getSize(); i++)
	{
	    int index = i * b.getSize() + i;
	    if (i % 2 == 0)
		b.markCell(index, MarkType.CIRCLE);
	    else
		b.markCell(index, MarkType.CROSS);
	}
    }
    
    public static void fillRDiagonal(Board b, MarkType type)
    {
	for (int i = 0; i < b.getSize(); i++)
	{
	    int index = (i * b.getSize()) + (b.getSize() - 1 - i);
	    b.markCell(index, type);
	}
    }
    
    public static void fillRDiagonalAlt(Board b)
    {
	for (int i = 0; i < b.getSize(); i++)
	{
	    int index = (i * b.getSize()) + (b.getSize() - 1 - i);
	    if (i % 2 == 0)
		b.markCell(index, MarkType.CIRCLE);
	    else
		b.markCell(index, MarkType.CROSS);
	}
    }
}
