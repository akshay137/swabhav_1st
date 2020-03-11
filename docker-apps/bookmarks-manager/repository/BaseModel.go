package repository

import (
	"time"

	uuid "github.com/satori/go.uuid"
)

// BaseModel defines base model for all business models
type BaseModel struct {
	ID        uuid.UUID  `gorm:"type:varchar(36);primary_key;column:id" json:"id"`
	CreatedAt time.Time  `gorm:"column:createdOn" json:"-"`
	UpdatedAt time.Time  `gorm:"column:updateOn" json:"-"`
	DeletedAt *time.Time `sql:"index" gorm:"column:deletedOn" json:"-"`
}
