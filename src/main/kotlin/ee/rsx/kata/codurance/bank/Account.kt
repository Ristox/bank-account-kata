package ee.rsx.kata.codurance.bank

import ee.rsx.kata.codurance.bank.api.BankAccount
import ee.rsx.kata.codurance.bank.statement.StatementPrinter
import ee.rsx.kata.codurance.bank.transaction.TransactionStorage

internal class Account(
  private val transactions: TransactionStorage,
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
