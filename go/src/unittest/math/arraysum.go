package math

import (
	"strconv"
	"strings"
)

type numberValidator func(int64) bool

const FLAGS string = "eo"

func SumArray(list []string) ([]int64, int64, error) {
	validate := allValid
	numbers := make([]int64, 0)
	for _, value := range list {
		if strings.ContainsAny(value, FLAGS) {
			validate = parseFlag(value)
			continue
		}
		num, err := strconv.ParseInt(value, 10, 64)
		if err != nil {
			return nil, 0, err
		}
		numbers = append(numbers, num)
	}
	result := make([]int64, 0)
	var sum int64
	for _, num := range numbers {
		if validate(num) {
			result = append(result, num)
			sum += num
		}
	}
	return result, sum, nil
}

func parseFlag(str string) numberValidator {
	switch str {
	case "-e":
		return evenValid
	case "-o":
		return oddValid
	default:
		return allValid
	}
}

func allValid(val int64) bool {
	return true
}

func evenValid(val int64) bool {
	return isEven(val)
}

func oddValid(val int64) bool {
	return !isEven(val)
}
