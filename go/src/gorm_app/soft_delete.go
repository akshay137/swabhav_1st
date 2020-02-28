package main

import (
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"

	"fmt"
)

type Emp struct {
	gorm.Model
	Name   string
	Salary float64
}

func main() {
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav?parseTime=true")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	defer db.Close()

	db.AutoMigrate(&Emp{})

	db.Create(&Emp{Name: "hello", Salary: 100})
	db.Create(&Emp{Name: "bye", Salary: 110})
	db.Create(&Emp{Name: "tata", Salary: 90})

	emps := []Emp{}
	db.Find(&emps)
	fmt.Println(emps)

	emp := &Emp{}
	db.First(emp, "name=?", "hello")
	db.Delete(emp)

	db.Find(&emps)
	fmt.Println(emps)

	e2 := &Emp{}
	db.First(e2, "name=?", "hello")
	fmt.Println("hello", e2)
}
