package com.techlabs.ttt;

public class User {
    private MarkType mark;
    private String name;
    private ICellDecider cellLogic;
    
    public User(String name, MarkType mark, ICellDecider cellLogic)
    {
	this.name = name;
	this.setCellLogic(cellLogic);
	this.setMark(mark);
    }
    
    public String getName()
    {
	return this.name;
    }
    
    public void setCellLogic(ICellDecider cellLogic)
    {
	if (cellLogic == null)
	    cellLogic = new Console();
	this.cellLogic = cellLogic;
    }
    
    private void setMark(MarkType mark)
    {
	if (mark == MarkType.EMPTY)
	    throw new RuntimeException("Can't set mark to empty");
	this.mark = mark;
    }
    
    public MarkType getMark()
    {
	return this.mark;
    }
    
    public int decideCell(IBoard board)
    {
	return this.cellLogic.decideCell(board, this.mark);
    }
}
