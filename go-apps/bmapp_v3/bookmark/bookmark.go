package bookmark

import (
	"github.com/akshay137/bookmarkapp/repository"
	uuid "github.com/satori/go.uuid"
)

type Bookmark struct {
	repository.ModelBase
	Name   string `gorm:"type:varchar(50)"`
	URL    string `gorm:"type:varchar(500)"`
	UserId uuid.UUID
}

func NewBookmark(userid uuid.UUID, name, url string) *Bookmark {
	bm := &Bookmark{
		Name:   name,
		URL:    url,
		UserId: userid,
		ModelBase: repository.ModelBase{
			ID: uuid.NewV4(),
		},
	}
	return bm
}
