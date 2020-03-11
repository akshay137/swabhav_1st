package main

import (
	"fmt"
	"log"
	"net/http"
	"os"
	"os/signal"
	"sync"
	"syscall"
	"time"

	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"

	"github.com/akshay137/bookmarksmanager/auth"
	"github.com/akshay137/bookmarksmanager/bookmark"
)

func main() {
	fmt.Println("Bookmark Manager App")
	db, err := gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav?parseTime=true")
	if err != nil {
		fmt.Println("Error in db connection:", err.Error())
		return
	}
	defer db.Close()

	r := mux.NewRouter()
	methods := handlers.AllowedMethods(
		[]string{"GET", "POST", "PUT", "DELETE"})
	origins := handlers.AllowedOrigins([]string{"*"})
	headers := handlers.AllowedHeaders([]string{"Content-Type"})

	authCtrl := auth.NewAuthController(db)
	authCtrl.RegisterRoutes(r)
	bmCtrl := bookmark.NewBookmarkController(db)
	bmCtrl.RegisterRoutes(r)

	server := http.Server{
		Handler:      handlers.CORS(methods, headers, origins)(r),
		Addr:         ":8080",
		ReadTimeout:  15 * time.Second,
		WriteTimeout: 15 * time.Second,
	}

	intch := make(chan os.Signal)
	signal.Notify(intch, os.Interrupt, syscall.SIGINT)
	go func() {
		s := <-intch
		fmt.Println("Caught signal:", s)
		server.Close()
	}()

	var wait sync.WaitGroup
	wait.Add(1)
	go func() {
		log.Println(server.ListenAndServe())
		wait.Done()
	}()

	wait.Wait()
}
