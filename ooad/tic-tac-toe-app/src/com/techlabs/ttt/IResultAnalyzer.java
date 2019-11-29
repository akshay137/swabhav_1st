package com.techlabs.ttt;

public interface IResultAnalyzer {
    GameResult checkBoard(MarkType type, int cellId);
}
