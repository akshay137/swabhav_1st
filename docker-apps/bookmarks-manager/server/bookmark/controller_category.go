package bookmark

import (
	"net/http"

	"github.com/akshay137/bookmarksmanager/web"
	"github.com/gorilla/mux"
)

func (bc *Controller) RegisterCategoryRoutes(router *mux.Router) {
	router.HandleFunc("/category/", bc.getCategories).Methods("GET")
	router.HandleFunc("/category/", bc.addCategory).Methods("POST")
	router.HandleFunc("/category/{c_id}", bc.getCatByID).Methods("GET")
	router.HandleFunc("/category/{c_id}", bc.updateCategory).Methods("PUT")
	router.HandleFunc("/category/{c_id}", bc.deleteCategory).Methods("DELETE")
}

func (bc *Controller) getCategories(w http.ResponseWriter, r *http.Request) {
	v := mux.Vars(r)
	uid := v["u_id"]
	cs, err := bc.svcCat.GetAll(uid)
	if err != nil {
		web.WriteHTTPError(http.StatusInternalServerError,
			"Something went wrong", &w)
		return
	}
	web.EncodeJSONObject(cs, &w)
}

func (bc *Controller) getCatByID(w http.ResponseWriter, r *http.Request) {
	v := mux.Vars(r)
	uid := v["u_id"]
	cid, ok := v["c_id"]
	if !ok || len(cid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid category id", &w)
		return
	}
	cat, err := bc.svcCat.GetByID(uid, cid)
	if err != nil {
		web.WriteHTTPError(http.StatusNotFound, err.Error(), &w)
		return
	}
	web.EncodeJSONObject(cat, &w)
}

func (bc *Controller) addCategory(w http.ResponseWriter, r *http.Request) {
	v := mux.Vars(r)
	uid := v["u_id"]
	err := r.ParseForm()
	if err != nil {
		web.WriteHTTPError(http.StatusBadRequest, "Error Parsing form", &w)
		return
	}
	name := r.PostFormValue("name")
	if len(name) == 0 {
		web.WriteHTTPError(http.StatusBadRequest, "Empty name", &w)
		return
	}
	// UID := uuid.FromStringOrNil(uid)
	// cat := NewCategory(name, UID)
	cat, err := bc.svcCat.Add(name, uid)
	if err != nil {
		web.WriteHTTPError(http.StatusInternalServerError, err.Error(), &w)
		return
	}
	web.EncodeJSONObject(cat.ID, &w)
}

func (bc *Controller) deleteCategory(w http.ResponseWriter, r *http.Request) {
	v := mux.Vars(r)
	uid := v["u_id"]
	cid, ok := v["c_id"]
	if !ok || len(cid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid category id", &w)
		return
	}
	err := bc.svcCat.Delete(uid, cid)
	if err != nil {
		web.WriteHTTPError(http.StatusBadRequest, err.Error(), &w)
		return
	}
	w.WriteHeader(http.StatusOK)
}

func (bc *Controller) updateCategory(w http.ResponseWriter, r *http.Request) {
	v := mux.Vars(r)
	uid := v["u_id"]
	cid, ok := v["c_id"]
	if !ok || len(cid) != uuidLength {
		web.WriteHTTPError(http.StatusBadRequest, "invalid category id", &w)
		return
	}
	cat, err := bc.svcCat.GetByID(uid, cid)
	if err != nil {
		web.WriteHTTPError(http.StatusNotFound, "No such category", &w)
		return
	}
	err = r.ParseForm()
	if err != nil {
		web.WriteHTTPError(http.StatusBadRequest, "Error parsing form", &w)
		return
	}
	if name := r.PostFormValue("name"); len(name) <= 0 {
		web.WriteHTTPError(http.StatusBadRequest, "Invalid name", &w)
		return
	} else {
		cat.Name = name
		err := bc.svcCat.Update(cat)
		if err != nil {
			web.WriteHTTPError(http.StatusInternalServerError, err.Error(), &w)
			return
		}
	}
	w.WriteHeader(http.StatusOK)
}
