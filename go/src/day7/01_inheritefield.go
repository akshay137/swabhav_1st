package main

import (
	"fmt"
)

type Name struct {
	firstname string
	lastname  string
}

func (n *Name) ToString() {
	fmt.Println(n.firstname, n.lastname)
}

type Customer struct {
	Name
	balance float64
}

type Student struct {
	Name
	cgpa float64
}

func (s *Student) ToString() {
	s.Name.ToString()
	fmt.Println("cgpa", s.cgpa)
}

func main() {
	c1 := Customer{
		Name{
			firstname: "hello",
			lastname:  "bye",
		},
		1000,
	}
	(&(c1.Name)).ToString()
	c1.ToString()
	s1 := Student{Name{"st", "modal"}, 5.8}
	s1.ToString()
}
