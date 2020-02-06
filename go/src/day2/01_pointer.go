package main

import (
	"fmt"
	"reflect"
)

func main() {
	case1()
	case2()
}

func case1() {
	fmt.Println("Case 1")
	name := "Swabhav"
	fmt.Println("typeof name:", reflect.TypeOf(name))
	fmt.Println("typeof &name:", reflect.TypeOf(&name))
	fmt.Println("name:", name)
	fmt.Println("&name:", &name)

	// pointerToname := &name
	var pointerToname *string = &name
	fmt.Println("typeof pointerToname:", reflect.TypeOf(pointerToname))
	fmt.Println("pointerToname:", pointerToname)
	fmt.Println("*pointerToname:", *pointerToname)
}

func case2() {
	fmt.Println("Case 2")
	mark := 10
	pointerToMark := &mark
	pointerToPointer := &pointerToMark
	fmt.Println("*pointerToMark", *pointerToMark)
	fmt.Println("*pointerToPointer", *pointerToPointer)
	fmt.Println("pointerToMark", pointerToMark)
	fmt.Println("pointerToMark == *pointerToPointer",
		pointerToMark == *pointerToPointer)
	fmt.Println("**pointerToPointer == *pointerToMark",
		**pointerToPointer == *pointerToMark)
}
