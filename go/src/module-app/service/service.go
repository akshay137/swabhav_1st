package service

import (
	"fmt"
)

var (
	PORT = 8080
	URL  = "www.abc.com"
)

const (
	CLIENT = "CyberInc"
)

func init() {
	fmt.Println("init of service", PORT, URL, CLIENT)
}
