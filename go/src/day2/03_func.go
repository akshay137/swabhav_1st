package main

import (
	"fmt"
	"strings"
)

func main() {
	// println(convertToUpper("swabhav", "techlabs"))
	fmt.Println(convertToUpper("maharashtra", "mumbai"))

	// state, city := convertToUpper("maharashtra", "mumbai")
	state, _ := convertToUpper("maharashtra", "mumbai")
	// fmt.Println("state", state, city)
	_, _ = convertToUpper("maharashtra", "mumbai")
	fmt.Println("state:", state)
}

func convertToUpper(firstName, lastName string) (string, string) {
	return strings.ToUpper(firstName), strings.ToUpper(lastName)
}
