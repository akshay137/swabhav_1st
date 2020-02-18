package main

import (
	"fmt"
)

func getData(ch chan string) {
	ch <- "hello"
}

func main() {
	ch := make(chan string)
	go getData(ch)
	fmt.Println(<-ch)
}
