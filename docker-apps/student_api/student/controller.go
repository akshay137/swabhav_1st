package student

import (
	"encoding/json"
	"fmt"
	"io"
	"io/ioutil"
	"log"
	"net/http"
	"strconv"
	"strings"

	"github.com/akshay137/student_api/repository"
	"github.com/akshay137/student_api/utils"
	"github.com/gorilla/mux"
	"github.com/jinzhu/gorm"
	uuid "github.com/satori/go.uuid"
)

type Controller struct {
	svc *Service
}

func NewStudentController(db *gorm.DB) *Controller {
	return &Controller{
		svc: NewStudentService(db),
	}
}

func (c *Controller) RegisterRoutes(router *mux.Router) error {
	// router.StrictSlash(true)
	router.HandleFunc("/api/students/", c.getAll).Methods("GET")
	router.HandleFunc("/api/students/", c.add).Methods("POST")
	router.HandleFunc("/api/students/{id}", c.get).Methods("GET")
	router.HandleFunc("/api/students/{id}", c.update).Methods("PUT")
	router.HandleFunc("/api/students/{id}", c.delete).Methods("DELETE")
	return nil
}

func (c *Controller) get(w http.ResponseWriter, r *http.Request) {
	v := mux.Vars(r)
	id := v["id"]
	log.Println("get id:", id)
	st := c.svc.GetById(id)
	if st == nil {
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode([]*Student{st})
}

func (c *Controller) getAll(w http.ResponseWriter, r *http.Request) {
	log.Println("get")
	s := c.svc.GetAll()
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(s)
}

func (c *Controller) add(w http.ResponseWriter, r *http.Request) {
	log.Println("add")
	w.Header().Set("Content-Type", "application/json")
	if isRequestDataJson(r) {
		log.Println("Parsing json")
		b, err := ioutil.ReadAll(r.Body)
		if err != nil {
			w.WriteHeader(http.StatusInternalServerError)
			return
		}
		st := &Student{}
		err = utils.DecodePartialJSON(b, st)
		if err != nil {
			w.WriteHeader(http.StatusInternalServerError)
			return
		}
		fmt.Println("custom decode",
			st.Name, st.Email, st.Date, st.RollNo, st.Age, st.IsMale)
		id := c.svc.Add(st.Name, st.Email, st.Date, st.RollNo, st.Age, st.IsMale)
		json.NewEncoder(w).Encode(id)
	} else {
		log.Println("Parsing form")
		r.ParseForm()
		id := c.svc.Add(r.FormValue("name"), r.FormValue("email"), r.FormValue("date"),
			getInt(r.FormValue("rollNo")), getInt(r.FormValue("age")),
			getBool(r.FormValue("isMale")))
		json.NewEncoder(w).Encode(id)
	}
}

func (c *Controller) update(w http.ResponseWriter, r *http.Request) {
	id := mux.Vars(r)["id"]
	log.Println("update id:", id)
	// log.Println(r.Header["Content-Type"])
	ID, err := uuid.FromString(id)
	if err != nil {
		w.WriteHeader(http.StatusNotFound)
		return
	}
	if isRequestDataJson(r) {
		log.Println("Parsing json")
		st := &Student{
			ModelBase: repository.ModelBase{
				ID: ID,
			},
		}
		err := json.NewDecoder(r.Body).Decode(st)
		if err != nil {
			w.WriteHeader(http.StatusInternalServerError)
			return
		}
		if c.svc.Update(id, st) {
			w.WriteHeader(http.StatusOK)
		} else {
			w.WriteHeader(http.StatusBadRequest)
		}
	} else {
		log.Println("Parsing form")
		r.ParseForm()
		st := &Student{
			// ID:     id,
			ModelBase: repository.ModelBase{
				ID: ID,
			},
			Name:   r.FormValue("name"),
			Email:  r.FormValue("email"),
			Date:   r.FormValue("date"),
			RollNo: getInt(r.FormValue("rollNo")),
			Age:    getInt(r.FormValue("age")),
			IsMale: getBool(r.FormValue("isMale")),
		}
		if c.svc.Update(id, st) {
			w.WriteHeader(http.StatusOK)
		} else {
			w.WriteHeader(http.StatusBadRequest)
		}
	}
}

func (c *Controller) delete(w http.ResponseWriter, r *http.Request) {
	id := mux.Vars(r)["id"]
	log.Println("delete id:", id)
	if c.svc.Delete(id) {
		w.WriteHeader(http.StatusOK)
	} else {
		w.WriteHeader(http.StatusBadRequest)
	}
}

func getInt(str string) int {
	i, err := strconv.Atoi(str)
	if err != nil {
		return 0
	}
	if i < 0 {
		return 0
	}
	return i
}

func getBool(str string) bool {
	return strings.Compare(strings.ToLower(str), "true") == 0
}

func isRequestDataJson(r *http.Request) bool {
	return strings.Compare(r.Header["Content-Type"][0], "application/json") == 0
}

func decodePartialJSON(data io.ReadCloser) *Student {
	var m map[string]json.RawMessage
	b, err := ioutil.ReadAll(data)
	err = json.Unmarshal(b, &m)
	if err != nil {
		fmt.Println(err.Error())
		return nil
	}
	fmt.Println("map", m)
	st := &Student{}
	if v, ok := m["name"]; ok {
		err := json.Unmarshal(v, &(st.Name))
		if err != nil {
			return nil
		}
	}
	// if v, ok := m["age"]; ok {
	// 	json.Unmarshal(v, &(st.Age))
	// }
	// if v, ok := m["isMale"]; ok {
	// 	json.Unmarshal(v, &(st.IsMale))
	// }
	// if v, ok := m["rollNo"]; ok {
	// 	json.Unmarshal(v, &(st.RollNo))
	// }
	return st
}
