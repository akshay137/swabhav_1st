package student

// Service student service
type Service struct {
	students map[string]*Student
}

// NewStudentService return new student serivce
func NewStudentService() *Service {
	svc := &Service{
		students: make(map[string]*Student),
	}
	fillData(svc)
	return svc
}

// GetAll returns all students
func (s *Service) GetAll() []*Student {
	list := make([]*Student, len(s.students))
	i := 0
	for _, v := range s.students {
		list[i] = v
		i++
	}
	return list
}

// GetById returns single student matching its id or nil
func (s *Service) GetById(id string) *Student {
	st := s.students[id]
	return st
}

func (s *Service) Add(name, email, date string, roll, age int, isMale bool) string {
	st := NewStudent(name, email, date, roll, age, isMale)
	s.students[st.ID] = st
	return st.ID
}

func (s *Service) Update(id string, st *Student) bool {
	student := s.students[id]
	if student == nil {
		return false
	}
	s.students[id] = st
	return true
}

func (s *Service) Delete(id string) bool {
	st := s.students[id]
	if st == nil {
		return false
	}
	delete(s.students, id)
	return true
}

func fillData(svc *Service) {
	st := NewStudent("hello", "h@b.com", "1990-02-20", 1, 20, true)
	svc.students[st.ID] = st
	st1 := NewStudent("bye", "b@h.com", "1989-02-20", 2, 21, false)
	svc.students[st1.ID] = st1
	st2 := NewStudent("bye", "b@h.com", "1989-02-20", 3, 21, false)
	svc.students[st2.ID] = st2
	// fmt.Println(svc.students)
}
