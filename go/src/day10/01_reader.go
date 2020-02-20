package main

import (
	"fmt"
	"net/http"
)

func main() {
	resp, err := http.Get("http://www.google.com")
	if err != nil {
		fmt.Println("Error:", err.Error())
		return
	}
	fmt.Printf("%T\n", resp)
	// fmt.Println(resp)
	fmt.Printf("%T\n", resp.Body)

	defer resp.Body.Close()
	bs := make([]byte, 1*1024)
	resp.Body.Read(bs)
	fmt.Println(string(bs))
}
