package main

import (
	"fmt"
	_ "module-app/service"
)

func init() {
	fmt.Println("init of main")
}

func main() {
	fmt.Println("inside main")
}
