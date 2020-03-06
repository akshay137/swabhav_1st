package repository

import (
	"time"

	uuid "github.com/satori/go.uuid"
)

// ModelBase contains common fields for entities
type ModelBase struct {
	ID        uuid.UUID  `gorm:"type:varchar(36);primary_key;"`
	CreatedAt time.Time  `gorm:"column:createdAt;"`
	UpdatedAt time.Time  `gorm:"column:updatedAt;"`
	DeletedAt *time.Time `sql:"index" gorm:"column:deletedAt"`
}
