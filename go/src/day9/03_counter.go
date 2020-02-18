package main

import (
	"fmt"
)

func main() {
	max := 50
	go func() {
		for i := 0; i < max; i++ {
			fmt.Println("func1", i)
		}
	}()

	go func() {
		for i := 0; i < max; i++ {
			fmt.Println("func2", i)
		}
	}()
	fmt.Println("end of main")
	fmt.Scanln()
}
