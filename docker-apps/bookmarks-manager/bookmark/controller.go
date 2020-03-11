package bookmark

import (
	"log"
	"net/http"
	"strconv"

	uuid "github.com/satori/go.uuid"

	"github.com/gorilla/mux"
	"github.com/jinzhu/gorm"

	"github.com/akshay137/bookmarksmanager/web"
)

const uuidLength = 36

// Controller bookmark controller
type Controller struct {
	svc *Service
}

// NewBookmarkController returns new bookmark controller instance
func NewBookmarkController(db *gorm.DB) *Controller {
	return &Controller{
		svc: NewBookmarkService(db),
	}
}

// RegisterRoutes registers api endpoints for bookmarks
func (bc *Controller) RegisterRoutes(router *mux.Router) {
	router.HandleFunc("/api/bm/users/{u_id}/bookmarks/",
		bc.getAllBookmarks).Methods("GET")
	router.HandleFunc("/api/bm/users/{u_id}/bookmarks/recent/{count}",
		bc.getNBookmarks).Methods("GET")
	router.HandleFunc("/api/bm/users/{u_id}/bookmarks/",
		bc.addBookmark).Methods("POST")
	router.HandleFunc("/api/bm/users/{u_id}/bookmarks/{bm_id}",
		bc.getBookmark).Methods("GET")
	router.HandleFunc("/api/bm/users/{u_id}/bookmarks/{bm_id}",
		bc.deleteBookmark).Methods("DELETE")
	router.HandleFunc("/api/bm/users/{u_id}/bookmarks/{bm_id}",
		bc.updateBookmark).Methods("PUT")
}

func (bc *Controller) getAllBookmarks(w http.ResponseWriter, r *http.Request) {
	log.Println("Get All Bookmarks")
	v := mux.Vars(r)
	uid, ok := v["u_id"]
	if !ok || len(uid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid user id", &w)
		return
	}
	log.Printf("uid: %s\n", uid)
	bms, err := bc.svc.GetAll(uid)
	if err != nil {
		web.WriteHTTPError(http.StatusInternalServerError, "Something went wrong", &w)
		return
	}
	web.EncodeJSONObject(bms, &w)
}

func (bc *Controller) getNBookmarks(w http.ResponseWriter, r *http.Request) {
	log.Println("Get recent bookmarks")
	v := mux.Vars(r)
	uid, ok := v["u_id"]
	if !ok || len(uid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid user id", &w)
		return
	}
	limit, ok := v["count"]
	if !ok {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid count", &w)
		return
	}
	count, err := strconv.Atoi(limit)
	if err != nil {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid count", &w)
		return
	}
	bms, err := bc.svc.GetRecent(uid, count)
	if err != nil {
		web.WriteHTTPError(http.StatusInternalServerError, err.Error(), &w)
		return
	}
	web.EncodeJSONObject(bms, &w)
}

func (bc *Controller) getBookmark(w http.ResponseWriter, r *http.Request) {
	log.Println("Get Bookmark")
	v := mux.Vars(r)
	uid, ok := v["u_id"]
	if !ok || len(uid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "invalid user id", &w)
		return
	}
	bid, ok := v["bm_id"]
	if !ok || len(uid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "invalid bookmark id", &w)
		return
	}
	log.Printf("uid: %s bid: %s\n", uid, bid)
	bm, err := bc.svc.Get(uid, bid)
	if err != nil {
		web.WriteHTTPError(http.StatusInternalServerError, err.Error(), &w)
		return
	}
	web.EncodeJSONObject(bm, &w)
}

func (bc *Controller) addBookmark(w http.ResponseWriter, r *http.Request) {
	log.Println("Add bookmark")
	v := mux.Vars(r)
	uid, ok := v["u_id"]
	if !ok || len(uid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid user id", &w)
		return
	}
	err := r.ParseForm()
	if err != nil {
		web.WriteHTTPError(http.StatusBadRequest, "Error parsing form", &w)
		return
	}
	name := r.PostFormValue("name")
	url := r.PostFormValue("url")
	cid := r.PostFormValue("category_id")
	bm, err := bc.svc.Add(uid, cid, name, url)
	if err != nil {
		web.WriteHTTPError(http.StatusInternalServerError, err.Error(), &w)
		return
	}
	web.EncodeJSONObject(bm.ID, &w)
}

func (bc *Controller) deleteBookmark(w http.ResponseWriter, r *http.Request) {
	log.Println("Delete bookmark")
	v := mux.Vars(r)
	uid, ok := v["u_id"]
	if !ok || len(uid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid user id", &w)
		return
	}
	bid, ok := v["bm_id"]
	if !ok || len(bid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid bookmark id", &w)
		return
	}
	err := bc.svc.Delete(uid, bid)
	if err != nil {
		web.WriteHTTPError(http.StatusInternalServerError, err.Error(), &w)
		return
	}
	w.WriteHeader(http.StatusOK)
}

func (bc *Controller) updateBookmark(w http.ResponseWriter, r *http.Request) {
	log.Println("Update bookmark")
	v := mux.Vars(r)
	uid, ok := v["u_id"]
	if !ok || len(uid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid user id", &w)
		return
	}
	bid, ok := v["bm_id"]
	if !ok || len(bid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid bookmark id", &w)
		return
	}
	log.Printf("uid: %s bid: %s\n", uid, bid)
	bm, err := bc.svc.Get(uid, bid)
	if err != nil {
		web.WriteHTTPError(http.StatusNotFound, "No such bookmark", &w)
		return
	}

	err = r.ParseForm()
	if err != nil {
		web.WriteHTTPError(http.StatusBadRequest, "Error parsing form", &w)
		return
	}
	if v := r.PostFormValue("name"); len(v) > 0 {
		bm.Name = v
	}
	if v := r.PostFormValue("url"); len(v) > 0 {
		bm.URL = v
	}
	if v := r.PostFormValue("category_id"); len(v) > 0 {
		cid := uuid.FromStringOrNil(v)
		if cid == uuid.Nil {
			web.WriteHTTPError(http.StatusBadRequest, "Invalid category id", &w)
			return
		}
		bm.Category = cid
	}
	err = bc.svc.Update(bm)
	if err != nil {
		web.WriteHTTPError(http.StatusInternalServerError, err.Error(), &w)
		return
	}
	web.EncodeJSONObject(bm.ID, &w)
}
