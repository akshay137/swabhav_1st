package repository

import (
	uuid "github.com/satori/go.uuid"
)

// Repository provides standard methods for accessing database
type Repository interface {
	Get(uow *UnitOfWork, out interface{}, id uuid.UUID, preloads []string) error
	GetAll(uow *UnitOfWork, out interface{}, preloads []string) error
	Add(uow *UnitOfWork, out interface{}) error
	Update(uow *UnitOfWork, out interface{}) error
	Delete(uow *UnitOfWork, out interface{}) error
}
