package repository

import (
	"github.com/jinzhu/gorm"
)

// UnitOfWork defines a single transaction in database
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

// Complete rollbacks a transaction if transaction is not readonly
// and not yet commited
func (uow *UnitOfWork) Complete() {
	if !uow.readOnly && !uow.commited {
		uow.DB.Rollback()
	}
}

// Commit commits the transaction if not already commited or
// transaction is not readonly
func (uow *UnitOfWork) Commit() {
	if !uow.readOnly && !uow.commited {
		uow.DB.Commit()
		uow.commited = true
	}
}
