package main

import (
	"fmt"
)

func main() {
	if cgpa := 8; cgpa >= 8 {
		fmt.Println("Distinction")
	} else if cgpa >= 7 {
		fmt.Println("Great")
	} else {
		fmt.Println("OK")
	}
}
