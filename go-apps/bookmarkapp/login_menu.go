package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"

	"github.com/akshay137/bookmarkapp/auth"
)

func showLogin(authsvc *auth.AService) uint {
	reader := bufio.NewReader(os.Stdin)
	for true {
		fmt.Println("1. Register")
		fmt.Println("2. Login")
		fmt.Println("3. Exit")
		opt, err := reader.ReadString('\n')
		if err != nil {
			fmt.Println("Error reading from stdin")
			continue
		}
		opt = opt[:len(opt)-1]
		i, err := strconv.Atoi(opt)
		if err != nil {
			fmt.Println("Please select valid option")
			continue
		}
		switch i {
		case 1:
			// register
			if id := register(reader, authsvc); id != 0 {
				return id
			}
		case 2:
			// login
			if id := login(reader, authsvc); id != 0 {
				return id
			}
		case 3:
			return 0
		default:
			fmt.Println("Please select valid option")
		}
	}
	return 0
}

func register(reader *bufio.Reader, authsvc *auth.AService) uint {
	fmt.Println("Enter username")
	uname, err := reader.ReadString('\n')
	if err != nil {
		return 0
	}
	fmt.Println("Enter password")
	pass, err := reader.ReadString('\n')
	if err != nil {
		return 0
	}
	id, err := authsvc.Register(
		strings.Replace(uname, "\n", "", -1),
		strings.Replace(pass, "\n", "", -1))
	if err != nil {
		fmt.Println(err.Error())
		return 0
	}
	return id
}

func login(reader *bufio.Reader, authsvc *auth.AService) uint {
	fmt.Println("Enter username")
	uname, err := reader.ReadString('\n')
	if err != nil {
		return 0
	}
	fmt.Println("Enter password")
	pass, err := reader.ReadString('\n')
	if err != nil {
		return 0
	}
	id, err := authsvc.Login(
		strings.Replace(uname, "\n", "", -1),
		strings.Replace(pass, "\n", "", -1))
	if err != nil {
		fmt.Println(err.Error())
		return 0
	}
	return id
}
