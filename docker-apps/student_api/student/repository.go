package student

import (
	"github.com/akshay137/student_api/repository"
	uuid "github.com/satori/go.uuid"
)

// Repository student repository
type Repository struct{}

// NewStudentRepository returns new repositry for student
func NewStudentRepository() *Repository {
	return &Repository{}
}

// Get retrives first student who mathces the given id
func (r *Repository) Get(uow *repository.UnitOfWork, out interface{},
	id uuid.UUID, preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.First(out, "id=?", id.String()).Error
}

// GetAll retrieves all students from database
func (r *Repository) GetAll(uow *repository.UnitOfWork, out interface{},
	preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.Find(out).Error
}

// Add adds new student data to database
func (r *Repository) Add(uow *repository.UnitOfWork, out interface{}) error {
	return uow.DB.Create(out).Error
}

// Update updates existing student's data
func (r *Repository) Update(uow *repository.UnitOfWork, out interface{}) error {
	return uow.DB.Model(out).Save(out).Error
}

// Delete removes student's data from database (soft delete)
func (r *Repository) Delete(uow *repository.UnitOfWork, out interface{}) error {
	return uow.DB.Delete(out).Error
}
