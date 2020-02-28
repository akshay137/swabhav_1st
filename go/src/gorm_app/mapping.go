package main

import (
	"fmt"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

type Passport struct {
	gorm.Model
	Address string
	UserId  uint
}

type User struct {
	gorm.Model
	Name     string
	Passport *Passport
}

func main() {
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav?parseTime=true")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	defer db.Close()

	db.AutoMigrate(&Passport{}, &User{})
	db.Model(&Passport{}).AddForeignKey("user_id", "users(id)",
		"CASCADE", "CASCADE")

	db.Save(&User{
		Name: "akshay",
		Passport: &Passport{
			Address: "mumbai",
		},
	})

	user := &User{}
	db.First(user, "id=?", 1)
	fmt.Println(user)

	// delete
	// db.Delete(&User{}, "id=?", 1)
	// db.Delete(&Passport{}, "id=?", 1)

	// users := []User{}
	ns := &User{}
	// db.Model(&Passport{}).Association("user_id").Find(&users)
	// ps := &Passport{}
	// db.Model(ns).Related(ps, "user_id")
	fmt.Println("")
	db.Preload("Passport").First(ns)
	// fmt.Println(users)
	fmt.Println(ns.Name, ns.Passport.Address)
}
