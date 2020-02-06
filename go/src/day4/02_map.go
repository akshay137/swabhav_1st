package main

import (
	"fmt"
)

func main() {
	ports := map[int]string{}
	fmt.Println("ports", ports)
	ports[80] = "http"
	ports[22] = "ssh"
	ports[23] = "telnet"

	fmt.Println("ports[0]", ports[0])
	v, stat := ports[0]
	fmt.Println("v", v, "stat", stat)

	fmt.Println("range over ports")
	for k, v := range ports {
		fmt.Println(k, ":", v)
	}

	ports[80] = "not http"

	delete(ports, 800)
	delete(ports, 23)
	fmt.Println("ports", ports)
}
