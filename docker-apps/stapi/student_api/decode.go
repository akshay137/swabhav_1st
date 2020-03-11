package main

import (
	"fmt"

	"github.com/akshay137/student_api/student"
	"github.com/akshay137/student_api/utils"
)

func main() {
	// st := student.NewStudent("test", "a@a", "1990-01-01", 0, 12, false)
	// err := json.NewEncoder(os.Stdout).Encode(st)
	// fmt.Println(err)
	jsonData := `{"id":"46a51677-44c9-496f-b23b-b429b8baefd5","rollNo":0,"name":"test","email":"a@a","age":12,"date":"1990-01-01","isMale":false,"tst":{"id":"testing"}}`
	st := &student.Student{}
	utils.DecodePartialJSON([]byte(jsonData), st)
	fmt.Println(st.Name, st.Tst.ID)
}
