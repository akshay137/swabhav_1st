package main

import (
	"fmt"
)

type Student struct {
	rollno    int
	firstname string
	lastname  string
}

func modify(s Student) {
	s.rollno = 200
	s.firstname = ""
	s.lastname = ""
}

func modifyRef(s *Student) {
	s.rollno = 200
	s.firstname = "x"
	s.lastname = "y"
}

func main() {
	s := Student{
		rollno:    100,
		firstname: "a",
		lastname:  "b",
	}
	fmt.Println("before modify", s)
	modify(s)
	fmt.Println("after modify", s)
	fmt.Println("before modifyRef", s)
	modifyRef(&s)
	fmt.Println("after modifyRef", s)
}
