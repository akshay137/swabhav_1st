package main

import (
	"fmt"
)

func main() {
	leads := map[string]string{}
	leads["One Peice"] = "Luffy"
	leads["One Punch Man"] = "Saitama"
	leads["Fairy Tail"] = "Lucy"

	fmt.Println("leads:", leads)
	modify(leads)
	fmt.Println("leads:", leads)
	f := Foo{5}
	fmt.Println(f)
}

func modify(m map[string]string) {
	for k := range m {
		// m[k] = ""
		delete(m, k)
	}
	m["Boku No Hero Academia"] = "Izuku"
}
