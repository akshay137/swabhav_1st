package main

import (
	"fmt"
	"runtime"
)

func main() {
	fmt.Println("Hello from fmt println")
	fmt.Println("GO OS: " + runtime.GOOS)
	fmt.Println("GO ARCH: " + runtime.GOARCH)
}
