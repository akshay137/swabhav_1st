package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

func main() {
	resp, err := http.Get("http://www.google.com")
	if err != nil {
		fmt.Println("Error in request:", err.Error())
		return
	}

	fmt.Println("contentLength:", resp.ContentLength)
	defer resp.Body.Close()
	bs, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Error in reading:", err.Error())
		return
	}
	fmt.Println("size of body:", len(bs))
	fmt.Println("contentLength:", resp.ContentLength)
	// fmt.Println(string(bs))
}
