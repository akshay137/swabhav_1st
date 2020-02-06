package main

import (
	"fmt"
)

type Foo struct {
	name string
	data int
}

func (f Foo) modifyV(n string) {
	fmt.Println("address v", &f.name)
	f.name = n
}

func (f *Foo) modifyP(n string) {
	fmt.Println("address p", &f.name)
	f.name = n
}

func main() {
	slice := []int{10, 20, 30, 40, 50}
	fmt.Println("slice before modify", slice)
	modifySlice(slice)
	fmt.Println("slice after modify", slice)

	f := Foo{name: "test"}
	fmt.Printf("original f %v: @%p\n", f, &f)

	f2 := &f

	f2.modifyV("f2 name")
	fmt.Println("after modifyV f2", f2.name)

	f.modifyV("new name")
	fmt.Println("after modifyV f", f.name)
	f.modifyP("new name")
	fmt.Println("after modifyP f", f.name)
	// slice = slice[:1]
	// slice = append([]int(nil), slice[:2]...)
	// fmt.Println("slice after slice", slice, len(slice), cap(slice))
}

func modifySlice(s []int) {
	for i := range s {
		s[i] = 0
	}
}
