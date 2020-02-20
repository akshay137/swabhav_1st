package main

import (
	"bufio"
	"bytes"
	"fmt"
	"io"
	"io/ioutil"
	"net/http"
	"os"
)

type Page struct {
	url     string
	file    string
	content string
}

func savePage(url, file string, out chan *Page) {
	res, err := http.Get(url)
	if err != nil {
		out <- nil
		return
	}

	f, err := os.Create(file)
	if err != nil {
		out <- nil
		return
	}
	bs, err := ioutil.ReadAll(res.Body)
	if err != nil {
		res.Body.Close()
		out <- nil
		return
	}
	out <- &Page{url, file, string(bs)}
	res.Body = ioutil.NopCloser(bytes.NewBuffer(bs))
	w := bufio.NewWriter(f)
	io.Copy(w, res.Body)
	f.Close()
}

func printPage(page *Page) {
	fmt.Printf("url: %s\ncontent: %s\n", page.url, page.content)
}

func main() {
	out := make(chan *Page, 1)
	urls := []struct {
		url  string
		file string
	}{
		{"http://www.google.com", "google.html"},
		{"http://swabhavtechlabs.com/", "swabhav.html"},
		{"http://www.golang.org", "golang.html"},
	}
	for _, data := range urls {
		fmt.Println("Loading url", data.url)
		go savePage(data.url, data.file, out)
	}
	for i := 0; i < len(urls); i++ {
		page := <-out
		if page != nil {
			printPage(page)
		}
	}
	// go savePage("http://www.google.com", "google.html", out)
	// fmt.Println("response:", <-out)
}
