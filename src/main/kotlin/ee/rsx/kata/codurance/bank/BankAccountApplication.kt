package ee.rsx.kata.codurance.bank

import ee.rsx.kata.codurance.bank.core.Account
import ee.rsx.kata.codurance.bank.core.statement.Console
import ee.rsx.kata.codurance.bank.core.statement.StatementPrinter
import ee.rsx.kata.codurance.bank.core.transaction.TransactionStorage
import java.time.Clock

fun main() {
  val systemClock = Clock.systemUTC()
  val transactionStorage = TransactionStorage(systemClock)

  val console = Console()
  val statementPrinter = StatementPrinter(console)

  val account = Account(transactionStorage, statementPrinter)

  account.deposit(1000)
  account.withdraw(100)
  account.deposit(500)
  account.printStatement()
}
