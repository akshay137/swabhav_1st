package board

import (
	"fmt"
	"testing"
)

func TestNewCellNoMark(t *testing.T) {
	expectedMark := NoMark
	c := NewCell()
	if c.Mark() != NoMark {
		t.Logf("expected: %v, actual: %v\n",
			expectedMark, c.Mark())
		t.FailNow()
	}
}

func TestMarkCellInvalid(t *testing.T) {
	c := NewCell()
	err := c.MarkCell(Cross)
	err = c.MarkCell(Cross)
	if err == nil {
		t.Logf("Expected %v, actual: %v\n",
			fmt.Errorf("Cell already marked"), err)
		t.FailNow()
	}
}

func TestMarkCellNoMark(t *testing.T) {
	c := NewCell()
	err := c.MarkCell(NoMark)
	if err == nil {
		t.Logf("Expected %v actual %v\n",
			fmt.Errorf("Cant mark cell with no mark"), err)
		t.FailNow()
	}
}
