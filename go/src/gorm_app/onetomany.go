package main

import (
	"fmt"
	"time"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

type Order struct {
	gorm.Model
	CustomerId uint
	Amount     float64
	PlacedAt   time.Time
}

type Customer struct {
	gorm.Model
	Name     string
	Location string
	Orders   []Order `gorm:'foreignkey:CustomerId`
}

func main() {
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav?parseTime=true")
	if err != nil {
		fmt.Println("Error in db opening", err.Error())
		return
	}
	defer db.Close()

	db.AutoMigrate(&Customer{}, &Order{})
	db.Model(&Order{}).
		AddForeignKey("customer_id", "customers(id)",
			"CASCADE", "CASCADE")

	/* db.Save(&Customer{
		Name:     "Bot",
		Location: "Mumbai",
		Orders: []Order{
			Order{
				Amount:   100,
				PlacedAt: time.Now(),
			},
			Order{
				Amount:   200,
				PlacedAt: time.Now().Add(time.Hour * 24),
			},
			Order{
				Amount:   50,
				PlacedAt: time.Now().Add(time.Hour * -24),
			},
		},
	})
	db.Save(&Customer{
		Name:     "Test",
		Location: "Mumbai",
		Orders: []Order{
			Order{
				Amount:   100,
				PlacedAt: time.Now(),
			},
			Order{
				Amount:   200,
				PlacedAt: time.Now().Add(time.Hour * 24),
			},
		},
	}) */

	customer := &Customer{}
	// db.Where("id=?", 1).First(customer)
	// printInfo(customer)

	// db.Where("location=?", "mumbai").
	// 	Preload("Orders").First(customer)
	db.Where("location=?", "mumbai").Or("id=?", 1).
		Preload("Orders").Last(customer)
	printInfo(customer)

	// customers := []Customer{}
	// db.Where("location=?", "mumbai").Preload("Orders").
	// 	Find(&customers)
	// for _, c := range customers {
	// 	printInfo(&c)
	// }

}

func printInfo(customer *Customer) {
	fmt.Println("Name:", customer.Name)
	fmt.Println("Location:", customer.Location)
	for i, o := range customer.Orders {
		fmt.Println("Order: #", i)
		fmt.Println("\tAmount:", o.Amount)
		fmt.Println("\tTime:", o.PlacedAt)
	}
}
