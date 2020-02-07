package main

import (
	"fmt"
	"strconv"
)

func cubeEven(no int) (int, error) {
	if no%2 != 0 {
		// return 0, errors.New(fmt.Sprintf("No %d is not even", no))
		return 0, fmt.Errorf("No %d is not even", no)
	}
	return (no * no * no), nil
}

func main() {
	no := 3
	if r, err := cubeEven(no); err != nil {
		fmt.Printf("Error Type: %T\n", err)
		fmt.Printf("Error message: %s\n", err.Error())
		fmt.Printf("Result: %d\n", r)
	} else {
		s := strconv.Itoa(r)
		fmt.Printf("s: %s\n", s)
		fmt.Printf("Cube of %d is: %d\n", no, r)
	}
}
