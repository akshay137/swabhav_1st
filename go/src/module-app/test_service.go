package main

import (
	"fmt"
	svc "module-app/service"
)

func init() {
	fmt.Println("init of main")
}

func main() {
	fmt.Println(svc.PORT)
}
