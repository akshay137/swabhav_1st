package main

import (
	"fmt"
)

type student struct {
	name string
}

func displayManyThings(elements ...interface{}) {
	for _, value := range elements {
		fmt.Println("value is:", value)
		if student, ok := value.(student); ok {
			i := float32(1)
			fmt.Println("I found a student", student, i)
			fmt.Printf("typeof ok: %T\n", ok)
		}
	}
}

func main() {
	var anything interface{}
	anything = 10
	fmt.Printf("typeof anything: %T\n", anything)
	anything = "hello"
	fmt.Printf("typeof anything: %T\n", anything)
	anything = student{"thanos"}
	fmt.Printf("typeof anything: %T\n", anything)

	fmt.Println()
	displayManyThings(10, 20, "hello", student{"hello"})
}
