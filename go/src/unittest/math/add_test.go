package math

import (
	"fmt"
	"testing"
)

func TestIsEvenWithOneInput(t *testing.T) {
	input := int64(100)
	expected := true
	actual := isEven(input)
	if expected != actual {
		t.Errorf("input: %v expected: %v actual: %v\n",
			input, expected, actual)
	}
}

func TestIsEvenWithDifferentNumbers(t *testing.T) {
	testData := []struct {
		no     int64
		result bool
	}{
		{10, true},
		{5, false},
		{50, true},
	}

	for _, data := range testData {
		t.Run("Match", func(t *testing.T) {
			actualresult := isEven(data.no)
			if actualresult != data.result {
				t.Errorf(getErrorMessage(data.no,
					data.result, actualresult))
			}
		})
	}
}

func TestAddEvenNosOnly(t *testing.T) {
	n1 := int64(20)
	n2 := int64(30)
	expected := n1 + n2
	actual, _ := addEventNosOnly(n1, n2)
	if expected != actual {
		fmt.Printf("n1: %v n2: %v expected: %v actual: %v\n",
			n1, n2, expected, actual)
	}
}

func getErrorMessage(input, expected, actual interface{}) string {
	return fmt.Sprintf("input: %v expected: %v actual: %v\n",
		input, expected, actual)
}
