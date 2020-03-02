package game

import (
	"fmt"

	"github.com/akshay137/tictactoe/board"
)

type Game struct {
	b       *board.Board
	ra      *board.ResultAnalyzer
	turn    uint8
	players [2]*Player
}

func NewGame(boardSize int32, players [2]*Player) *Game {
	return &Game{
		b:       board.NewBoard(boardSize),
		ra:      board.NewResultAnalyzer(),
		players: players,
		turn:    0,
	}
}

func (g *Game) Play(cellId int32) (board.GameStatus, error) {
	currentMark := g.players[g.turn].mark
	err := g.b.MarkCell(cellId, currentMark)
	if err != nil {
		return 0, err
	}
	g.changeTurn()
	return g.ra.GetBoardStatus(g.b, currentMark, cellId), nil
}

func (g *Game) changeTurn() {
	g.turn = (g.turn + 1) % uint8(len(g.players))
}

func (g *Game) CurrentUser() *Player {
	return g.players[g.turn]
}

func (g *Game) PrintBoard() {
	fmt.Println(g.b)
}
