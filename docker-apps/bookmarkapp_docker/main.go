package main

import (
	"fmt"

	"github.com/akshay137/bookmarkapp/auth"
	"github.com/akshay137/bookmarkapp/bookmark"
	"github.com/akshay137/bookmarkapp/database"
)

func main() {
	fmt.Println("Welcome to BookMarksStore app")

	db, err := database.GetConnection("root",
		"root", "tcp(127.0.0.1:3306)", "swabhav")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	// defer db.Close()
	defer database.CloseConnection(db)

	authsvc := auth.NewAService(db)
	bmsvc := bookmark.NewBMService(db)

	for true {
		uid := showLogin(authsvc)
		if uid == 0 {
			return
		}
		fmt.Println("UserID", uid)
		showBookMarkMenu(uid, bmsvc)
	}
}
