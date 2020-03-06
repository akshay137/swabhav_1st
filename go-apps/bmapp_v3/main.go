package main

import (
	"fmt"
	"time"

	"github.com/akshay137/bookmarkapp/bookmark"
	"github.com/akshay137/bookmarkapp/repository"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

func main() {
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav?parseTime=true")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	defer db.Close()
	br := bookmark.NewBookmarkRepository(db)
	bms := []bookmark.Bookmark{}
	uw := repository.NewUnitOfWork(db, false)
	br.Add(uw, bookmark.NewBookmark("0", "hello", "http://a.com"))
	br.GetAll(uw, &bms, nil)
	fmt.Println("Got", len(bms), "bookmarks")
	bm := bms[0]
	bm.Name = "new hello"
	time.Sleep(1 * time.Second)
	br.Update(uw, bm)
	br.Delete(uw, bms[0])
	fmt.Println(bms)
	uw.Commit()
}
