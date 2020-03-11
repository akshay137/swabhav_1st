package bookmark

import (
	"fmt"

	"github.com/jinzhu/gorm"
	uuid "github.com/satori/go.uuid"

	"github.com/akshay137/bookmarksmanager/repository"
)

// Service bookmark service
type Service struct {
	db   *gorm.DB
	repo *Repository
}

// NewBookmarkService returns new bookmark service instance
func NewBookmarkService(db *gorm.DB) *Service {
	db.AutoMigrate(&Bookmark{})
	db.Model(&Bookmark{}).AddForeignKey("user_id", "users(id)",
		"CASCADE", "CASCADE")
	// db.Model(&Bookmark{}).AddForeignKey("category", "categorys(id)",
	// 	"CASCADE", "CASCADE")
	return &Service{
		db:   db,
		repo: NewBookmarkRepository(),
	}
}

// GetAll returns all bookmarks for given user
func (bs *Service) GetAll(uid string) ([]Bookmark, error) {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return nil, fmt.Errorf("Invalid user id")
	}
	uow := repository.NewUnitOfWork(bs.db, true)
	bms := []Bookmark{}
	err = bs.repo.GetAllByUser(uow, &bms, UID, nil)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return bms, nil
}

// Get returns single bookmark for given user and bookmark id
func (bs *Service) Get(uid string, bid string) (*Bookmark, error) {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return nil, fmt.Errorf("Invalid user id")
	}
	BID, err := uuid.FromString(bid)
	if err != nil {
		return nil, fmt.Errorf("Invalid bookmark id")
	}
	bm := &Bookmark{}
	uow := repository.NewUnitOfWork(bs.db, true)
	err = bs.repo.GetByID(uow, bm, UID, BID, nil)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return bm, nil
}

// Add adds new bookmark to database
func (bs *Service) Add(uid, cid, name, url string) (*Bookmark, error) {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return nil, fmt.Errorf("Invalid user id")
	}
	CID, err := uuid.FromString(cid)
	if err != nil {
		return nil, fmt.Errorf("Invalid category id")
	}
	bm := NewBookmark(name, url, CID, UID)
	uow := repository.NewUnitOfWork(bs.db, false)
	err = bs.repo.Add(uow, bm)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return bm, nil
}

// Delete deletes the bookmark by id
func (bs *Service) Delete(uid, bid string) error {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return err
	}
	BID, err := uuid.FromString(bid)
	if err != nil {
		return err
	}
	uow := repository.NewUnitOfWork(bs.db, false)
	bm := &Bookmark{}
	err = bs.repo.GetByID(uow, bm, UID, BID, nil)
	if err != nil {
		uow.Complete()
		return err
	}
	err = bs.repo.Delete(uow, bm)
	if err != nil {
		uow.Complete()
		return err
	}
	uow.Commit()
	return nil
}

// GetRecent returns recent bookmarks upto limit
func (bs *Service) GetRecent(uid string, limit int) ([]Bookmark, error) {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return nil, fmt.Errorf("Invalid user id")
	}
	uow := repository.NewUnitOfWork(bs.db, true)
	bms := []Bookmark{}
	err = bs.repo.GetRecent(uow, &bms, UID, limit, nil)
	if err != nil {
		return nil, err
	}
	return bms, nil
}

// Update updates bookmark
func (bs *Service) Update(bm *Bookmark) error {
	uow := repository.NewUnitOfWork(bs.db, false)
	err := bs.repo.Update(uow, bm)
	if err != nil {
		uow.Complete()
		return err
	}
	uow.Commit()
	return nil
}
