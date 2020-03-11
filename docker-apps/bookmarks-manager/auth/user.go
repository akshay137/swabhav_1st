package auth

import (
	"crypto/sha512"
	"encoding/base64"

	"github.com/akshay137/bookmarksmanager/repository"

	uuid "github.com/satori/go.uuid"
)

// User ...
type User struct {
	repository.BaseModel
	Name     string `gorm:"type:varchar(50);" json:"name"`
	Email    string `sql:"index" gorm:"type:varchar(100);unique_index" json:"email"`
	Password string `gorm:"type:varchar(88);" json:"-"`
}

// NewUser returns new user
func NewUser(name, email, password string) *User {
	id := uuid.NewV4()
	passHash := sha512.Sum512([]byte(password))
	passEnc := base64.StdEncoding.EncodeToString(passHash[:])
	// log.Println("hash", passHash)
	// log.Println("b64", "len", len(passEnc), "enc", passEnc)
	return &User{
		BaseModel: repository.BaseModel{
			ID: id,
		},
		Name:     name,
		Email:    email,
		Password: passEnc,
	}
}
