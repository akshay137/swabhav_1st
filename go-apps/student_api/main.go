package main

import (
	"context"
	"fmt"
	"log"
	"net/http"
	"os"
	"os/signal"
	"runtime"
	"sync"
	"syscall"
	"time"

	"github.com/akshay137/student_api/student"
	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
)

func main() {
	fmt.Println("Student API")

	r := mux.NewRouter()
	sc := student.NewStudentController()
	r.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		w.Write([]byte(
			fmt.Sprintf("go version%s\n", runtime.Version())))
	})
	sc.RegisterRoutes(r)
	origins := handlers.AllowedOrigins([]string{"*"})
	headers := handlers.AllowedHeaders([]string{"Content-Type", "Authorization"})
	methods := handlers.AllowedMethods([]string{"GET", "POST", "PUT", "DELETE"})
	server := &http.Server{
		Handler:      handlers.CORS(headers, methods, origins)(r),
		Addr:         ":8080",
		WriteTimeout: 10 * time.Second,
		ReadTimeout:  10 * time.Second,
	}

	c := make(chan os.Signal)
	signal.Notify(c, os.Interrupt, syscall.SIGINT)
	go func() {
		s := <-c
		fmt.Println("signal", s)
		ctx, cancle := context.WithTimeout(context.Background(), time.Second)
		// server.Close()
		// fmt.Println("Server closed")
		defer cancle()
		server.Shutdown(ctx)
	}()

	var wg sync.WaitGroup
	wg.Add(1)
	go func() {
		log.Println(server.ListenAndServe())
		wg.Done()
	}()
	wg.Wait()
	fmt.Println("Closing student api")
}
