package bookmark

import (
	"fmt"

	"github.com/jinzhu/gorm"
	uuid "github.com/satori/go.uuid"

	"github.com/akshay137/bookmarksmanager/repository"
)

//
type ServiceCategory struct {
	db   *gorm.DB
	repo *Repository
}

func NewCategoryService(db *gorm.DB) *ServiceCategory {
	return &ServiceCategory{
		db:   db,
		repo: NewBookmarkRepository(),
	}
}

func (cs *ServiceCategory) Add(name, uid string) (*Category, error) {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return nil, err
	}
	c := NewCategory(name, UID)
	uow := repository.NewUnitOfWork(cs.db, false)
	err = cs.repo.Add(uow, c)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return c, nil
}

func (cs *ServiceCategory) GetAll(uid string) ([]Category, error) {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return nil, err
	}
	c := []Category{}
	uow := repository.NewUnitOfWork(cs.db, true)
	err = cs.repo.GetAllByUser(uow, &c, UID, nil)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return c, nil
}

func (cs *ServiceCategory) GetByID(uid string, cid string) (*Category, error) {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return nil, fmt.Errorf("Invalid user id")
	}
	CID, err := uuid.FromString(cid)
	if err != nil {
		return nil, fmt.Errorf("Invalid category id")
	}
	c := &Category{}
	uow := repository.NewUnitOfWork(cs.db, true)
	err = cs.repo.GetByID(uow, c, UID, CID, nil)
	if err != nil {
		uow.Complete()
		return nil, err
	}
	uow.Commit()
	return c, nil
}

func (cs *ServiceCategory) Delete(uid, cid string) error {
	UID, err := uuid.FromString(uid)
	if err != nil {
		return err
	}
	CID, err := uuid.FromString(cid)
	if err != nil {
		return err
	}
	uow := repository.NewUnitOfWork(cs.db, false)
	cat := &Category{}
	err = cs.repo.GetByID(uow, cat, UID, CID, nil)
	if err != nil {
		uow.Complete()
		return err
	}
	err = cs.repo.Delete(uow, cat)
	if err != nil {
		uow.Complete()
		return err
	}
	uow.Commit()
	return nil
}

func (cs *ServiceCategory) Update(cat *Category) error {
	uow := repository.NewUnitOfWork(cs.db, false)
	err := cs.repo.Update(uow, cat)
	if err != nil {
		uow.Complete()
		return err
	}
	uow.Commit()
	return nil
}
