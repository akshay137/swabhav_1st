package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"path"
)

func isDir(name string) (bool, error) {
	f, err := os.Stat(name)
	if err != nil {
		return false, err
	}
	return f.IsDir(), nil
}

func scanDir(name string) {
	fmt.Println(name)
	files, err := ioutil.ReadDir(name)
	if err != nil {
		panic(err.Error())
	}
	for _, v := range files {
		// fmt.Println(v.Name())
		fullpath := path.Join(name, v.Name())
		if res, _ := isDir(fullpath); res {
			scanDir(fullpath)
		} else {
			fmt.Println(fullpath)
		}
	}
}

func main() {
	defer func() {
		if r := recover(); r != nil {
			fmt.Println("Recoverd:", r)
		}
	}()
	scanDir("parent")
}
