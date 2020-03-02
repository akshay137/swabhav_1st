package board

import (
	"fmt"
	"testing"
)

var bSize int32 = 3

func TestNewBoard(t *testing.T) {
	b := NewBoard(bSize)
	for _, c := range b.cells {
		if c.Mark() != NoMark {
			t.Logf("cell initialized with invalid mark")
			t.FailNow()
		}
	}
}

func TestBoardMarkCellNoMark(t *testing.T) {
	b := NewBoard(bSize)
	err := b.MarkCell(1, NoMark)
	if err == nil {
		t.Logf("expected %v got %v",
			fmt.Errorf("Cant mark cell with no mark"), err)
		t.FailNow()
	}
}

func TestBoardMarkCellIOB(t *testing.T) {
	b := NewBoard(bSize)
	err := b.MarkCell(-1, Cross)
	if err == nil {
		t.Logf("Expected %v got %v",
			fmt.Errorf("index out of bounds"), err)
		t.FailNow()
	}
}

func TestBoardMarkCellTwice(t *testing.T) {
	b := NewBoard(bSize)
	err := b.MarkCell(1, Cross)
	err = b.MarkCell(1, Cross)
	if err == nil {
		t.Logf("Expected: %v got %v",
			fmt.Errorf("Cell already marked"), err)
		t.FailNow()
	}
}
