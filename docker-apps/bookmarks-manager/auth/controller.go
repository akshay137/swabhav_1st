package auth

import (
	"encoding/json"
	"log"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/jinzhu/gorm"

	"github.com/akshay137/bookmarksmanager/web"
)

// Controller Authorization controller
type Controller struct {
	svc *Service
}

// NewAuthController returns new authorization controller
func NewAuthController(db *gorm.DB) *Controller {
	return &Controller{
		svc: NewAuthService(db),
	}
}

// RegisterRoutes registers api endpoints for authorization service
func (ac *Controller) RegisterRoutes(router *mux.Router) {
	router.HandleFunc("/api/bm/users/register/", ac.registerUser).Methods("POST")
	router.HandleFunc("/api/bm/users/login/", ac.loginUser).Methods("POST")
}

func (ac *Controller) registerUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Register User")
	err := r.ParseForm()
	if err != nil {
		log.Println("error parsing form", err.Error())
		web.WriteHTTPError(http.StatusBadRequest, "Could not parse form", &w)
	}
	username := r.PostFormValue("username")
	email := r.PostFormValue("email")
	password := r.PostFormValue("password")
	user, err := ac.svc.GetUserByEmail(email)
	if err == nil {
		// User already exists
		web.WriteHTTPError(http.StatusConflict, "Email is already registered", &w)
		return
	}
	user, err = ac.svc.AddUser(username, email, password)
	if err != nil {
		log.Println("error", err.Error())
		web.WriteHTTPError(http.StatusInternalServerError, "Something went wrong", &w)
		return
	}
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(user.ID)
}

func (ac *Controller) loginUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Login User")
	err := r.ParseForm()
	if err != nil {
		log.Println("error parsing form", err.Error())
		web.WriteHTTPError(http.StatusBadRequest, "Could not parse form", &w)
		return
	}
	email := r.PostFormValue("email")
	user, err := ac.svc.GetUserByEmail(email)
	if err != nil {
		// User does not exists
		web.WriteHTTPError(http.StatusNotFound, "No such user", &w)
		return
	}
	password := r.PostFormValue("password")
	err = ac.svc.AuthenticateUser(user, email, password)
	if err != nil {
		web.WriteHTTPError(http.StatusForbidden, err.Error(), &w)
		return
	}
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(user.ID)
}
