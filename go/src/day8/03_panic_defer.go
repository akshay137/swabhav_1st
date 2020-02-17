package main

import (
	"fmt"
)

func m1() {
	defer func() {
		r := recover()
		fmt.Println("recovering:", r)
	}()
	panic("Get me out of here, Please")
	fmt.Println("End of m1")
}

func main() {
	m1()
	fmt.Println("End of main")
}
