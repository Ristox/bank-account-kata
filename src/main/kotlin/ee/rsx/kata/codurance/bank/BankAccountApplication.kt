package ee.rsx.kata.codurance.bank

import ee.rsx.kata.codurance.bank.core.Account
import ee.rsx.kata.codurance.bank.core.statement.StatementPrinter
import ee.rsx.kata.codurance.bank.infra.transactions.InMemoryTransactionStorage
import ee.rsx.kata.codurance.bank.infra.console.SystemOutConsole
import java.time.Clock

fun main() {

  val inMemoryTransactions =
    InMemoryTransactionStorage(clock = Clock.systemUTC())

  val statementPrinterToSystemOut =
    StatementPrinter(console = SystemOutConsole())

  val bankAccount = Account(inMemoryTransactions, statementPrinterToSystemOut)

  with(bankAccount) {
    deposit(1000)
    withdraw(100)
    deposit(500)

    printStatement()
  }
}
