package main

import (
	"fmt"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

type TxnUser struct {
	gorm.Model
	Name     string `gorm:"unique"`
	Location string
}

func main() {
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav?parseTime=true")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	defer db.Close()

	db.AutoMigrate(&TxnUser{})
	// db.Create(&TxnUser{Name: "bot", Location: "m"})
	txn := db.Begin()
	if err := txn.Create(&TxnUser{Name: "bot", Location: "m"}).Error; err != nil {
		txn.Rollback()
		return
	}
	if err := txn.Create(&TxnUser{Name: "bot2", Location: "m"}).Error; err != nil {
		txn.Rollback()
		return
	}
	txn.Commit()
	fmt.Println("All was ok")
}
