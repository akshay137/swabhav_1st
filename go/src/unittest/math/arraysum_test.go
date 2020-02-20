package math

import (
	"testing"
)

func TestSumArrayEven(t *testing.T) {
	input := []string{"10", "20", "30", "45", "-e"}
	expectedArray := []int64{10, 20, 30}
	expectedResult := int64(10 + 20 + 30)
	actualArray, actualResult, _ := SumArray(input)
	if actualResult != expectedResult {
		printError(t, input, expectedArray, expectedResult,
			actualArray, actualResult)
	}
}

func TestSumArrayOdd(t *testing.T) {
	input := []string{"10", "20", "30", "45", "-o"}
	expectedArray := []int64{45}
	expectedResult := int64(45)
	actualArray, actualResult, _ := SumArray(input)
	if actualResult != expectedResult {
		printError(t, input, expectedArray, expectedResult,
			actualArray, actualResult)
	}
}

func TestSumArrayNoFlag(t *testing.T) {
	input := []string{"10", "20", "30", "45"}
	expectedArray := []int64{10, 20, 30, 45}
	expectedResult := int64(10 + 20 + 30 + 45)
	actualArray, actualResult, _ := SumArray(input)
	if actualResult != expectedResult {
		printError(t, input, expectedArray, expectedResult,
			actualArray, actualResult)
	}
}

func printError(t *testing.T, input []string,
	expectedArray []int64, expectedResult int64,
	actualArray []int64, actualResult int64,
) {
	t.Errorf("input: %v\n", input)
	t.Errorf("expected array: %v\n", expectedArray)
	t.Errorf("expected result: %v\n", expectedResult)
	t.Errorf("actual array: %v\n", actualArray)
	t.Errorf("actual result: %v\n", actualResult)
}
