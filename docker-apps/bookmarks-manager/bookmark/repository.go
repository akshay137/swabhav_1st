package bookmark

import (
	uuid "github.com/satori/go.uuid"

	"github.com/akshay137/bookmarksmanager/repository"
)

// Repository bookmark repository extends stdrepository functionalities
type Repository struct {
	repository.StdRepository
}

// NewBookmarkRepository returns new bookmark repository
func NewBookmarkRepository() *Repository {
	return &Repository{
		StdRepository: *repository.NewStdRepository(),
	}
}

// GetAllByUser returns all entities for given user
func (br *Repository) GetAllByUser(uow *repository.UnitOfWork, out interface{},
	id uuid.UUID, preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.Find(out, "user_id=?", id).Error
}

// GetByID returns entity for current user by matching entities id
func (br *Repository) GetByID(uow *repository.UnitOfWork, out interface{},
	uid uuid.UUID, bid uuid.UUID, preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.First(out, "id=? AND user_id=?", bid, uid).Error
}

// GetAllByCategory returns all bookmarks under a cateogry for given user
func (br *Repository) GetAllByCategory(uow *repository.UnitOfWork, out interface{},
	uid uuid.UUID, cid uuid.UUID, preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.Find(out, "user_id=? AND category=?", uid, cid).Error
}

// GetRecent returns entities modified or created recently
func (br *Repository) GetRecent(uow *repository.UnitOfWork, out interface{},
	uid uuid.UUID, limit int, preloads []string) error {
	db := uow.DB
	for _, association := range preloads {
		db = db.Preload(association)
	}
	return db.Order("updateOn desc").Limit(limit).Find(out).Error
}
