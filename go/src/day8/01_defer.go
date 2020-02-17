package main

import (
	"fmt"
)

func f2() {
	fmt.Println("Inside f2")
}

func main() {
	defer func() {
		fmt.Println("Inside anon function")
	}()
	defer f2()
	fmt.Println("End on main")
}
