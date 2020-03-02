package game

import (
	"github.com/akshay137/tictactoe/board"
)

type Player struct {
	name string
	mark board.CellMark
}

func NewPlayer(name string, mark board.CellMark) *Player {
	return &Player{
		name: name,
		mark: mark,
	}
}

func (p *Player) Name() string {
	return p.name
}

func (p *Player) Mark() board.CellMark {
	return p.mark
}
