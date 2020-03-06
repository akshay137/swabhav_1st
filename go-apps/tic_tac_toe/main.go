package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"

	"github.com/akshay137/tictactoe/board"
	"github.com/akshay137/tictactoe/game"
)

func main() {
	fmt.Println("Tic Tac Toe")
	bSize := int32(3)
	p1 := game.NewPlayer("p1", board.Cross)
	p2 := game.NewPlayer("p2", board.Zero)
	p := [2]*game.Player{p1, p2}
	g := game.NewGame(bSize, p)

	g.PrintBoard()
	gameRes := board.GAME_RUNNING
	user := g.CurrentUser()
	reader := bufio.NewReader(os.Stdin)
	for gameRes == board.GAME_RUNNING {
		user = g.CurrentUser()
		fmt.Println("turn:", user.Name())
		id := getInput(reader, bSize)
		if id == -1 {
			fmt.Println("Please enter valid id")
			fmt.Println("valid format: x y")
			continue
		}
		res, err := g.Play(id)
		if err != nil {
			fmt.Println(err.Error())
			continue
		}
		gameRes = res
		g.PrintBoard()
	}
	switch gameRes {
	case board.GAME_DRAW:
		fmt.Println("Game was draw")
	case board.GAME_WIN:
		fmt.Println(user.Name(), " won")
	default:
		fmt.Println("How the hell did you manage this!")
	}
}

func getInput(reader *bufio.Reader, size int32) int32 {
	fmt.Println("Enter cell id")
	line, err := reader.ReadString('\n')
	if err != nil {
		return -1
	}
	line = line[:len(line)-1]
	s := strings.Split(line, " ")
	if len(s) != 2 {
		return -1
	}
	tx, err := strconv.ParseInt(s[0], 10, 32)
	x := int32(tx)
	if err != nil || x >= size || x < 0 {
		return -1
	}
	ty, err := strconv.ParseInt(s[1], 10, 32)
	y := int32(ty)
	if err != nil || y >= size || y < 0 {
		return -1
	}
	return x*size + y
}
