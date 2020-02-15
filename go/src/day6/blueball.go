package main

import (
	"bufio"
	"errors"
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"strings"
	"time"
)

const MIN_NUMBER int64 = 0
const MAX_NUMBER int64 = 50

func randomRange(min, max int64) int64 {
	rand.Seed(time.Now().UnixNano())
	return (MIN_NUMBER + rand.Int63n(MAX_NUMBER-MIN_NUMBER))
}

func getInputNumber(reader *bufio.Reader) (int64, error) {
	res, err := reader.ReadString('\n')
	if err != nil {
		return 0, errors.New("Cant read from reader")
	}
	res = strings.Replace(res, "\n", "", -1)
	guess, err := strconv.ParseInt(res, 10, 64)
	if err != nil {
		return 0, errors.New("can't parse your input, Please enter valid number")
	}
	return guess, nil
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	magicNumber := randomRange(MIN_NUMBER, MAX_NUMBER)
	// fmt.Println("magick number", magicNumber)
	attempts := 5
	for i := 0; i < attempts; i++ {
		fmt.Printf("Guess the number between %d and %d\n",
			MIN_NUMBER, MAX_NUMBER)
		var guess int64
		{
			var err error
			for guess, err = getInputNumber(reader); err != nil; {
				fmt.Println(err.Error())
				guess, err = getInputNumber(reader)
			}
		}
		if guess == magicNumber {
			fmt.Printf("You guessed the number is %d attempts\n", i+1)
			os.Exit(0)
		} else if guess < magicNumber {
			fmt.Println("Magick number is greater than your guess")
		} else {
			fmt.Println("Magick number is smaller than your guess")
		}
	}

	fmt.Println("You failed to guess magick number")
	fmt.Println("Magick number was:", magicNumber)
	fmt.Printf("You took %d attempts\n", attempts)
}
