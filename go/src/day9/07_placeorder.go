package main

import (
	"fmt"
	"math/rand"
	"time"
)

type order struct {
	amount float64
	id     int64
}

func generateID() int64 {
	return rand.Int63()
}

func placeOrder(o *order, ch chan int64) {
	time.Sleep(1 * time.Second)
	o.id = generateID()
	ch <- o.id
}

func main() {
	rand.Seed(time.Now().UnixNano())
	ch := make(chan int64, 1)
	o := &order{amount: 500}
	o2 := &order{amount: 500}
	go placeOrder(o, ch)
	fmt.Println("Order was placed with id:", <-ch)
	go placeOrder(o2, ch)
	fmt.Println("Order was placed with id:", <-ch)

}
