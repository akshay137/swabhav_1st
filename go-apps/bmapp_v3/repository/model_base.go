package repository

import (
	"time"

	uuid "github.com/satori/go.uuid"
)

// ModelBase contains common fields for entities
type ModelBase struct {
	ID        uuid.UUID
	CreatedAt time.Time  `gorm:"column:createdOn"`
	UpdateAt  time.Time  `gorm:"column:modifiedOn"`
	DeletedAt *time.Time `gorm:"column:deletedOn"`
}
