package com.techlabs.ttt;

public class Cell {
    private MarkType mark;
    
    public Cell()
    {
	this.mark = MarkType.EMPTY;
    }
    
    public MarkType getMark()
    {
	return this.mark;
    }
    
    public boolean isEmpty()
    {
	return this.mark == MarkType.EMPTY;
    }
    
    public void setMark(MarkType mark)
    {
	if (this.mark != MarkType.EMPTY)
	    throw new SettinNonEmptyCell();
	this.mark = mark;
    }
}
