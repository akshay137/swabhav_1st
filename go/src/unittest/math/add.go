package math

import (
	"errors"
	"fmt"
)

func isEven(no int64) bool {
	return no%2 == 0
}

func addEventNosOnly(n1, n2 int64) (int64, error) {
	if !isEven(n1) {
		return 0, errors.New(fmt.Sprintf("%d is not even", n1))
	}
	if !isEven(n2) {
		return 0, errors.New(fmt.Sprintf("%d is not even", n2))
	}
	return n1 + n2, nil
}
