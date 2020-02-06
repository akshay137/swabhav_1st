package main

import (
	"fmt"
	"reflect"
)

func main() {
	sum(10, 20)
	sum(10, 20, 30, 40, 50)
	sum(10, 20, 30, 40)
	fmt.Println("max", max(10, 20, 30))
	fmt.Println("max", max())
	fmt.Println("max", max(30, 20, 10, 40, 50, 60, 0))
}

func sum(a int, b int, c ...int) {
	fmt.Println("a", a)
	fmt.Println("b", b)
	fmt.Println("c", c)
	fmt.Println("Typeof c", reflect.TypeOf(c))
	for _, val := range c {
		fmt.Println(val)
	}
}

func max(list ...int) int {
	ans := 0
	for _, value := range list {
		if ans < value {
			ans = value
		}
	}
	return ans
}
