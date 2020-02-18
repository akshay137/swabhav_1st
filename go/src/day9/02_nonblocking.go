package main

import (
	"fmt"
	"time"
)

func main() {
	go func() {
		fmt.Println("f1 now sleeping")
		time.Sleep(3 * time.Second)
		fmt.Println("inside f1")
	}()
	go func() {
		fmt.Println("inside f2")
	}()
	fmt.Println("end of main")
	fmt.Scanln()
}
