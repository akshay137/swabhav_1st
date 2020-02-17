package main

import (
	"fmt"
	"module-app/greetings"
	"module-app/greetings/hindi"
)

func main() {
	fmt.Println(greetings.Hello())
	fmt.Println(hindi.Namaste())
}
