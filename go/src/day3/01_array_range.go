package main

import (
	"fmt"
	"reflect"
)

func main() {
	marks := [3]int{10, 20, 30}
	fmt.Println("marks", marks)
	fmt.Println("length of marks", len(marks))
	fmt.Println("typeof marks", reflect.TypeOf(marks))
	for index, value := range marks {
		fmt.Println("[", index, "] = ", value)
	}

	// marksCopy := marks
	var marksCopy [3]int = marks
	marksCopy[0] = 0
	fmt.Println("marksCopy", marksCopy)
	fmt.Println("marks", marks)
}
