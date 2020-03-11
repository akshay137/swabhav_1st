package auth

import (
	"crypto/sha512"
	"encoding/base64"
	"fmt"
	"strings"

	"github.com/jinzhu/gorm"
	uuid "github.com/satori/go.uuid"

	"github.com/akshay137/bookmarksmanager/repository"
)

// Service authorization service
type Service struct {
	db   *gorm.DB
	repo *repository.StdRepository
}

// NewAuthService returns new authorization service
func NewAuthService(db *gorm.DB) *Service {
	db.AutoMigrate(&User{})
	return &Service{
		db:   db,
		repo: repository.NewStdRepository(),
	}
}

// // NewUser returns new user
// func NewUser(name, email, password string) *User {
// 	passHash := sha512.Sum512([]byte(password))
// 	return newUser(name, email, string(passHash[:]))
// }

// AddUser adds new user
func (as *Service) AddUser(name, email, password string) (*User, error) {
	user := NewUser(name, email, password)
	uow := repository.NewUnitOfWork(as.db, false)
	err := as.repo.Add(uow, user)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return user, nil
}

// GetUserById returns a user matching its id
func (as *Service) GetUserById(id string) (*User, error) {
	ID, err := uuid.FromString(id)
	if err != nil {
		return nil, err
	}
	user := &User{}
	uow := repository.NewUnitOfWork(as.db, true)
	err = as.repo.Get(uow, user, ID, nil)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return user, nil
}

// GetUserByEmail return user by email
func (as *Service) GetUserByEmail(email string) (*User, error) {
	user := &User{}
	uow := repository.NewUnitOfWork(as.db, true)
	err := as.repo.GetByField(uow, user, "email", email, nil)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return user, nil
}

// AuthenticateUser checks if user creditials are valid
func (as *Service) AuthenticateUser(user *User, email, password string) error {
	hash := sha512.Sum512([]byte(password))
	enc := base64.StdEncoding.EncodeToString(hash[:])
	if strings.Compare(user.Password, enc) != 0 {
		return fmt.Errorf("Incorrect password")
	}
	return nil
}
