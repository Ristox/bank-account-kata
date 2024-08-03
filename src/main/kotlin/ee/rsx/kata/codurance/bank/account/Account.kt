package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.AccountService
import ee.rsx.kata.codurance.bank.Console

class Account(
  private val console: Console,
  private val transactions: TransactionStorage
) : AccountService {

  override fun deposit(amount: Int) {
    transactions.add(Transaction(amount))
  }

  override fun withdraw(amount: Int) {
    transactions.add(Transaction(-amount))
  }

  override fun printStatement() {
    console.printLine("DATE        | AMOUNT   | BALANCE  |")
    console.printLine("10/04/2024  | 500.00   | 1400.00  |")
    console.printLine("02/04/2024  | -100.00  | 900.00   |")
    console.printLine("01/04/2024  | 1000.00  | 1000.00  |")
  }
}
