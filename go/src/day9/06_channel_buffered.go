package main

import (
	"fmt"
)

func main() {
	ch := make(chan string, 10)
	ch <- "hello"
	fmt.Println(<-ch)
	ch <- "hello again 1"
	ch <- "hello again 2"
	fmt.Println(<-ch)
}
