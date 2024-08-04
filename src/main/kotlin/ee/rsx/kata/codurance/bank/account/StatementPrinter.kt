package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console

class StatementPrinter(val console: Console) {

  fun print(transactions: List<Transaction>) {
    console.printLine("DATE        | AMOUNT   | BALANCE  |")
  }
}
