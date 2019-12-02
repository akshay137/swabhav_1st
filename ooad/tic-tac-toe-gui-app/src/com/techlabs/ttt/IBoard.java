package com.techlabs.ttt;

public interface IBoard {
    void markCell(int cellId, MarkType type);
    int getSize();
    Cell[] getGrid(); 
}
