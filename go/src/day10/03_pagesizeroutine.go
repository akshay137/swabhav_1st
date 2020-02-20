package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

type Page struct {
	Link       string
	Size       int
	StatusCode int
	Status     string
}

func loadPageInfo(url string, ch chan *Page) {
	fmt.Println("getting data for", url)
	res, err := http.Get(url)
	if err != nil {
		// fmt.Println("Error making request:", url, err.Error())
		// ch <- nil
		ch <- &Page{
			Link:       url,
			Size:       -1,
			StatusCode: -1,
			Status:     err.Error(),
		}
		return
	}

	defer res.Body.Close()
	bs, err := ioutil.ReadAll(res.Body)
	if err != nil {
		// fmt.Println("Error reading content:", url, err.Error())
		// ch <- nil
		ch <- &Page{
			Link:       url,
			Size:       -1,
			StatusCode: res.StatusCode,
			Status:     err.Error(),
		}
		return
	}
	ch <- &Page{
		Link:       url,
		Size:       len(bs),
		StatusCode: res.StatusCode,
		Status:     res.Status,
	}
}

func printInfo(page *Page) {
	fmt.Printf("Page detail from url: %s %dkB\n",
		page.Link, page.Size/1024)
}

func main() {
	urls := []string{
		"http://www.google.com",
		"http://www.golang.org",
		"http://swabhavtechlabs.com/",
	}
	pageChannel := make(chan *Page, 1)
	for _, url := range urls {
		go loadPageInfo(url, pageChannel)
	}

	for i := 0; i < len(urls); i++ {
		page := <-pageChannel
		if page.StatusCode == -1 || page.Size == -1 {
			fmt.Println("Error loading data", page.Status)
			continue
		}
		printInfo(page)
	}
}
