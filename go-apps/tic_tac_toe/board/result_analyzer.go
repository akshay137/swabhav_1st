package board

type GameStatus int

const (
	GAME_RUNNING GameStatus = 0
	GAME_WIN     GameStatus = 1
	GAME_DRAW    GameStatus = 2
)

type ResultAnalyzer struct{}

func NewResultAnalyzer() *ResultAnalyzer {
	return &ResultAnalyzer{}
}

func (ra *ResultAnalyzer) GetBoardStatus(b *Board, m CellMark, cellId int32) GameStatus {
	if m == NoMark {
		return GAME_RUNNING
	}
	if checkRow(b, m, cellId) {
		return GAME_WIN
	}
	if checkColumn(b, m, cellId) {
		return GAME_WIN
	}
	if checkDiagonal(b, m, cellId) {
		return GAME_WIN
	}
	if checkReverseDiagonal(b, m, cellId) {
		return GAME_WIN
	}
	if b.marked == b.size*b.size {
		return GAME_DRAW
	}
	return GAME_RUNNING
}

func checkRow(b *Board, m CellMark, cellId int32) bool {
	st := (cellId / b.size) * b.size
	for i := st; i < st+b.size; i++ {
		if b.cells[i].mark != m {
			return false
		}
	}
	return true
}

func checkColumn(b *Board, m CellMark, cellId int32) bool {
	c := cellId % b.size
	for i := int32(0); i < b.size; i++ {
		if b.cells[i*b.size+c].mark != m {
			return false
		}
	}
	return true
}

func checkDiagonal(b *Board, m CellMark, cellId int32) bool {
	if cellId%b.size != cellId/b.size {
		return false
	}
	for i := int32(0); i < b.size; i++ {
		if b.cells[i*b.size+i].mark != m {
			return false
		}
	}
	return true
}

func checkReverseDiagonal(b *Board, m CellMark, cellId int32) bool {
	row := cellId / b.size
	column := cellId % b.size
	tsize := b.size - 1
	if row != tsize-column {
		return false
	}
	for i := int32(0); i < b.size; i++ {
		index := (i * b.size) + (tsize - i)
		if b.cells[index].mark != m {
			return false
		}
	}
	return true
}
