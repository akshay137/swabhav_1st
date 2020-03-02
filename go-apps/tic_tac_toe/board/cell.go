package board

import (
	"fmt"
)

type CellMark uint8

var cmStrings = [...]string{"_", "X", "O"}

// valid marks for Cell
const (
	NoMark CellMark = 0
	Cross  CellMark = 1
	Zero   CellMark = 2
)

type Cell struct {
	mark CellMark
}

func NewCell() *Cell {
	return &Cell{mark: NoMark}
}

func (c *Cell) Mark() CellMark {
	return c.mark
}

func (c *Cell) isEmpty() bool {
	return c.mark == NoMark
}

func (c *Cell) MarkCell(mark CellMark) error {
	if mark == NoMark {
		return fmt.Errorf("Cant mark cell with no mark")
	}
	if c.mark != NoMark {
		return fmt.Errorf("Cell already marked")
	}
	c.mark = mark
	return nil
}

func (m CellMark) String() string {
	return cmStrings[m]
}
