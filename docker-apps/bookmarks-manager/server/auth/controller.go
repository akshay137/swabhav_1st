package auth

import (
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

// Service returns service
func (ac *Controller) Service() *Service {
	return ac.svc
}

// RegisterRoutes registers api endpoints for authorization service
func (ac *Controller) RegisterRoutes(router *mux.Router) {
	authRouter := router.PathPrefix("/api/bm/users").Subrouter()
	authRouter.HandleFunc("/{u_id}", ac.getUser).Methods("GET")
	authRouter.HandleFunc("/register/", ac.registerUser).Methods("POST")
	authRouter.HandleFunc("/login/", ac.loginUser).Methods("POST")
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
	web.EncodeJSONObject(user.ID, &w)
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
	web.EncodeJSONObject(user.ID, &w)
	// w.WriteHeader(http.StatusOK)
	// json.NewEncoder(w).Encode(user.ID)
}

func (ac *Controller) getUser(w http.ResponseWriter, r *http.Request) {
	log.Println("Get User")
	v := mux.Vars(r)
	uid, ok := v["u_id"]
	if !ok || len(uid) != 36 {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid user id", &w)
		return
	}
	user, err := ac.svc.GetUserById(uid)
	if err != nil {
		web.WriteHTTPError(http.StatusNotFound, err.Error(), &w)
		return
	}
	web.EncodeJSONObject(user, &w)
}
