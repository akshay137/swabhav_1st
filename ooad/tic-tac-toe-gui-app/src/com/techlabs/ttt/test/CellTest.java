package com.techlabs.ttt.test;

import org.junit.jupiter.api.*;

import com.techlabs.ttt.*;

public class CellTest {
    
    @Test
    public void testEmptyMarkCell()
    {
	Cell c = new Cell();
	Assertions.assertTrue(c.getMark() == MarkType.EMPTY);
    }
    
    @Test
    public void testEmpty()
    {
	Cell c = new Cell();
	Assertions.assertTrue(c.isEmpty());
	c.setMark(MarkType.CIRCLE);
	Assertions.assertFalse(c.isEmpty());
    }
    
    @Test
    public void testRepeateSetMark()
    {
	Cell c = new Cell();
	c.setMark(MarkType.CIRCLE);
	Assertions.assertThrows(SettinNonEmptyCell.class,
		() -> {c.setMark(MarkType.CIRCLE);});
    }
}
