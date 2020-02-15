package main

import (
	"fmt"
)

type myfunc func() int

func main() {
	case1()
	case2()
	case3()
	case4()
	case5()
	case6()
}

func case1() {
	fmt.Println("Case 1")
	func() {
		fmt.Println("Anonymous function in case1 called")
	}()
}

func case2() {
	fmt.Println("Case 2")
	f1 := func(name string) {
		fmt.Println("hello", name)
	}
	fmt.Printf("typeof f1: %T\n", f1)
	f1("go")
}

func giveFunc() func(int32, int32) int32 {
	return func(n1, n2 int32) int32 {
		return n1 + n2
	}
}

func case3() {
	f1 := giveFunc()
	fmt.Println("f1(10, 20):", f1(10, 20))
}

func doProcessing(num int32, callback func(string)) {
	callback(fmt.Sprintf("Processing on server... %d",
		num*num*num))
}

func case4() {
	fmt.Println("Case 4")
	doProcessing(2, func(res string) {
		fmt.Println("res", res)
	})
}

func case5() {
	fmt.Println("Case 5")
	manyFuncs := []func() int{}
	for i := 1; i <= 5; i++ {
		manyFuncs = append(manyFuncs, func() int {
			return i * 10
		})
	}

	// fmt.Println(manyFuncs[0]())

	fmt.Println("Invoking...")
	for i, f := range manyFuncs {
		fmt.Println(i, f())
	}
}

func case6() {
	fmt.Println("Case 6")
	fns := []myfunc{}
	for i := 1; i <= 5; i++ {
		fns = append(fns, func(index int) myfunc {
			return func() int {
				return index * 10
			}
		}(i))
		// index := i * 10
		// fns = append(fns, func() int {
		// 	return index
		// })
	}

	fmt.Println("Invoking...")
	for i, f := range fns {
		fmt.Println(i, f())
	}
}
