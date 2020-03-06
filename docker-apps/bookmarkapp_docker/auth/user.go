package auth

import (
	"github.com/jinzhu/gorm"
)

// User contains user information for authentication
type User struct {
	gorm.Model
	Name     string `gorm:"type:varchar(50);unique"`
	Password string `gorm:"type:varchar(512)"`
}

func newUser(name, password string) *User {
	return &User{
		Name:     name,
		Password: password,
	}
}
