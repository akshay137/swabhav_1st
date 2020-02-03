package main

import (
	"fmt"
	"reflect"
)

var (
	name   string
	cgpa   float64
	rollno int64
	foo    string
)

func main() {
	fmt.Println("typeof name", reflect.TypeOf(name), "value of name", name)
	fmt.Println("typeof cgpa", reflect.TypeOf(cgpa), "value of cgpa", cgpa)
	fmt.Println("typeof rollno", reflect.TypeOf(rollno),
		"value of rollno", rollno)
}
