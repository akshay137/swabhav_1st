package main

import (
	"fmt"
)

type node struct {
	data int32
	next *node
}

func print(n *node) {
	fmt.Println(n.data)
}

func main() {
	var name = "name"
	var cgpa float64 = 7.5
	rollno := 101
	// foo := "bar"

	var head = node{100, nil}
	print(&head)

	fmt.Println("name", name)
	fmt.Println("cgpa", cgpa)
	fmt.Println("rollno", rollno)
	fmt.Println("no foo here")
}
