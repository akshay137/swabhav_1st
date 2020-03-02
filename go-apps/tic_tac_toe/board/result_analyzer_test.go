package board

import (
	"fmt"
	"testing"
)

var b_size int32 = 3

func clearBoard(b *Board) {
	for i := int32(0); i < b.size*b.size; i++ {
		b.cells[i].mark = NoMark
	}
}

func printBoard(b *Board) {
	for i := int32(0); i < b.size; i++ {
		for j := int32(0); j < b.size; j++ {
			fmt.Printf("%s ",
				b.cells[i*b.size+j].mark)
		}
		fmt.Println("")
	}
}

func fillVertical(b *Board, c int32, m CellMark) {
	for i := int32(0); i < b.size; i++ {
		b.cells[i*b.size+c].mark = m
	}
}

func fillHorizontal(b *Board, r int32, m CellMark) {
	lim := r*b.size + b.size
	for i := r * b.size; i < lim; i++ {
		b.cells[i].mark = m
	}
}

func fillDiagonal(b *Board, m CellMark) {
	for i := int32(0); i < b.size; i++ {
		b.cells[i*b.size+i].mark = m
	}
}

func fillRDiagonal(b *Board, m CellMark) {
	for i := int32(0); i < b.size; i++ {
		index := (i * b.size) + (b.size - 1 - i)
		b.cells[index].mark = m
	}
}

func fillForDraw(b *Board) {
	for i := int32(0); i < b.size-1; i++ {
		for j := int32(0); j < b.size; j++ {
			index := i*b.size + j
			if i%2 == 0 {
				if j%2 == 0 {
					b.MarkCell(index, Cross)
				} else {
					b.MarkCell(index, Zero)
				}
			} else {
				if j%2 == 0 {
					b.MarkCell(index, Zero)
				} else {
					b.MarkCell(index, Cross)
				}
			}
		}
	}
	rstart := (b.size - 1) * b.size
	for i := rstart; i < rstart+b.size; i++ {
		if i%2 == 0 {
			b.MarkCell(i, Zero)
		} else {
			b.MarkCell(i, Cross)
		}
	}
}

func TestCheckVerticalPass(t *testing.T) {
	b := NewBoard(b_size)
	ra := NewResultAnalyzer()
	for i := int32(0); i < b_size; i++ {
		clearBoard(b)
		fillVertical(b, i, Cross)
		for j := int32(0); j < b_size; j++ {
			stat := ra.GetBoardStatus(b, Cross,
				j*b_size+i)
			if stat != GAME_WIN {
				t.Logf("Expected: %v got %v",
					GAME_WIN, stat)
				t.FailNow()
			}
		}
	}
}

func TestCheckHorizontalPass(t *testing.T) {
	b := NewBoard(b_size)
	ra := NewResultAnalyzer()
	for i := int32(0); i < b_size; i++ {
		clearBoard(b)
		fillHorizontal(b, i, Cross)
		for j := int32(0); j < b_size; j++ {
			stat := ra.GetBoardStatus(b, Cross,
				i*b.size+j)
			if stat != GAME_WIN {
				t.Logf("for row: %v cell: %v Expected: %v got %v",
					i, i*b.size+j,
					GAME_WIN, stat)
				printBoard(b)
				t.FailNow()
			}
		}
	}
}

func TestCheckDiagonalPass(t *testing.T) {
	b := NewBoard(b_size)
	ra := NewResultAnalyzer()
	clearBoard(b)
	fillDiagonal(b, Cross)
	for i := int32(0); i < b_size; i++ {
		stat := ra.GetBoardStatus(b, Cross, i*b_size+i)
		if stat != GAME_WIN {
			t.Logf("Expected: %v got %v",
				GAME_WIN, stat)
			t.FailNow()
		}
	}
}

func TestCheckRDiagonalPass(t *testing.T) {
	b := NewBoard(b_size)
	ra := NewResultAnalyzer()
	clearBoard(b)
	fillRDiagonal(b, Cross)
	for i := int32(0); i < b_size; i++ {
		index := (i * b_size) + (b_size - 1 - i)
		stat := ra.GetBoardStatus(b, Cross, index)
		if stat != GAME_WIN {
			t.Logf("Expected: %v got %v",
				GAME_WIN, stat)
			t.FailNow()
		}
	}
}

func TestCheckDrawPass(t *testing.T) {
	b := NewBoard(b_size)
	ra := NewResultAnalyzer()
	clearBoard(b)
	fillForDraw(b)
	for i := int32(0); i < b_size; i++ {
		index := (i * b_size) + (b_size - 1 - i)
		stat := ra.GetBoardStatus(b, Cross, index)
		if stat != GAME_DRAW {
			t.Logf("Expected: %v got %v %v",
				GAME_DRAW, stat, b.marked)
			printBoard(b)
			t.FailNow()
		}
	}
}
