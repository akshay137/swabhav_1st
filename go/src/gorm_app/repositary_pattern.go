package main

import (
	"fmt"
	"time"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
	uuid "github.com/satori/go.uuid"
)

type UnitOfWork struct {
	DB       *gorm.DB
	commited bool
	readOnly bool
}

func NewUnitOfWork(db *gorm.DB, readOnly bool) *UnitOfWork {
	if readOnly {
		return &UnitOfWork{DB: db.New(), commited: false, readOnly: true}
	}
	return &UnitOfWork{DB: db.New().Begin(), commited: false, readOnly: false}
}

func (uow *UnitOfWork) Complete() {
	if !uow.commited && !uow.readOnly {
		uow.DB.Rollback()
	}
}

func (uow *UnitOfWork) Commit() {
	if !uow.readOnly {
		uow.DB.Commit()
	}
	uow.commited = true
}

// Repository pattern start

type Repository interface {
	Get(uow *UnitOfWork, out interface{}, id uuid.UUID, preloadAssociations []string) error
	GetAll(uow *UnitOfWork, out interface{}, preloadAssociations []string) error
	Add(uow *UnitOfWork, out interface{}) error
	Update(uow *UnitOfWork, out interface{}) error
	Delete(uow *UnitOfWork, out interface{}) error
}

type GormRepository struct{}

func NewRepository() Repository {
	return &GormRepository{}
}

func (repo *GormRepository) Get(uow *UnitOfWork, out interface{}, id uuid.UUID, preloadAssociations []string) error {
	db := uow.DB
	for _, association := range preloadAssociations {
		db = db.Preload(association)
	}
	return db.First(out, "id=?", id).Error
}

func (repo *GormRepository) GetAll(uow *UnitOfWork, out interface{}, preloadAssociations []string) error {
	db := uow.DB
	for _, association := range preloadAssociations {
		db = db.Preload(association)
	}
	return db.Find(out).Error
}

func (repo *GormRepository) Add(uow *UnitOfWork, entity interface{}) error {
	return uow.DB.Create(entity).Error
}

func (repo *GormRepository) Update(uow *UnitOfWork, entity interface{}) error {
	return uow.DB.Model(entity).Update(entity).Error
}

func (repo *GormRepository) Delete(uow *UnitOfWork, entity interface{}) error {
	return uow.DB.Delete(entity).Error
}

// Repository pattern end

type ModelBase struct {
	ID        uuid.UUID  `gorm:"type:varchar(36);primary_key`
	CreatedAt time.Time  `gorm:"column:createdOn;default:CURRENT_TIMESTAMP"`
	UpdateAt  time.Time  `gorm:"column:modifiedOn;default:CURRENT_TIMESTAMP"`
	DeletedAt *time.Time `sql:"index" gorm:"deletedOn"`
}

type RepoUser struct {
	ModelBase
	Name string `gorm:"type:varchar(30);unique"`
}

func main() {
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav?parseTime=true")
	if err != nil {
		fmt.Println(err.Error())
		return
	}
	defer db.Close()

	db.AutoMigrate(&RepoUser{})

	uw := NewUnitOfWork(db, false)
	gr := NewRepository()

	id, _ := uuid.NewV4()
	user := &RepoUser{Name: "hello"}
	user.ID = id
	err = gr.Add(uw, user)
	if err != nil {
		uw.Complete()
		goto end_of_transaction
	}
	id, _ = uuid.NewV4()
	user = &RepoUser{Name: "Hello2"}
	user.ID = id
	err = gr.Add(uw, user)
	if err != nil {
		uw.Complete()
		goto end_of_transaction
	}
	uw.Commit()

end_of_transaction:

	users := []RepoUser{}
	uw = NewUnitOfWork(db, true)
	gr.GetAll(uw, &users, nil)
	for _, user := range users {
		fmt.Println("ID", user.ID)
		fmt.Println("Name", user.Name)
	}
	uw.Commit()

	uw = NewUnitOfWork(db, true)
	user = &RepoUser{}
	id, _ = uuid.FromString("a0658cf1-0b5b-41c9-a22a-99927554d484")
	gr.Get(uw, user, id, nil)
	fmt.Println("User")
	fmt.Println("ID", user.ID)
	fmt.Println("Name", user.Name)

}
