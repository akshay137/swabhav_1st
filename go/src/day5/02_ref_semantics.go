package main

import (
	"fmt"
)

type movable interface {
	move()
}

type car struct {
	name string
}

func (c *car) move() {
	fmt.Printf("car %s is moving\n", c.name)
	fmt.Printf("&c.name: %p\n", &(c.name))
}

type bike struct {
	name string
}

func (b bike) move() {
	fmt.Printf("bike %s is moving\n", b.name)
}

func main() {
	c := car{"suzuki"}
	fmt.Printf("&c.name: %p\n", &(c.name))
	var m movable = &c
	m.move()

	var m2 movable = bike{"suzuki"}
	m2.move()
}
