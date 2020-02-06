package main

import (
	"fmt"
)

// Student contains basic details of student
type Student struct {
	rollno    int
	firstname string
	lastname  string
}

func main() {
	s1 := Student{
		rollno:    101,
		firstname: "a",
		lastname:  "b",
	}
	fmt.Println("s1", s1)

	s2 := s1
	s2.rollno = 102
	fmt.Println("s1", s1)
	fmt.Println("s2", s2)
	fmt.Printf("type s1: %T\n", s1)
	fmt.Printf("type &s1 %T\n", &s1)
}
