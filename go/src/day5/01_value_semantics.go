package main

import (
	"fmt"
)

type printable interface {
	print()
}

type customer struct {
	name string
	id   int
}

func (c customer) print() {
	fmt.Printf("id: %d name: %s\n", c.id, c.name)
	// fmt.Printf("&c.name: %p\n", &(c.name))
}

func main() {
	var p printable = customer{"abc", 101}
	p.print()

	var p2 printable = &customer{"xyz", 102}
	p2.print()

	// c := customer{"lemon", 0}
	// p3 := c
	// fmt.Printf("&c.name: %p\n", &(c.name))
	// p3.print()

	// p4 := &c
	// p4.print()
}
