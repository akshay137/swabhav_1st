package main

import (
	"account-app/account"
	"fmt"
)

func printInfo(acc *account.Account) {
	fmt.Printf("Account id is: %d\nName is: %s\nBalance is: %.2f\n",
		acc.AccNo(), acc.Name(), acc.Balance())
}

func main() {
	acc := account.NewAccount(101, "abc", 500)
	printInfo(acc)
	acc.Deposit(100)
	printInfo(acc)
	acc.Withdraw(100)
	printInfo(acc)
	acc.Withdraw(100)
	printInfo(acc)
}
