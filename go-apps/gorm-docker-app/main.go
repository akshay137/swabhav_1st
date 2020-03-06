package main

import (
	"fmt"
	"os"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

func main() {
	durl := fmt.Sprintf("%s:%s@tcp(%s:3306)/%s?parseTime=true",
		// "root", "root", "swabhav",
		os.Getenv("MYSQL_USER"),
		os.Getenv("MYSQL_PASSWORD"),
		os.Getenv("MYSQL_HOST"),
		os.Getenv("MYSQL_DATABASE"),
	)
	fmt.Println("link", durl)
	db, err := gorm.Open("mysql", durl)
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	fmt.Println("Connected to", db.Dialect().GetName())
	db.Close()
}
