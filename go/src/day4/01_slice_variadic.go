package main

import (
	"fmt"
)

func main() {
	nos := []int{10, 20, 30}
	fmt.Println("sum of slice", sum(nos...))
	marks := []int{40, 50, 60}
	nos = append(nos, marks...)
	fmt.Println("nos:", nos)
}

func sum(nos ...int) int {
	result := 0
	fmt.Printf("typeof variadic parameter: %T\n", nos)
	for _, v := range nos {
		result += v
	}
	return result
}
