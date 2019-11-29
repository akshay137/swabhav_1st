package com.techlabs.ttt.test;

import com.techlabs.ttt.*;

import org.junit.jupiter.api.*;

public class BoardTest {
    
    private final int size = 3;
    
    @Test
    public void testSize()
    {
	Board b = new Board(size);
	Assertions.assertTrue(size == b.getSize());;
	Assertions.assertTrue((size * size) == b.getGrid().length);
    }
    
    @Test
    public void testMark()
    {
	Board b = new Board(3);
	Cell[] c = b.getGrid();
	b.markCell(0, MarkType.CIRCLE);
	Assertions.assertFalse(c[0].isEmpty());
	b.markCell(1, MarkType.CROSS);
	Assertions.assertFalse(c[1].isEmpty());
	Assertions.assertThrows(SettinNonEmptyCell.class,
		() -> {b.markCell(1, MarkType.CIRCLE);});
    }
}
