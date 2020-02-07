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
}

type bike struct {
	name string
}

func (b *bike) move() {
	fmt.Printf("bike %s is moving\n", b.name)
}

type truck struct {
	name string
}

func (t *truck) move() {
	fmt.Printf("truck %s is moving\n", t.name)
}

func race(vehicles []movable) {
	for _, v := range vehicles {
		v.move()
	}
}

func main() {
	race([]movable{
		&car{"Tesla"},
		&bike{"Kawasaki"},
		&truck{"AshokLeyland"},
	})
}
