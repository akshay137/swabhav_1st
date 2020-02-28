package main

import (
	"fmt"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

type Customer struct {
	Id        int
	FirstName string `gorm:"type:varchar(50)"`
	LastName  string `gorm:"type:varchar(50)"`
}

func main() {
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	defer db.Close()

	ec := &Customer{}
	db.DropTableIfExists(ec)
	db.CreateTable(ec)

	customers := []Customer{
		Customer{FirstName: "hello", LastName: "bye"},
		Customer{FirstName: "ratan", LastName: "tata"},
		Customer{FirstName: "mukesh", LastName: "ambani"},
		Customer{FirstName: "aditya", LastName: "birla"},
	}
	for _, c := range customers {
		db.Debug().Create(&c)
	}

	hello := &Customer{}
	db.Debug().Find(hello, "first_name=?", "hello")
	fmt.Println("hello", hello)

	allCust := make([]Customer, 0)
	db.Debug().Find(&allCust)
	fmt.Println(allCust)

	hello.FirstName = "Welcome"
	hello.Id = 2
	db.Debug().Save(hello)

	// allCust := make([]Customer, 0)
	db.Debug().Find(&allCust)
	fmt.Println(allCust)

	db.Delete(hello)
	db.Debug().Find(&allCust)
	fmt.Println(allCust)
}
