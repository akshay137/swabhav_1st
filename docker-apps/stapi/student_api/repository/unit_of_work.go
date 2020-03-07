package repository

import (
	"github.com/jinzhu/gorm"
)

// UnitOfWork defines a transaction in database
type UnitOfWork struct {
	DB       *gorm.DB
	readOnly bool
	commited bool
}

// NewUnitOfWork returns a new UnitOfWork
func NewUnitOfWork(db *gorm.DB, readOnly bool) *UnitOfWork {
	if readOnly {
		return &UnitOfWork{
			DB:       db.New(),
			readOnly: true,
			commited: false,
		}
	}
	return &UnitOfWork{
		DB:       db.New().Begin(),
		readOnly: false,
		commited: false,
	}
}

// Complete rollbacks transaction
func (uow *UnitOfWork) Complete() {
	if !uow.readOnly && !uow.commited {
		uow.DB.Rollback()
	}
}

// Commit commits the changes to database
func (uow *UnitOfWork) Commit() {
	if !uow.commited && !uow.readOnly {
		uow.DB.Commit()
	}
	uow.commited = true
}
