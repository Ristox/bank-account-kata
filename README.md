# Bank Account Kata

### Description

Create a simple bank application with the following features:

* Deposit into Account
* Withdraw from Account
* Print Account statement to the console

### Acceptance criteria

Statement should have transactions in the following format:

```
DATE        | AMOUNT   | BALANCE  |
10/04/2024  | 500.00   | 1400.00  |
02/04/2024  | -100.00  | 900.00   |
01/04/2024  | 1000.00  | 1000.00  |
```

### Starting point and constraints

Start with a class `Account` which implements the following interface

```
public interface AccountService
{
    void deposit(int amount) 
    void withdraw(int amount) 
    void printStatement()
}
```

**NB - You cannot change the public interface of this class**

**Use integers for amounts and Strings for dates, for simplicity**

**Don't worry about spacing in the statement printed to the console**


