package bookmark

import (
	"github.com/jinzhu/gorm"
)

// BookMark bookmark stores descriptive name and url
type BookMark struct {
	gorm.Model
	Name   string `gorm:"type:varchar(50)"`
	URL    string `gorm:"type:varchar(500)"`
	UserID uint
}

func newBookMark(name, url string, userID uint) *BookMark {
	return &BookMark{
		Name:   name,
		URL:    url,
		UserID: userID,
	}
}
