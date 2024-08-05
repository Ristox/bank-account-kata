package ee.rsx.kata.codurance.bank

import ee.rsx.kata.codurance.bank.core.Account
import ee.rsx.kata.codurance.bank.core.statement.StatementPrinter
import ee.rsx.kata.codurance.bank.infra.transactions.InMemoryTransactionStorage
import ee.rsx.kata.codurance.bank.infra.console.SystemOutConsole
import java.time.Clock

fun main() {
  val systemClock = Clock.systemUTC()
  val inMemoryTransactions = InMemoryTransactionStorage(systemClock)

  val console = SystemOutConsole()
  val statementPrinterToSystemOut = StatementPrinter(console)

  val account = Account(inMemoryTransactions, statementPrinterToSystemOut)

  account.deposit(1000)
  account.withdraw(100)
  account.deposit(500)
  account.printStatement()
}
