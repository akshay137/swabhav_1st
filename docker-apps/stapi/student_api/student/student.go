package student

import (
	"fmt"

	"github.com/akshay137/student_api/repository"
	uuid "github.com/satori/go.uuid"
)

type Student struct {
	repository.ModelBase
	// ID     string `json:"id"`
	RollNo int    `json:"rollNo"`
	Name   string `json:"name"`
	Email  string `json:"email"`
	Age    int    `json:"age"`
	Date   string `json:"date"`
	IsMale bool   `json:"isMale"`
}

// NewStudent returns new student
func NewStudent(name, email, date string, roll, age int, isMale bool) *Student {
	id := uuid.NewV4()
	return &Student{
		ModelBase: repository.ModelBase{
			ID: id,
		},
		// ID:     id.String(),
		RollNo: roll,
		Name:   name,
		Email:  email,
		Age:    age,
		Date:   date,
		IsMale: isMale,
	}
}

func (s *Student) String() string {
	return fmt.Sprintf("%s: %s", s.ID, s.Name)
}
