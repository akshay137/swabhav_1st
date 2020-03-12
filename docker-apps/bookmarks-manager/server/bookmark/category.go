package bookmark

import (
	uuid "github.com/satori/go.uuid"

	"github.com/akshay137/bookmarksmanager/repository"
)

// Category bookmark category
type Category struct {
	repository.BaseModel
	Name   string    `gorm:"type:varchar(50);" json:"name"`
	UserID uuid.UUID `gorm:"type:varchar(36);" json:"-"`
}

// NewCategory returns a new category instance
func NewCategory(name string, user uuid.UUID) *Category {
	ID := uuid.NewV4()
	return &Category{
		BaseModel: repository.BaseModel{
			ID: ID,
		},
		Name:   name,
		UserID: user,
	}
}
