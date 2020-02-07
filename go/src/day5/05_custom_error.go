package main

import (
	"fmt"
)

type notEvenError struct {
	no int
}

func (e *notEvenError) Error() string {
	return fmt.Sprintf("%d is not even number", e.no)
}

func cubeEven(no int) (int, error) {
	if no%2 != 0 {
		return 0, &notEvenError{no}
	}
	return no * no * no, nil
}

func main() {
	no := 3
	// no := 4
	if r, err := cubeEven(no); err != nil {
		fmt.Printf("error: %s\n", err.Error())
	} else {
		fmt.Printf("Cube of %d is %d\n", no, r)
	}
}
