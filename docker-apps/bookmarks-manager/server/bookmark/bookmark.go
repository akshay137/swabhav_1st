package bookmark

import (
	uuid "github.com/satori/go.uuid"

	"github.com/akshay137/bookmarksmanager/repository"
)

// Bookmark stores bookmark info
type Bookmark struct {
	repository.BaseModel
	Name     string    `gorm:"type:varchar(100);" json:"name"`
	URL      string    `gorm:"type:varchar(512);" json:"url"`
	Category uuid.UUID `gorm:"type:varchar(36);" json:"category_id"`
	UserID   uuid.UUID `gorm:"type:varchar(36);" json:"-"`
}

// NewBookmark returns new bookmark
func NewBookmark(name, url string, cat, user uuid.UUID) *Bookmark {
	ID := uuid.NewV4()
	return &Bookmark{
		BaseModel: repository.BaseModel{
			ID: ID,
		},
		Name:     name,
		URL:      url,
		Category: cat,
		UserID:   user,
	}
}
