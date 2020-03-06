package bookmark

import (
	"github.com/akshay137/bookmarkapp/repository"
	"github.com/jinzhu/gorm"
	uuid "github.com/satori/go.uuid"
)

type Repository struct{}

func NewBookmarkRepository(db *gorm.DB) *Repository {
	db.AutoMigrate(&Bookmark{})
	return &Repository{}
}

func (r *Repository) Get(uow *repository.UnitOfWork, out interface{}, id uuid.UUID, preloadAssociations []string) error {
	db := uow.DB
	for _, association := range preloadAssociations {
		db = db.Preload(association)
	}
	return db.Find(out, "id=?", id).Error
}

func (r *Repository) GetAll(uow *repository.UnitOfWork, out interface{}, preloadAssociations []string) error {
	db := uow.DB
	for _, association := range preloadAssociations {
		db = db.Preload(association)
	}
	return db.Find(out).Error
}

func (r *Repository) Add(uow *repository.UnitOfWork, out interface{}) error {
	db := uow.DB
	return db.Create(out).Error
}

func (r *Repository) Update(uow *repository.UnitOfWork, out interface{}) error {
	db := uow.DB
	return db.Model(out).Update(out).Error
}

func (r *Repository) Delete(uow *repository.UnitOfWork, out interface{}) error {
	db := uow.DB
	return db.Delete(out).Error
}
