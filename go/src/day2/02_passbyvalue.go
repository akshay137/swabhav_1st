package main

import (
	"fmt"
)

func main() {
	case1()
	case2()
}

func changeMark(m int) {
	m = 0
}

func case1() {
	fmt.Println("Case 1")
	mark := 10
	changeMark(mark)
	fmt.Println("mark", mark)
}

func changeScore(s *int) {
	*s = 0
}

func case2() {
	fmt.Println("Case 2")
	score := 100
	changeScore(&score)
	fmt.Println("score", score)
}
