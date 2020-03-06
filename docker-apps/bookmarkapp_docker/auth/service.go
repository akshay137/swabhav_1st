package auth

import (
	"fmt"

	"github.com/jinzhu/gorm"
)

// AService service for authentication
type AService struct {
	db *gorm.DB
}

// NewAService returns new instance of auth service
func NewAService(db *gorm.DB) *AService {
	as := &AService{
		db: db,
	}
	as.db.AutoMigrate(&User{})
	return as
}

// AddNewUser adds new user to database
func (as *AService) AddNewUser(name, password string) (*User, error) {
	user := newUser(name, password)
	as.db.Save(user)
	if as.db.Error != nil {
		return nil, as.db.Error
	}
	return user, nil
}

// Login login user or return error
func (as *AService) Login(name, password string) (uint, error) {
	var user User
	as.db.First(&user, "name=? AND password=?",
		name, password)
	if user.ID == 0 {
		return 0, fmt.Errorf("NO such user")
	}
	return user.ID, nil
}

// Register login user or return error
func (as *AService) Register(name, password string) (uint, error) {
	user := &User{}
	as.db.First(user, "name=?", name)
	if user.ID != 0 {
		return 0, fmt.Errorf("Username already taken")
	}
	user = newUser(name, password)
	as.db.Save(user)
	if as.db.Error != nil {
		return 0, as.db.Error
	}
	return user.ID, nil
}
