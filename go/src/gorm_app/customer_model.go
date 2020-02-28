package main

import (
	"fmt"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

type Customer struct {
	ID        int
	Firstname string `gorm:"type:varchar(50)"`
	LastName  string `gorm:"type:varchar(50)"`
}

func main() {
	db, err := gorm.Open("mysql", "root:root@/swabhav")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	defer db.Close()

	ecustomer := &Customer{}
	db.Debug().DropTableIfExists(ecustomer)
	db.Debug().CreateTable(ecustomer)

	cust := &Customer{
		Firstname: "Akshay",
		LastName:  "N",
	}
	db.Debug().Create(&cust)
	fmt.Println("after create", cust)
	db.Debug().Create(&Customer{Firstname: "hello", LastName: "bye"})

	customers := make([]Customer, 0)
	db.Debug().Find(&customers)
	fmt.Println(customers)

}
