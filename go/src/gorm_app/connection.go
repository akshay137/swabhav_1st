package main

import (
	"fmt"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

func main() {
	db, err := gorm.Open("mysql", "root:root@/swabhav")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	defer db.Close()
	fmt.Printf("type of db connection: %T\n", db)
	d := db.Dialect()
	fmt.Println("Current Database", d.CurrentDatabase())
	fmt.Println("dialect name", d.GetName())
	fmt.Println("has table DEPT", d.HasTable("DEPT"))
}
