package repository

import (
	"time"

	uuid "github.com/satori/go.uuid"
)

// ModelBase provides base columns for all entities
type ModelBase struct {
	ID        uuid.UUID  `gorm:"primary_key" json:"id,omitempty"`
	CreatedAt time.Time  `gorm:"column:createdAt" json:"-"`
	UpdatedAt time.Time  `gorm:"column:updatedAt"  json:"-"`
	DeletedAt *time.Time `sql:"index" gorm:"column:deletedAt"  json:"-"`
}
