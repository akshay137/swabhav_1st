package board

import (
	"fmt"
)

type Board struct {
	cells  []Cell
	size   int32
	marked int32
}

func NewBoard(size int32) *Board {
	c := make([]Cell, size*size)
	for i := 0; i < len(c); i++ {
		c[i] = *NewCell()
	}
	return &Board{
		cells: c,
		size:  size,
	}
}

func (b *Board) GetSize() int32 {
	return b.size
}

func (b *Board) MarkCell(cellId int32, m CellMark) error {
	if cellId > b.size*b.size || cellId < 0 {
		return fmt.Errorf("index out of bounds")
	}
	err := b.cells[cellId].MarkCell(m)
	if err != nil {
		return err
	}
	b.marked++
	return nil
}

func (b *Board) String() string {
	str := "  "
	for i := int32(0); i < b.size; i++ {
		str = fmt.Sprintf("%s %v", str, i)
	}
	str = fmt.Sprintf("%s\n", str)
	for i := int32(0); i < b.size; i++ {
		row := fmt.Sprintf("%v: ", i)
		for j := int32(0); j < b.size; j++ {
			row = fmt.Sprintf("%s%s ", row,
				b.cells[i*b.size+j].mark)
		}
		str = fmt.Sprintf("%s%s\n", str, row)
	}
	return str
}
