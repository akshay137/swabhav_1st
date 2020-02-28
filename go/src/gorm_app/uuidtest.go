package main

import (
	"fmt"

	uuid "github.com/satori/go.uuid"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

type UserUUID struct {
	ID string `gorm:"type:varchar(36)"`
}

func main() {
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav?parseTime=true")
	if err != nil {
		fmt.Println(err.Error())
	}
	defer db.Close()
	db.AutoMigrate(&UserUUID{})

	userid, err := uuid.NewV4()
	if err != nil {
		fmt.Println(err.Error())
	}
	fmt.Println("userid", userid)

	// for i := 0; i < 5; i++ {
	// id, err := uuid.NewV4()
	// 	if err != nil {
	// 		fmt.Println("Error", err.Error())
	// 		continue
	// 	}
	// 	db.Save(&UserUUID{ID: id.String()})
	// }

	users := []UserUUID{}
	db.Find(&users)
	for _, u := range users {
		fmt.Println("ID", u.ID)
	}

	user := &UserUUID{}
	db.First(user, "id=?", users[0].ID)
	fmt.Println("user", user)

}
