package main

import (
	"fmt"
	"time"
)

func main() {
	// case1()
	case2()
}

func case1() {
	for {
		fmt.Printf("%s\r", time.Now())
	}
}

func case2() {
	for i := 1; i < 5; i++ {
		fmt.Println("i", i)
	}
}
