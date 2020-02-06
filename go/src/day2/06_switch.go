package main

import (
	"fmt"
)

func main() {
	switch cgpa := 10; cgpa {
	case 10:
		fmt.Println("Great work")
		fallthrough
	case 8, 9:
		fmt.Println("You got Distincion")
	case 7:
		fmt.Println("You got A")
	case 6:
		fmt.Println("You got B")
	default:
		fmt.Println("You are OK")
	}
}
