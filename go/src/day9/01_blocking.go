package main

import (
	"fmt"
	"time"
)

func main() {
	func() {
		time.Sleep(3 * time.Second)
		fmt.Println("Inside f1")
	}()
	func() {
		fmt.Println("Inside f2")
	}()
	fmt.Println("end of main")
}
