package main

import (
	"fmt"
)

func f1() {
	defer func() {
		fmt.Println("inside defer of f1")
	}()
	fmt.Println("inside f1")
	f2()
}

func f2() {
	defer func() {
		fmt.Println("Inside defer of f2")
	}()
	fmt.Println("Inside f2")
}

func main() {
	f1()
	fmt.Println("End of main")
}
