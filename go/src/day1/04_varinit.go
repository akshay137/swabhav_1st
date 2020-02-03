package main

import (
	"fmt"
)

var (
	name, cgpa, rollno = "Name", 7.5, 1001
	foo                = "bar"
)

func main() {
	fmt.Printf("name: %s\n", name)
	fmt.Printf("cgpa %f\n", cgpa)
	fmt.Printf("rollno %d\n", rollno)
}
