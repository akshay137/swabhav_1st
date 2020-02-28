package service

import (
	"fmt"

	"github.com/akshay137/gomodapp/model"
)

// DataService __
type DataService struct{}

// NewDataService return new service instance
func NewDataService() *DataService {
	return &DataService{}
}

// ProcessData process student data
func (ds *DataService) ProcessData(st *model.Student) {
	fmt.Println("Processing", st)
}
