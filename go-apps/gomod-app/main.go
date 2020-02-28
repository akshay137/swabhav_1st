package main

import (
	"github.com/akshay137/gomodapp/model"
	"github.com/akshay137/gomodapp/service"
)

func main() {
	ds := service.NewDataService()
	ds.ProcessData(&model.Student{
		ID:   1,
		Name: "Hello",
	})
}
