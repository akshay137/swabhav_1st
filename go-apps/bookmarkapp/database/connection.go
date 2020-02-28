package database

import (
	"fmt"

	"github.com/jinzhu/gorm"
	// mysql driver
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

// GetConnection returns a connection to database
// for user passed in parameter
func GetConnection(user, pass, url, databse string) (*gorm.DB, error) {
	db, err := gorm.Open("mysql",
		fmt.Sprintf("%s:%s@%s/%s?parseTime=true",
			user, pass, url, databse))
	if err != nil {
		return nil, err
	}
	return db, nil
}

// CloseConnection terminates the database connection
func CloseConnection(db *gorm.DB) {
	db.Close()
}
