package main

import (
	"fmt"
)

type Student struct {
	rollno    int
	firstname string
	lastname  string
}

func (student Student) modify() {
	student.rollno *= 2
}

func (student *Student) modifyRef() {
	student.rollno *= 2
}

func main() {
	s := Student{
		rollno:    1,
		firstname: "a",
		lastname:  "b",
	}
	fmt.Println("student", s)
	s.modify()
	fmt.Println("after s.modify()", s)
	s.modifyRef()
	fmt.Println("after s.modifyRef()", s)
}
