package repository

import (
	"github.com/jinzhu/gorm"
)

// UnitOfWork contains fields for handling transactions
type UnitOfWork struct {
	db       *gorm.DB
	readOnly bool
	commited bool
}

// NewUnitOfWork return new UnitOfWork
func NewUnitOfWork(db *gorm.DB, readOnly bool) *UnitOfWork {
	if readOnly {
		return &UnitOfWork{
			db:       db.New(),
			commited: false,
			readOnly: true,
		}
	}
	return &UnitOfWork{
		db:       db.New().Begin(),
		commited: false,
		readOnly: false,
	}
}

// Complete rollbacks transaction if not commited
func (uow *UnitOfWork) Complete() {
	if !uow.commited && !uow.readOnly {
		uow.db.Rollback()
	}
}

// Commit commits transaction if readOnly is false
func (uow *UnitOfWork) Commit() {
	if !uow.readOnly {
		uow.db.Commit()
	}
}
