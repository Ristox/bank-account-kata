package ee.rsx.kata.codurance.bank.core

import ee.rsx.kata.codurance.bank.api.BankAccount
import ee.rsx.kata.codurance.bank.core.statement.StatementPrinter
import ee.rsx.kata.codurance.bank.core.transaction.gateway.Transactions

internal class Account(
  private val transactions: Transactions,
  private val statementPrinter: StatementPrinter
) : BankAccount {

  override fun deposit(amount: Int) {
    transactions.addDeposit(amount)
  }

  override fun withdraw(amount: Int) {
    if (transactions.isAvailable(amount)) {
      transactions.addWithdrawal(amount)
    }
  }

  override fun printStatement() {
    statementPrinter.print(transactions.listAll())
  }
}
