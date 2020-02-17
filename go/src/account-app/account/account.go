package account

// Account account structure
type Account struct {
	accNo   int64
	name    string
	balance float64
}

func NewAccount(accNo int64, name string, balance float64) *Account {
	if balance < 0 {
		panic("Balance should be positive")
	}
	return &Account{
		accNo:   accNo,
		name:    name,
		balance: balance,
	}
}

func (acc *Account) Balance() float64 {
	return acc.balance
}

func (acc *Account) Name() string {
	return acc.name
}

func (acc *Account) AccNo() int64 {
	return acc.accNo
}

func (acc *Account) Deposit(amount float64) {
	acc.balance += amount
}

func (acc *Account) Withdraw(amount float64) {
	if amount < 0 || acc.balance-amount < 500 {
		panic("Given amount cant be withdrawn")
	}
	acc.balance -= amount
}
