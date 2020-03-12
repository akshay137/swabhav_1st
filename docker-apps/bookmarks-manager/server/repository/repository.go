package repository

import (
	"fmt"

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

// StdRepository standard repository
type StdRepository struct{}

// NewStdRepository returns new repositry for entity
func NewStdRepository() *StdRepository {
	return &StdRepository{}
}

// Get retrives first entity who mathces the given id
func (r *StdRepository) Get(uow *UnitOfWork, out interface{},
	id uuid.UUID, preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.First(out, "id=?", id.String()).Error
}

// GetByField returns first entity that mathces the condition
func (r *StdRepository) GetByField(uow *UnitOfWork, out interface{},
	fieldName string, field interface{}, preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.First(out, fmt.Sprintf("%s=?", fieldName), field).Error
}

// GetAll retrieves all entities from database
func (r *StdRepository) GetAll(uow *UnitOfWork, out interface{},
	preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.Find(out).Error
}

// GetAllForField retrives all entities matching the conditions
func (r *StdRepository) GetAllForField(uow *UnitOfWork, out interface{},
	fieldName string, condition interface{}, preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.Find(out, fmt.Sprintf("%s=?", fieldName), condition).Error
}

// Add adds new entity data to database
func (r *StdRepository) Add(uow *UnitOfWork, out interface{}) error {
	return uow.DB.Create(out).Error
}

// Update updates existing entity's data
func (r *StdRepository) Update(uow *UnitOfWork, out interface{}) error {
	return uow.DB.Model(out).Save(out).Error
}

// Delete removes entity's data from database (soft delete)
func (r *StdRepository) Delete(uow *UnitOfWork, out interface{}) error {
	return uow.DB.Delete(out).Error
}
