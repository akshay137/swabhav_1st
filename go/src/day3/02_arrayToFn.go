package main

import (
	"fmt"
)

func main() {
	marks := [3]int{10, 20, 30}
	fmt.Println("marks before modify: ", marks)
	modify(marks)
	fmt.Println("marks after modify: ", marks)

	fmt.Println("marks before modifyPointer: ", marks)
	modifyPointer(&marks)
	fmt.Println("&marks", &marks)
	fmt.Println("marks after modifyPointer: ", marks)

	pMarks := &marks
	fmt.Println("pmarks", pMarks)
	pMarks[0] = 1
	fmt.Println("pmarks", pMarks)
	fmt.Println("marks", marks)
}

func modify(m [3]int) {
	// fmt.Println("typeof m []int", reflect.TypeOf(m))
	size := len(m)
	for i := 0; i < size; i++ {
		m[i] = 0
	}
}

func modifyPointer(m *[3]int) {
	// fmt.Println("typeof m *[]int", reflect.TypeOf(m))
	fmt.Println("m", m)
	fmt.Println("&m", &m)
	size := len(*m)
	for i := 0; i < size; i++ {
		m[i] = 0
		// (*m)[i] = 0
	}
}
