package main

import (
	"fmt"
)

func main() {
	// case1()
	case2()
}

func case1() {
	// slice := []int {10, 20, 30}
	slice := make([]int, 3, 4)
	slice[0] = 10
	slice[1] = 20
	slice[2] = 30
	fmt.Printf("value of capacity: %v\n", cap(slice))
	fmt.Printf("value of size: %v\n", len(slice))
	fmt.Printf("value of address of array[0]: %v\n", &slice[0])
	fmt.Println("slice", slice)

	slice = append(slice, 40, 50, 60)
	fmt.Printf("value of capacity: %v\n", cap(slice))
	fmt.Printf("value of size: %v\n", len(slice))
	fmt.Printf("value of address of array[0]: %v\n", &slice[0])

	pointerToSlice := slice
	pointerToSlice[0] = 0
	slice[1] = 1
	fmt.Printf("typeof slice: %T typeof pointerToSlice: %T\n",
		slice, pointerToSlice)
	fmt.Println("pointerToSlice", pointerToSlice)
	fmt.Println("slice", slice)
}

func case2() {
	slice := []int{10, 20}
	slicePointer := slice
	slice = append(slice, 30, 40, 50)
	slice[0] = 0
	fmt.Println("slice[0]", slice[0])
	fmt.Println("slicePointer[0]", slicePointer[0])
	fmt.Println("slice", slice)
	sliceP(&slice)
	fmt.Println("slice", slice)

}

func sliceP(slice *[]int) {
	// size := len(*slice)
	for i := range *slice {
		(*slice)[i] = 0
	}
}
