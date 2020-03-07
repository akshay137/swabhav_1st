package student

import (
	"github.com/akshay137/student_api/repository"
	"github.com/jinzhu/gorm"
	uuid "github.com/satori/go.uuid"
)

// Service student service
type Service struct {
	students map[string]*Student
	repo     *Repository
	db       *gorm.DB
}

// NewStudentService return new student serivce
func NewStudentService(db *gorm.DB) *Service {
	svc := &Service{
		students: make(map[string]*Student),
		repo:     NewStudentRepository(),
		db:       db,
	}
	db.AutoMigrate(&Student{})
	fillData(svc)
	return svc
}

// GetAll returns all students
func (s *Service) GetAll() []*Student {
	list := []*Student{}
	uw := repository.NewUnitOfWork(s.db, true)
	err := s.repo.GetAll(uw, &list, nil)
	if err != nil {
		uw.Complete()
		return nil
	}
	uw.Commit()
	return list
}

// GetById returns single student matching its id or nil
func (s *Service) GetById(id string) *Student {
	ID, err := uuid.FromString(id)
	if err != nil {
		return nil
	}
	uw := repository.NewUnitOfWork(s.db, true)
	st := &Student{}
	err = s.repo.Get(uw, st, ID, nil)
	if err != nil {
		uw.Complete()
		return nil
	}
	uw.Commit()
	// st := s.students[id]
	return st
}

func (s *Service) Add(name, email, date string, roll, age int, isMale bool) string {
	st := NewStudent(name, email, date, roll, age, isMale)
	uw := repository.NewUnitOfWork(s.db, false)
	err := s.repo.Add(uw, st)
	if err != nil {
		uw.Complete()
		return ""
	}
	uw.Commit()
	return st.ID.String()
}

func (s *Service) Update(id string, st *Student) bool {
	uw := repository.NewUnitOfWork(s.db, false)
	err := s.repo.Update(uw, st)
	if err != nil {
		uw.Complete()
		return false
	}
	uw.Commit()
	return true
}

func (s *Service) Delete(id string) bool {
	uw := repository.NewUnitOfWork(s.db, false)
	st := s.GetById(id)
	if st == nil {
		uw.Complete()
		return false
	}
	err := s.repo.Delete(uw, st)
	if err != nil {
		uw.Complete()
		return false
	}
	uw.Commit()
	return true
}

func fillData(svc *Service) {
	// uw := repository.NewUnitOfWork(svc.db, false)
	// st := NewStudent("hello", "h@b.com", "1990-02-20", 1, 20, true)
	// err := svc.repo.Add(uw, st)
	// if err != nil {
	// 	uw.Complete()
	// 	return
	// }
	// st1 := NewStudent("bye", "b@h.com", "1989-02-20", 2, 21, false)
	// err = svc.repo.Add(uw, st1)
	// if err != nil {
	// 	uw.Complete()
	// 	return
	// }
	// st2 := NewStudent("bye", "b@h.com", "1989-02-20", 3, 21, false)
	// err = svc.repo.Add(uw, st2)
	// if err != nil {
	// 	uw.Complete()
	// 	return
	// }
	// uw.Commit()
}
